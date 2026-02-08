package com.id.cozy.home.entity;

import com.id.cozy.home.entity.base.BaseEntity;
import jakarta.persistence.Column;
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
@Table(name = "ROLE_PERMISSION")
@Setter
@Getter
public class RolePermissionEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @Column(name = "PATH")
    String path;

    @Column(name = "METHOD")
    String method;
}
