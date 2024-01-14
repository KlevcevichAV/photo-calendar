package com.klevtcevichav.photocalendar.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
    }
}
