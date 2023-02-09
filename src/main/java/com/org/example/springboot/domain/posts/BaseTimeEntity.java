package com.org.example.springboot.domain.posts;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // (1)
@EntityListeners(AuditingEntityListener.class) // (2)
public abstract class BaseTimeEntity {

    @CreatedDate // (3)
    private LocalDateTime createdDate;

    @LastModifiedDate // (4)
    private LocalDateTime modifiedDate;
}
/*BaseTimeEntity 클래스는 모든 Entity의 상위 클래스가 되어 Entity들의 createdDate, modifiedDate를 자동으로 관리하는 역할*/
