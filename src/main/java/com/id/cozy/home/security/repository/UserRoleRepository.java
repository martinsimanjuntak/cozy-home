package com.id.cozy.home.security.repository;

import com.id.cozy.home.security.entity.UserEntity;
import com.id.cozy.home.security.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author martin
 * Date 07/02/26
 */


public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    Optional<UserRoleEntity> findByUser(UserEntity user);
}
