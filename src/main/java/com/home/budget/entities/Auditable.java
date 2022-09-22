package com.home.budget.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    @CreatedBy
    protected String createdUser;
    @LastModifiedBy
    protected String updatedUser;
    @Column(nullable = false, updatable = false)
    @CreatedDate
    protected LocalDateTime createdDate;
    @LastModifiedDate
    protected LocalDateTime updatedDate;

    @PrePersist
    private void prePersist() {
        ZoneId zone = ZoneId.of("Europe/Warsaw");
        createdDate = LocalDateTime.now(ZoneId.of(zone.getId()));
    }

    @PreUpdate
    private void preUpdate() {
        ZoneId zone = ZoneId.of("Europe/Warsaw");
        updatedDate = LocalDateTime.now(ZoneId.of(zone.getId()));
    }
}
