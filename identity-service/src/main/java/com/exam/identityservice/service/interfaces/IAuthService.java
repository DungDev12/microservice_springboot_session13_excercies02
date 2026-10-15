package com.exam.identityservice.service.interfaces;

import com.exam.identityservice.dto.request.RegisterUserRequest;
import com.exam.identityservice.dto.response.AuthRegisterResponse;
import com.exam.identityservice.entity.User;

public interface IAuthService {
    AuthRegisterResponse register(RegisterUserRequest request);
}
