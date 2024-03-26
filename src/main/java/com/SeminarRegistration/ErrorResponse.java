package com.SeminarRegistration;

public record ErrorResponse(
        String code,
        String message
) {
}
