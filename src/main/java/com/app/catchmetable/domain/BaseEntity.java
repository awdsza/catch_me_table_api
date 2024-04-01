package com.app.catchmetable.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // 공통적인 필드가 있을떄 사용(ex.작성일자,작성자, 수정일자, 수정자)
@EntityListeners(AuditingEntityListener.class) // 엔티티를 수정하거나 추가하는 행위를 감시하는 어노테이션이다. 이에 대한 처리는 괄호의 클래스가 담당한다.
public abstract class BaseEntity {

    @Column(name="created_by",updatable = false) //해당 객체를 상속받은 엔티티가 업데이트 될때 해당 칼럼은 업데이트 되지않는다.
    private String createdBy;
    @CreatedDate
    @Column(name="created_date",updatable = false)//해당 객체를 상속받은 엔티티가 업데이트 될때 해당 칼럼은 업데이트 되지않는다.
    private LocalDateTime createdDate;
    @Column(name="last_modified_by",insertable = false)//해당 객체를 상속받은 엔티티가 생성 될때 해당 칼럼은 업데이트 되지않는다.
    private String lasModifiedBy;
    @LastModifiedDate
    @Column(name="last_modified_date",insertable = false)//해당 객체를 상속받은 엔티티가 생성 될때 해당 칼럼은 업데이트 되지않는다.
    private LocalDateTime lastModifiedDate;

}
