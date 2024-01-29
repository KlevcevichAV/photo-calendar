package com.klevtcevichav.photocalendar.exception;

import com.klevtcevichav.photocalendar.core.dto.response.BadResponse;
import com.klevtcevichav.photocalendar.core.exception.BadRequestException;
import com.klevtcevichav.photocalendar.core.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CalendarApiExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<BadResponse> handleNotFoundException(NotFoundException e) {

        BadResponse badResponse = BadResponse
                .builder()
                .message(e.getMessage())
                .throwable(e)
                .timeStamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();
        return new ResponseEntity<>(badResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<BadResponse> handleBadRequestException(BadRequestException e) {

        BadResponse badResponse = BadResponse
                .builder()
                .message(e.getMessage())
                .throwable(e)
                .timeStamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();
        return new ResponseEntity<>(badResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {CalendarBusinessException.class})
    public ResponseEntity<BadResponse> handleCalendarBusinessException(CalendarBusinessException e) {

        BadResponse badResponse = BadResponse
                .builder()
                .message(e.getMessage())
                .throwable(e)
                .timeStamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();
        return new ResponseEntity<>(badResponse, HttpStatus.NOT_FOUND);
    }
}
