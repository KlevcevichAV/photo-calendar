package com.klevtcevichav.photocalendar.dto.response;

import com.klevtcevichav.photocalendar.core.dto.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDTO extends AbstractDTO {

    private Long userId;
    private String name;
    private String fullName;
    private Long age;
}
