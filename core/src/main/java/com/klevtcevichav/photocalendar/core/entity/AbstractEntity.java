package com.klevtcevichav.photocalendar.core.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
@MappedSuperclass
@Data
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
}
