package com.id.cozy.home.security.annotation;

import com.id.cozy.home.security.exception.CustomSecurityException;
import com.id.cozy.home.security.service.UserPermissionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import static com.id.cozy.home.security.util.JwtUtil.getUsername;

@Aspect
@Component
@RequiredArgsConstructor
public class CheckAccessAspect {

    private final UserPermissionService permissionService;
    private final HttpServletRequest request;

    @Before("@annotation(com.id.cozy.home.security.annotation.CheckAccess)")
    public void checkAccess() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new CustomSecurityException(401, "User not authenticated");
        }
        String username = getUsername();
        String path = request.getRequestURI();
        String method = request.getMethod();

        boolean allowed = permissionService.isAllowed(username, path, method);
        if (!allowed) {
            throw new CustomSecurityException(403, "Access denied");
        }
    }
}