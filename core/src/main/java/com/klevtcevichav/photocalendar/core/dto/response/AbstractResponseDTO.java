package com.klevtcevichav.photocalendar.core.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public abstract class AbstractResponseDTO extends SimpleResponseDTO {

    private Long id;
}
