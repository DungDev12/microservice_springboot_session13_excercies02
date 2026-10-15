package com.exam.identityservice.dto.response;

public record AuthRegisterResponse(
        String username,
        String role
) {
}
