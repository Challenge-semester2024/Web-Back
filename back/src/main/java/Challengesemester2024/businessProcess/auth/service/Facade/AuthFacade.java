package Challengesemester2024.businessProcess.auth.service.Facade;

import Challengesemester2024.SpringSecurity.jwt.dto.AllJwtTokenDto;
import Challengesemester2024.businessProcess.auth.dto.SignInDto;
import Challengesemester2024.businessProcess.auth.dto.SignUpDto;
import org.springframework.web.multipart.MultipartFile;

public interface AuthFacade {
    void authSignup(SignUpDto signUpDto, MultipartFile multipartFile);
    AllJwtTokenDto authSignIn(SignInDto signDto);
}
