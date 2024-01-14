package com.klevtcevichav.photocalendar.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends AbstractUser {

    private String password;
}
