package com.klevtcevichav.photocalendar.dto.response;

import com.klevtcevichav.photocalendar.core.dto.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoResponseDTO extends AbstractDTO {

    private Long accountId;
    private String nameFile;
    private LocalDate dateTimeOfCreation;
    private byte[] photo;
    private String location;
//    private List<String> tags;
}
