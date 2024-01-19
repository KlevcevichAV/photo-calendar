package com.klevtcevichav.photocalendar.calendar.client;

import com.klevtcevichav.photocalendar.calendar.dto.request.CalendarRequestDTO;
import com.klevtcevichav.photocalendar.calendar.dto.request.DayRequestDTO;
import com.klevtcevichav.photocalendar.calendar.dto.request.MonthRequestDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.CalendarResponseDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.DayResponseDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient
public interface CalendarClientApi {

    @GetMapping("/{accountId}/{year}")
    ResponseEntity<CalendarResponseDTO> getCalendar(@RequestBody @Valid CalendarRequestDTO calendarRequestDTO);

    @GetMapping("/{accountId}/{year}/{month}")
    ResponseEntity<CalendarResponseDTO> getMonth(@RequestBody @Valid MonthRequestDTO monthRequestDTO);

    @GetMapping("/{accountId}/{year}/{month}/{day}")
    ResponseEntity<DayResponseDTO> getDay(@RequestBody @Valid DayRequestDTO monthRequestDTO);
}
