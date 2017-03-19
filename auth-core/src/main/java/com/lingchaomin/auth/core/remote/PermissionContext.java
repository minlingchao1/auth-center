package com.lingchaomin.auth.core.remote;

import java.io.Serializable;
import java.util.Set;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 下午10:32
 * @description
 */
public class PermissionContext implements Serializable {
    private Set<String> roles;
    private Set<String> permissions;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }


    @Override
    public String toString() {
        return "PermissionContext{" +
                ", roles=" + roles +
                ", permissions=" + permissions +
                '}';
    }
}