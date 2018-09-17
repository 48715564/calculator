package com.cn.common.infrastructure;

import com.cn.common.infrastructure.annotation.AccessTimesAnnotation;
import com.cn.common.utils.CommUtil;
import com.cn.common.utils.StringUtils;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.util.concurrent.TimeUnit;


@Aspect
@Component
public class AccessTimesContract {
    private final Log logger = LogFactory.getLog(getClass());
    public static final String key = "accessTime";
    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Before("execution(* com.cn.webapi.*..*(..)) && @annotation(accessTimesAnnotation))")
    public void requestLimit(final JoinPoint joinPoint, AccessTimesAnnotation accessTimesAnnotation) throws ValidationException {
        Object[] args = joinPoint.getArgs();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        if (request == null) {
            throw new ValidationException("方法中缺失HttpServletRequest参数!");
        }
        String ip = CommUtil.getIp(request);
        String url = request.getRequestURL().toString();
        String key = "req_limit_".concat(url).concat(ip);
        IMap map = hazelcastInstance.getMap("accessTime");
        Long count = map.get(key)==null?0: (Long) map.get(key);
        count=count+1;
        if (count == 1) {
            map.put(key,count,accessTimesAnnotation.time(),TimeUnit.MILLISECONDS);
        }
        if (count > accessTimesAnnotation.count()) {
            throw new ValidationException("请勿频繁操作!");
        }
    }

    public static String byteToBit(byte b) {
        return ""
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);
    }

    public static void main(String[] args) {
        Long b = 10L;
        System.out.println(0x1);
        System.out.println(StringUtils.leftPad(Long.toBinaryString(b),32,"0"));
    }

}