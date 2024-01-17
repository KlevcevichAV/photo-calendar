package com.klevtcevichav.photocalendar.dto.request;

import com.klevtcevichav.photocalendar.core.dto.request.AbstractRequestDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDTO extends AbstractRequestDTO {

    @Email
    private String email;
    @NotEmpty
    private String username;
    @NotEmpty
//  TODO  THINK ABOUT IT!!!!!
    private String phoneNumber;

}