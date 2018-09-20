package com.cn.common.service;

import com.cn.domain.entity.SysUser;
import com.cn.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by bozhou on 2018/1/22.
 */
@Component
public class UserService {
    @Autowired
    private SysUserService sysUserService;
    public SysUser getUserByUserName(String username) {
        SysUser sysUser = sysUserService.getUserByUserName(username);
        return sysUser;
    }
}
