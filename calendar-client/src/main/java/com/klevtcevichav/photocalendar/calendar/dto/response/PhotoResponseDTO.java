package com.klevtcevichav.photocalendar.calendar.dto.response;

import com.klevtcevichav.photocalendar.core.dto.response.FullAbstractResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoResponseDTO extends FullAbstractResponseDTO {

    private Long accountId;
    private String fileName;
    private LocalDate dateOfCreationPhoto;
    private byte[] photo;
    private String location;
//    private List<String> tags;
}
