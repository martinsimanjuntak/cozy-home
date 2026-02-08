package com.id.cozy.home.repository;

import com.id.cozy.home.entity.RoleEntity;
import com.id.cozy.home.entity.RolePermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author martin
 * Date 07/02/26
 */


public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, Long> {
    Optional<RolePermissionEntity> findByRoleAndPathAndMethod(RoleEntity role, String path, String method);
}
