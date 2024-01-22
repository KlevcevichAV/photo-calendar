package com.klevtcevichav.photocalendar.auth.dto.request;

import com.klevtcevichav.photocalendar.core.dto.request.AbstractRequestDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|" +
            "2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|" +
            "4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$")
    private String phoneNumber;

}
