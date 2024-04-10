package Challengesemester2024.domain.childCenter.service;

import Challengesemester2024.businessProcess.facade.dto.CenterForeignKeyDto;
import Challengesemester2024.businessProcess.facade.dto.ManagerRegisterDto;
import Challengesemester2024.businessProcess.auth.dto.auth.SignUpDto;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import org.springframework.security.core.Authentication;

public interface ChildCenterService {
    public ManagerRegisterDto register(SignUpDto.centerInfo childCenter, CenterForeignKeyDto centerForeignKeyDto, String s3url);
    public void checkExits(SignUpDto.centerInfo childCenter);
    ChildCenter getChildCenterPk(Authentication authentication);
}
