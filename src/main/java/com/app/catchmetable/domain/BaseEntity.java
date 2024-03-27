package com.app.catchmetable.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Column(name="created_by",updatable = false)
    private String createdBy;
    @CreatedDate
    @Column(name="created_date",updatable = false)
    private LocalDateTime createdDate;
    @Column(name="last_modified_by",insertable = false)
    private String lasModifiedBy;
    @LastModifiedDate
    @Column(name="last_modified_date",insertable = false)
    private LocalDateTime lastModifiedDate;

}
