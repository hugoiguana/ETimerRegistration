package br.com.hugo.etimerregistration.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationEmployeeDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String pis;
    private String password;
}