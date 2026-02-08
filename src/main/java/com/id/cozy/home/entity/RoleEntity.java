package com.id.cozy.home.entity;

import com.id.cozy.home.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author martin
 * Date 07/02/26
 */

@Entity
@Table(name = "ROLE_ACCOUNT")
@Setter
@Getter
public class RoleEntity extends BaseEntity {

    @Column(name = "NAME")
    String name;
}
