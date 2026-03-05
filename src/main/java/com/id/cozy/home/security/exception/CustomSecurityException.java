package com.id.cozy.home.security.exception;

import lombok.Getter;

/**
 * @author martin
 * Date 07/02/26
 */

@Getter
public class CustomSecurityException extends RuntimeException {

    private final int statusCode;

    public CustomSecurityException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
