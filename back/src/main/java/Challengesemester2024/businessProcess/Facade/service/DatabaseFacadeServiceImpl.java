package Challengesemester2024.businessProcess.Facade.service;

import Challengesemester2024.businessProcess.Facade.dto.CenterForeignKeyDto;
import Challengesemester2024.businessProcess.Facade.dto.ManagerRegisterDto;
import Challengesemester2024.businessProcess.auth.dto.auth.S3urlDto;
import Challengesemester2024.businessProcess.auth.dto.auth.SignUpDto;
import Challengesemester2024.domain.childCenter.service.ChildCenterService;
import Challengesemester2024.domain.facilityIntroduction.model.FacilityIntroduction;
import Challengesemester2024.domain.facilityIntroduction.service.FacilityService;
import Challengesemester2024.domain.greetings.domain.Greetings;
import Challengesemester2024.domain.greetings.service.GreetingsService;
import Challengesemester2024.domain.manager.service.ManagerService;
import Challengesemester2024.domain.routeInfo.domain.RouteInfo;
import Challengesemester2024.domain.routeInfo.service.RouteInfoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DatabaseFacadeServiceImpl implements DatabaseFacadeService{
    private final RouteInfoService routeInfoService;
    private final GreetingsService greetingsService;
    private final FacilityService facilityService;
    private final ManagerService managerService;
    private final ChildCenterService childCenterService;
    @Override
    @Transactional
    public void createDbAndRelationsWhenSignUp(SignUpDto signUpDto, S3urlDto s3urlDto) throws IOException {
        //1. 찾아오는 길 db 생성
        RouteInfo routeInfo = routeInfoService.createRouteInfo();
        //2. 인삿말 db 생성
        Greetings greetings = greetingsService.createGreetings();
        //3. 시설소개 db 생성
        FacilityIntroduction facility = facilityService.createFacilityIntroduction();

        //4. Center db 생성 위해, foreignKey로 담길 db들 하나의 dto로 묶기
        CenterForeignKeyDto centerForeignKeyDto = CenterForeignKeyDto.builder()
                .facility(facility)
                .greetings(greetings)
                .routeInfo(routeInfo)
                .build();

        //5. ChildCenter db 생성 및 관계 설정
        ManagerRegisterDto managerRegisterDto = childCenterService.register(signUpDto.getCenterInfo(), centerForeignKeyDto, s3urlDto);

        //6. manager db 생성 및 관계 설정
        managerService.register(signUpDto.getCeoInfo(), managerRegisterDto);
    }

}
