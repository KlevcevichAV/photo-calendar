package com.klevtcevichav.photocalendar.enitty;

import com.klevtcevichav.photocalendar.core.entity.FullAbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldNameConstants
public class Account extends FullAbstractEntity {

    @Column
    private Long userId;
    @Column
    private String name;
    @Column
    private String fullName;
    @Column
    private LocalDate birthDate;
}