package com.klevtcevichav.photocalendar.dto.request;

import com.klevtcevichav.photocalendar.core.dto.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CalendarRequestDTO extends AbstractDTO {

//    maybe remove because we will have account id in the token :) but now will TAK :0
    private Long accountId;
    private Long year;

}
