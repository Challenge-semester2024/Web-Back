package Challengesemester2024.SpringSecurity.jwt.controller;

import Challengesemester2024.SpringSecurity.jwt.dto.AccessTokenDto;
import Challengesemester2024.SpringSecurity.jwt.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class JwtController {
    private final JwtService jwtService;

    @PostMapping("/update/AccessToken")
    public AccessTokenDto updateAccessToken(HttpServletRequest request){
        return jwtService.updateAccessToken(request);
    }


}
