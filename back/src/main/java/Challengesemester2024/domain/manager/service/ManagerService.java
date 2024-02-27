package Challengesemester2024.domain.manager.service;

import Challengesemester2024.SpringSecurity.jwt.dto.AllJwtTokenDto;
import Challengesemester2024.businessProcess.auth.dto.auth.SignInDto;
import Challengesemester2024.businessProcess.auth.dto.auth.SignUpDto;

public interface ManagerService {
    public void checkExits(SignUpDto.ceoInfo manager);
    public void register(SignUpDto.ceoInfo manager);
    public AllJwtTokenDto signIn(SignInDto signInDto);
}
