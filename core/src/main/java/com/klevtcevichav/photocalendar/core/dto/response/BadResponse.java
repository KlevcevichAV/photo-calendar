package com.klevtcevichav.photocalendar.core.dto.response;

import com.klevtcevichav.photocalendar.core.dto.AbstractDTO;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Builder
@Getter
public class BadResponse extends AbstractDTO {

    private final String message;
    private final Throwable throwable;
    private final ZonedDateTime timeStamp;
}
