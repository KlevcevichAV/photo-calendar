package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.calendar.dto.response.CalendarResponseDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.DayResponseDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.PhotoResponseDTO;
import com.klevtcevichav.photocalendar.entity.Photo;
import com.klevtcevichav.photocalendar.exception.CalendarBusinessException;
import com.klevtcevichav.photocalendar.repository.PhotoRepository;
import com.klevtcevichav.photocalendar.s3.S3Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CalendarServiceImpl implements CalendarService{

    private final S3Service s3Service;
    private final PhotoRepository photoRepository;

    @Override
    public CalendarResponseDTO getCalendar(Long accountId, LocalDate from, LocalDate to) {

        log.info("Start finding calendar with account id: {}.From {} to {}", accountId, from, to);
//        check existAccountById(id).orElseThrow(() -> new NotFoundException("Not found account with id: %s".format(req.getId()))

        if (from.getYear() != to.getYear()) {
            throw new CalendarBusinessException("This period more than 1 year! Please shorten the period!");
        }

        List<Photo> photos = photoRepository.findAllByAccountIdAndDateOfCreationPhotoGreaterThanEqualAndDateOfCreationPhotoLessThan(
                accountId,
                from,
                to);

        log.info("Calendar found and start building response for calendar: {}", photos);
        return CalendarResponseDTO
                .builder()
                .year((long) from.getYear())
                .month(from.getMonth().equals(to.getMonth()) ? from.getMonth() : null)
                .days(buildDaysResponseList(from, to, photos))
                .build();
    }

    @Override
    public DayResponseDTO getDay(Long accountId, LocalDate day) {

        log.info("Start finding photos for date:{}  with account id: {}",
                accountId, day);
//        check existAccountById(id).orElseThrow(() -> new NotFoundException("Not found account with id: %s".format(req.getId()))

        List<Photo> photos = photoRepository.findAllByAccountIdAndDateOfCreationPhoto(
                accountId,
                day);

        log.info("Photos for date found and start building response: {}", photos);
        return DayResponseDTO
                .builder()
                .date(day)
                .photos(buildPhotoResponseList(photos, true))
                .build();
    }

    private List<DayResponseDTO> buildDaysResponseList(LocalDate startDate, LocalDate finishDate, List<Photo> photos) {

        log.info("Start building list day response for photos: {}", photos);
        List<DayResponseDTO> response = new ArrayList<>();

        for (LocalDate dayIterator = startDate; dayIterator.isBefore(finishDate); dayIterator = dayIterator.plusDays(1)) {

            LocalDate finalDayIterator = dayIterator;
            List<Photo> photoListForDayIterator = photos.stream().filter(photo -> photo.getDateOfCreationPhoto().equals(finalDayIterator)).collect(Collectors.toList());
            response.add(DayResponseDTO
                    .builder()
                    .date(finalDayIterator)
                    .photos(buildPhotoResponseList(photoListForDayIterator, false))
                    .build());
        }
        log.info("List DayResponse built: {}", response);
        return response;
    }

    private List<PhotoResponseDTO> buildPhotoResponseList(List<Photo> photos, boolean isGetDay) {

        List<PhotoResponseDTO> photoResponseDTOS = new ArrayList<>();

        if(photos.isEmpty()) {
            log.info("List photo is empty!");
            return photoResponseDTOS;
        }

        log.info("Start building list photo for day: {}", photos.get(0).getDateOfCreationPhoto());
        for (Photo photo : photos) {
            photoResponseDTOS.add(PhotoResponseDTO
                    .builder()
                    .location(photo.getLocation())
                    .dateOfCreationPhoto(photo.getDateOfCreationPhoto())
                    .accountId(photo.getAccountId())
                    .fileName(photo.getFileName())
                    .photo((photoResponseDTOS.size() == 0 || isGetDay) ? s3Service.getObject(photo.getKey().toString()) : null)
                    .build());
        }
        log.info("List list photo for day: {} built: {}", photos.get(0).getDateOfCreationPhoto(), photoResponseDTOS);
        return photoResponseDTOS;
    }

}
