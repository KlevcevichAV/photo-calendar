package com.klevtcevichav.photocalendar.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserProfile extends AbstractUser {

    private String password;
}
