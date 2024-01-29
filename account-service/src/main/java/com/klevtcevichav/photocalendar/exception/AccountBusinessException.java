package com.klevtcevichav.photocalendar.exception;

import com.klevtcevichav.photocalendar.core.exception.BusinessException;

public class AccountBusinessException extends BusinessException {

    public AccountBusinessException(String message) {
        super(message);
    }
}
