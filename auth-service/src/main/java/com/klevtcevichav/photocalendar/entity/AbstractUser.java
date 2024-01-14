package com.klevtcevichav.photocalendar.entity;

import com.klevtcevichav.photocalendar.core.entity.FullAbstractEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractUser extends FullAbstractEntity {

    private Long accountId;
    private String email;
    private String username;
    private String phoneNumber;

    private String accessToken;
    private String refreshToken;
}

