package com.id.cozy.home.entity;

import com.id.cozy.home.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author martin
 * Date 07/02/26
 */

@Entity
@Table(name = "USER_ROLE_ACCOUNT")
@Getter
@Setter
public class UserRoleEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity roles;


    public RoleEntity getRole() {
        return this.roles;
    }
}
