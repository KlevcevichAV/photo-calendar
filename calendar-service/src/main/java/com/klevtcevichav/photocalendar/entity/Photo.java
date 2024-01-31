package com.klevtcevichav.photocalendar.entity;

import com.klevtcevichav.photocalendar.core.entity.FullAbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldNameConstants
public class Photo extends FullAbstractEntity {

    @Column
    private Long accountId;
    @Column
    private String fileName;
    @Column
    private UUID key;
    @Column
    private LocalDate dateOfCreationPhoto;
//    if photo hasn't location then location is null, it's not important
    @Column
    private String location;


//    maybe enumCHIK ;0 need think about it
//    private List<String> tags;
//    ids people how has in the photo by default list is empty ;0
//    private List<String> people;
}
