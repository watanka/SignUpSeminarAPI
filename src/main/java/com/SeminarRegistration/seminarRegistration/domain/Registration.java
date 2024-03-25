package com.SeminarRegistration.seminarRegistration.domain;

public record Registration (
        long registrationId,
        long userId,
        long seminarId
){
}
