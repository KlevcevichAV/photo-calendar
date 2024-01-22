package com.klevtcevichav.photocalendar.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserOIDC extends AbstractUser {

    private String oidcAccessToken;
    private String oidcRefreshToken;
}
