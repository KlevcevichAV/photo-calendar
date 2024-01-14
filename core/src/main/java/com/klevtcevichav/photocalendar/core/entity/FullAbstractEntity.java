package com.klevtcevichav.photocalendar.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class FullAbstractEntity extends AbstractEntity{
    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation;

    @Column(name = "date_of_last_update")
    private LocalDateTime dateOfLastUpdate;
    @Column(name = "date_of_delete")
    private LocalDateTime dateOfDelete;

    @PrePersist
    protected void abstractEntityPreInit() {
        this.dateOfCreation = LocalDateTime.now();
        this.dateOfLastUpdate = LocalDateTime.now();
    }

    @PreUpdate
    protected void abstractEntityPreUpdate() {
        this.dateOfLastUpdate = LocalDateTime.now();
    }
}
