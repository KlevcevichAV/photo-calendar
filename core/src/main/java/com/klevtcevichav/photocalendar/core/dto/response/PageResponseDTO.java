package com.klevtcevichav.photocalendar.core.dto.response;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@FieldNameConstants
public class PageResponseDTO extends SimpleResponseDTO {

    private Long pageNumber;
    private Long pageSize;
    private Long totalElements;
    private Long totalPages;
}
