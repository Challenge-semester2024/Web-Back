package Challengesemester2024.domain.volunteer.service;

import Challengesemester2024.SpringSecurity.jwt.dto.AllJwtTokenDto;
import Challengesemester2024.businessProcess.auth.app.dto.AppSignInDto;
import Challengesemester2024.businessProcess.auth.app.dto.AppSignUpDto;
import Challengesemester2024.businessProcess.auth.app.dto.CheckExitsVoluteerDto;

public interface VoluteerService {
    void checkExits(CheckExitsVoluteerDto checkExits );
    void signUp(AppSignUpDto appSignUpDto);
    AllJwtTokenDto signIn(AppSignInDto appSignInDto);
}
