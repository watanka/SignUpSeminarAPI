package com.SeminarRegistration.controller.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;


@Getter
@Setter
public class SeminarDTO {
    private ZonedDateTime openDate;
    private long maxRegistrationNum;
    private long currentRegistrationCount;

}
