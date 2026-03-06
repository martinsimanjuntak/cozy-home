package com.id.cozy.home.security.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;

@UtilityClass
public class JwtUtil {

    public static String getUsername() {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (auth == null || !(auth.getPrincipal() instanceof Jwt jwt)) {
            return null;
        }

        return jwt.getClaim("preferred_username");
    }

}