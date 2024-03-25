package com.SeminarRegistration.seminarRegistration;

public record ErrorResponse(
        String code,
        String message
) {
}
