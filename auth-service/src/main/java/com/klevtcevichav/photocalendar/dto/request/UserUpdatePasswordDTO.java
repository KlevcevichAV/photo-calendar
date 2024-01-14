package com.klevtcevichav.photocalendar.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdatePasswordDTO {

    @NotNull
    private Long id;
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;
}
