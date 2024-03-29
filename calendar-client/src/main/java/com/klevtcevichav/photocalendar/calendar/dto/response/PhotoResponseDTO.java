package com.klevtcevichav.photocalendar.calendar.dto.response;

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
    private LocalDate dateOfCreationPhoto;
    private byte[] photo;
    private String location;
//    private List<String> tags;
}
