package com.klevtcevichav.photocalendar.dto.request;


import com.klevtcevichav.photocalendar.core.dto.request.SimpleRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddPhotoRequestDTO extends SimpleRequestDTO {

    private byte[] photo;
    private Long accountId;
    private String fileName;
    private LocalDate dateOfCreation;
    private String location;


}
