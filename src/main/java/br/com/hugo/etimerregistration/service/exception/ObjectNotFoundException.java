package br.com.hugo.etimerregistration.service.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String msg) {
        super(msg);
    }

}
