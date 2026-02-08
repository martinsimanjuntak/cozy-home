package com.id.cozy.home.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @author martin
 * Date 07/02/26
 */

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    Long id;

    @Column(name = "IS_DELETE")
    boolean deleted;

    @Version
    @Column(name="VERSION")
    Long version;

    @CreatedBy
    @Column(name = "CREATED_BY")
    String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "UPDATED_BY")
    String updatedBy;

    @LastModifiedDate
    @Column(name = "UPDATED_DATE")
    LocalDateTime updatedDate;

}
