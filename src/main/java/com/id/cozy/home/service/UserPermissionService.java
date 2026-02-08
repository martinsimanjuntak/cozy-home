package com.id.cozy.home.service;

import com.id.cozy.home.entity.UserEntity;
import com.id.cozy.home.entity.UserRoleEntity;
import com.id.cozy.home.exception.CustomSecurityException;
import com.id.cozy.home.repository.RolePermissionRepository;
import com.id.cozy.home.repository.UserRepository;
import com.id.cozy.home.repository.UserRoleRepository;
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
