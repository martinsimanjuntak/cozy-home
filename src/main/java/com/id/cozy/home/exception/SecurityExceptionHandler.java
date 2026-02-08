package com.id.cozy.home.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author martin
 * Date 07/02/26
 */

@RestControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(CustomSecurityException.class)
    public ResponseEntity<?> handleApiException(CustomSecurityException ex,
                                                HttpServletRequest req) {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", ex.getStatusCode());
        body.put("error", HttpStatus.valueOf(ex.getStatusCode()).getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("path", req.getRequestURI());

        return new ResponseEntity<>(body, HttpStatus.valueOf(ex.getStatusCode()));
    }
}
