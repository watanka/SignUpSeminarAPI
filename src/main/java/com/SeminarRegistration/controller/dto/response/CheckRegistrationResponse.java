package com.SeminarRegistration.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CheckRegistrationResponse {

    private String userId;
    private Long seminarId;
    boolean registerResult;

}
