package com.id.cozy.home.security.service;

import com.id.cozy.home.security.entity.UserEntity;
import com.id.cozy.home.security.entity.UserRoleEntity;
import com.id.cozy.home.security.exception.CustomSecurityException;
import com.id.cozy.home.security.repository.RolePermissionRepository;
import com.id.cozy.home.security.repository.UserRepository;
import com.id.cozy.home.security.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author martin
 * Date 07/02/26
 */

@Service
@RequiredArgsConstructor
public class UserPermissionService {

    private final UserRepository userRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final UserRoleRepository userRoleRepository;

    public boolean isAllowed(String username, String path, String method) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new CustomSecurityException(400, "User not found"));

        UserRoleEntity userRoleEntity = userRoleRepository.findByUser(userEntity).orElseThrow(() -> new CustomSecurityException(400, "Role not found"));

        return rolePermissionRepository
                .findByRoleAndPathAndMethod(userRoleEntity.getRole(), path, method)
                .isPresent();
    }
}
