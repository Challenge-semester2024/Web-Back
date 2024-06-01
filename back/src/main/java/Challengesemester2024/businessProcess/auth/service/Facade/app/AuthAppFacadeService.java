package Challengesemester2024.businessProcess.auth.service.Facade.app;

import Challengesemester2024.SpringSecurity.jwt.dto.AllJwtTokenDto;
import Challengesemester2024.businessProcess.auth.app.dto.AppSignInDto;
import Challengesemester2024.businessProcess.auth.app.dto.AppSignUpDto;

public interface AuthAppFacadeService {
    void authSignUp(AppSignUpDto appSignUpDto);
    AllJwtTokenDto authSignIn (AppSignInDto appSignInDto);
}
