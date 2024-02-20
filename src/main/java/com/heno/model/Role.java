package com.heno.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enum describing user roles.
 */
public enum Role implements GrantedAuthority {

    SUPERVISOR,
    DEPUTYDIRECTORFORLOGISTICS,
    SALESMAN,
    LOGISTICAL,
    ACCOUNTANT;

    @Override
    public String getAuthority() {
        return name();
    }
}