package Challengesemester2024.domain.childCenter.service;

import Challengesemester2024.businessProcess.facade.dto.CenterForeignKeyDto;
import Challengesemester2024.businessProcess.facade.dto.ManagerRegisterDto;
import Challengesemester2024.businessProcess.auth.dto.auth.S3urlDto;
import Challengesemester2024.businessProcess.auth.dto.auth.SignUpDto;

public interface ChildCenterService {
    public ManagerRegisterDto register(SignUpDto.centerInfo childCenter, CenterForeignKeyDto centerForeignKeyDto, S3urlDto s3urlDto);
    public void checkExits(SignUpDto.centerInfo childCenter);
}
