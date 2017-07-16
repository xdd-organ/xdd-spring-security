package com.java.xdd.springsecurity.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Administrator on 2017/7/16.
 */
public class UserRole  implements GrantedAuthority {
    private static final long serialVersionUID = 2961750449929019405L;

    private String role;

    /** getter and setter method */
    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public String getAuthority() {
        return role;
    }
}
