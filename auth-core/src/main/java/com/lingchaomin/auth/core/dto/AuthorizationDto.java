package com.lingchaomin.auth.core.dto;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/29 下午9:25
 * @description
 */
public class AuthorizationDto implements Serializable {

    private Collection<String> roles;

    private Collection<String> permissions;

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }

    public Collection<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Collection<String> permissions) {
        this.permissions = permissions;
    }
}
