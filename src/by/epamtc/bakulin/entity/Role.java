package by.epamtc.bakulin.entity;

import java.io.Serializable;

import static by.epamtc.bakulin.entity.constant.AuthoritiesConstant.*;

public enum Role implements Serializable {
    USER(USER_AUTHORITIES), ADMIN(ADMIN_AUTHORITIES);

    private String[] authorities;

    private String roleName;

    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
