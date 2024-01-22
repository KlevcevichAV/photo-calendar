package com.klevtcevichav.photocalendar.exception;

import com.klevtcevichav.photocalendar.core.exception.BusinessException;

public class CalendarBusinessException extends BusinessException {

    public CalendarBusinessException(String message) {
        super(message);
    }
}
