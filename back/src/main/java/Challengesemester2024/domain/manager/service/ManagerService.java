package Challengesemester2024.domain.manager.service;

import Challengesemester2024.SpringSecurity.jwt.dto.AllJwtTokenDto;
import Challengesemester2024.businessProcess.facade.dto.ManagerRegisterDto;
import Challengesemester2024.businessProcess.auth.web.dto.auth.SignInDto;
import Challengesemester2024.businessProcess.auth.web.dto.auth.SignUpDto;

public interface ManagerService {
    public void checkExits(SignUpDto.ceoInfo manager);
    public void register(SignUpDto.ceoInfo manager, ManagerRegisterDto managerRegisterDto);
    public AllJwtTokenDto signIn(SignInDto signInDto);
}
