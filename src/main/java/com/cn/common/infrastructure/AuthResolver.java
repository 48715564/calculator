package com.cn.common.infrastructure;


import com.cn.common.security.JwtTokenUtil;
import com.cn.common.utils.StringUtils;
import com.cn.domain.entity.SysUser;
import com.cn.service.SysUserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;

@Component
public class AuthResolver extends AbstractNamedValueMethodArgumentResolver {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SysUserService sysUserService;

    @Override
    protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
        return new NamedValueInfo("userId", false, null);
    }

    @Override
    protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request) throws Exception {
        String authToken = request.getHeader("token");
        if (StringUtils.isNotBlank(authToken)) {
            try {
                String username = jwtTokenUtil.getUsernameFromToken(authToken);
                if (StringUtils.isNotBlank(username)) {
                    SysUser sysUser = sysUserService.getUserByUserName(username);
                    if (sysUser != null) {
                        return sysUser.getId();
                    }
                }
            } catch (IllegalArgumentException e) {
//                logger.error("an error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
//                logger.warn("the token is expired and not valid anymore", e);
            }
        }
        return null;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.getParameterName().equals("userId")) {
            return true;
        }
        return false;
    }

}