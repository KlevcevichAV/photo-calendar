package com.klevtcevichav.photocalendar.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserOIDC extends AbstractUser {

    //TODO: think about it...
    private String oidcAccessToken;
    private String oidcRefreshToken;
}
