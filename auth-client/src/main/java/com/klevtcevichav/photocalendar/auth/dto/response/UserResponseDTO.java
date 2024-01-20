package com.klevtcevichav.photocalendar.auth.dto.response;

import com.klevtcevichav.photocalendar.core.dto.AbstractDTO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO extends AbstractDTO {

    private Long accountId;
    private String email;
    private String username;
    private String phoneNumber;
}
