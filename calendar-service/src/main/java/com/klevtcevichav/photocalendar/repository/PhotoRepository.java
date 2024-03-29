package com.klevtcevichav.photocalendar.repository;

import com.klevtcevichav.photocalendar.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Photo> findAllByAccountIdAndDateOfCreationPhotoGreaterThanEqualAndDateOfCreationPhotoLessThanAndDateOfDeleteNull(Long accountId, LocalDate startDate, LocalDate finishDate);
    List<Photo> findAllByAccountIdAndDateOfCreationPhotoAndDateOfDeleteNull(Long accountId, LocalDate dateOfCreationPhoto);
}
