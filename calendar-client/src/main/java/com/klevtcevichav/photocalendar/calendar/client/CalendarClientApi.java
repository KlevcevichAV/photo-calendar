package com.klevtcevichav.photocalendar.calendar.client;

import com.klevtcevichav.photocalendar.calendar.dto.response.CalendarResponseDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.DayResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient("calendar")
public interface CalendarClientApi {

    @GetMapping
    ResponseEntity<CalendarResponseDTO> getCalendar(@RequestParam Long accountId,
                                                    @RequestParam LocalDate from,
                                                    @RequestParam LocalDate to);

    @GetMapping("/{day}")
    ResponseEntity<DayResponseDTO> getDay(@PathVariable LocalDate day,
                                          @RequestParam Long accountId,
                                          @RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "10") int size);
}
