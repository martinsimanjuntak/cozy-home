package com.id.cozy.home.annotation;

import com.id.cozy.home.exception.CustomSecurityException;
import com.id.cozy.home.service.UserPermissionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class CheckAccessAspect {

    private final UserPermissionService permissionService;
    private final HttpServletRequest request;

    @Before("@annotation(com.id.cozy.home.annotation.CheckAccess)")
    public void checkAccess() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new CustomSecurityException(401, "User not authenticated");
        }
        Jwt jwt = (Jwt) auth.getPrincipal();
        String username = jwt.getClaim("preferred_username");
        String path = request.getRequestURI();
        String method = request.getMethod();

        boolean allowed = permissionService.isAllowed(username, path, method);
        if (!allowed) {
            throw new CustomSecurityException(403, "Access denied");
        }
    }
}