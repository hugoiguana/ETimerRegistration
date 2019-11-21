package br.com.hugo.etimerregistration.domain;

import lombok.Getter;

@Getter
public enum EProfile {

    EMPLOYEER(1, "ROLE_EMPLOYEER");

    private int code;
    private String role;

    private EProfile(Integer code, String role) {
        this.code = code;
        this.role = role;
    }

    public static EProfile toEnum(Integer cod) {
        for (EProfile p : EProfile.values()) {
            if (cod.equals(p.getCode())) {
                return p;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + cod);
    }
}
