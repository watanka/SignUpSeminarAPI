package com.signUpSeminar.domain;

public record Registration (
        long registrationId,
        long userId,
        long seminarId
){
}
