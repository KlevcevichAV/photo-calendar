package com.klevtcevichav.photocalendar.calendar.dto.request;


import com.klevtcevichav.photocalendar.core.dto.request.SimpleRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddPhotoRequestDTO extends SimpleRequestDTO {

    @NotNull
    private MultipartFile photo;
    @NotNull
    private Long accountId;
    @NotNull
    private String fileName;
    @NotNull
    private LocalDate dateOfCreationPhoto;
    private String location;

}
