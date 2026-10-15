package com.exam.identityservice.dto.request;

public record RegisterUserRequest(
        String username,
        String password,
        String role
) {
}
