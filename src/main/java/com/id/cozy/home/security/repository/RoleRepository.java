package com.id.cozy.home.security.repository;

import com.id.cozy.home.security.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author martin
 * Date 07/02/26
 */


public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);
}
