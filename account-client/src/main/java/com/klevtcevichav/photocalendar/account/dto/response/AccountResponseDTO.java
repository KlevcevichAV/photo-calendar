package com.klevtcevichav.photocalendar.account.dto.response;

import com.klevtcevichav.photocalendar.core.dto.response.FullAbstractResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDTO extends FullAbstractResponseDTO {

    private Long userId;
    private String name;
    private String fullName;
    private Long age;
}
