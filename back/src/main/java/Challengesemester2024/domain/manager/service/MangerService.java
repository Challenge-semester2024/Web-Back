package Challengesemester2024.domain.manager.service;

import Challengesemester2024.SpringSecurity.jwt.dto.AllJwtTokenDto;
import Challengesemester2024.businessProcess.auth.dto.SignInDto;
import Challengesemester2024.businessProcess.auth.dto.SignUpDto;

public interface MangerService {
    public void checkExits(SignUpDto.Manager manager);
    public void register(SignUpDto.Manager manager);
    public AllJwtTokenDto signIn(SignInDto signInDto);
}
