package Challengesemester2024.SpringSecurity.jwt.service;

import Challengesemester2024.SpringSecurity.jwt.dto.AccessTokenDto;
import jakarta.servlet.http.HttpServletRequest;

public interface JwtService {
    AccessTokenDto updateAccessToken(HttpServletRequest request);
}
