package Challengesemester2024.domain.manager.service;

import Challengesemester2024.SpringSecurity.jwt.dto.AllJwtTokenDto;
import Challengesemester2024.businessProcess.auth.web.dto.WebSignUpDto;
import Challengesemester2024.businessProcess.facade.dto.ManagerRegisterDto;
import Challengesemester2024.businessProcess.auth.web.dto.WebSignInDto;

public interface ManagerService {
    void checkExits(WebSignUpDto.ceoInfo manager);
    void register(WebSignUpDto.ceoInfo manager, ManagerRegisterDto managerRegisterDto);
    AllJwtTokenDto signIn(WebSignInDto webSignInDto);
}
