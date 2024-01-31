package com.klevtcevichav.photocalendar.entity;

import com.klevtcevichav.photocalendar.core.entity.FullAbstractEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public abstract class AbstractUser extends FullAbstractEntity {

    private Long accountId;
    private String email;
    private String username;
    private String phoneNumber;

    private String accessToken;
    private String refreshToken;
}

