package com.klevtcevichav.photocalendar.core.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractDTO implements Serializable {

}
