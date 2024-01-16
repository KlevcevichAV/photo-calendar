package com.klevtcevichav.photocalendar.dto.response;

import com.klevtcevichav.photocalendar.core.entity.FullAbstractEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoResponseDTO extends FullAbstractEntity {

    private Long accountId;
    private String fileName;
    private LocalDate dateOfCreation;
    private byte[] photo;
    private String location;
//    private List<String> tags;
}
