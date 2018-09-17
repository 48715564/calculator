package com.cn.common.security;

import com.cn.domain.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(SysUser sysUser) {
        return new JwtUser(sysUser, mapToGrantedAuthorities());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities() {
            return new ArrayList<>();
    }
}
