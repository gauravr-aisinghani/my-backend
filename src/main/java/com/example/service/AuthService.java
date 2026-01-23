//for driver and transporter login
package com.example.service;

import com.example.dto.LoginRequestDto;
import com.example.dto.LoginResponseDto;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto request);
}

