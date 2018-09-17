package com.cn.common.security.controller;

import com.cn.common.security.JwtTokenUtil;
import com.cn.common.security.JwtUser;
import com.cn.domain.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserRestController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public SysUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader("token");
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(username);
        return jwtUser.getSysUser();
    }

}
