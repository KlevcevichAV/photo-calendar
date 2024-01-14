package com.klevtcevichav.photocalendar.core.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractResponseDTO extends SimpleResponseDTO {

    private Long id;
}
