package Challengesemester2024.domain.childCenter.service;

import Challengesemester2024.businessProcess.auth.web.dto.WebSignUpDto;
import Challengesemester2024.businessProcess.facade.dto.CenterForeignKeyDto;
import Challengesemester2024.businessProcess.facade.dto.ManagerRegisterDto;
import Challengesemester2024.domain.childCenter.dto.put.RequestFindChildCenterDto;
import Challengesemester2024.domain.childCenter.dto.put.ResponseChildCenterDto;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ChildCenterService {
    ManagerRegisterDto register(WebSignUpDto.centerInfo childCenter, CenterForeignKeyDto centerForeignKeyDto, String s3url);
    void checkExits(WebSignUpDto.centerInfo childCenter);
    ChildCenter getChildCenterPk(Authentication authentication);
    List<ResponseChildCenterDto> findChildCenter(RequestFindChildCenterDto requestFindChildCenterDto);
    ChildCenter findById(Long id);
}
