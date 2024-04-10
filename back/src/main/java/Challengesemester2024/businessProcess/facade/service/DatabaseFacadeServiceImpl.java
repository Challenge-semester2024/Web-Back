package Challengesemester2024.businessProcess.facade.service;

import Challengesemester2024.SpringSecurity.authentication.SecurityUtils;
import Challengesemester2024.businessProcess.facade.dto.CenterForeignKeyDto;
import Challengesemester2024.businessProcess.facade.dto.ManagerRegisterDto;
import Challengesemester2024.businessProcess.auth.dto.auth.SignUpDto;
import Challengesemester2024.businessProcess.facade.dto.RequestUpdateGreetingOrRouteInfoDto;
import Challengesemester2024.businessProcess.facade.dto.ResponseGerGreetingsandRouteInfoDto;
import Challengesemester2024.businessProcess.s3.S3Service;
import Challengesemester2024.businessProcess.util.UtilService;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import Challengesemester2024.domain.childCenter.service.ChildCenterService;
import Challengesemester2024.domain.facility.dto.CreateDbWhenUpdateFloorPictureDto;
import Challengesemester2024.domain.facility.dto.FloorPictureListUpdateRequest;
import Challengesemester2024.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import Challengesemester2024.domain.facility.facilityIntroduction.service.FacilityService;
import Challengesemester2024.domain.facility.floorPictureCluster.service.FloorPictureClusterService;
import Challengesemester2024.domain.facility.floorPicutre.dto.FloorPictureDto;
import Challengesemester2024.domain.facility.floorPicutre.service.FloorPictureService;
import Challengesemester2024.domain.greetings.domain.Greetings;
import Challengesemester2024.domain.greetings.service.GreetingsService;
import Challengesemester2024.domain.manager.service.ManagerService;
import Challengesemester2024.domain.routeInfo.domain.RouteInfo;
import Challengesemester2024.domain.routeInfo.dto.UpdateRouteInfoDto;
import Challengesemester2024.domain.routeInfo.service.RouteInfoService;
import Challengesemester2024.domain.yearHistory.model.DecadeYear;
import Challengesemester2024.domain.yearHistory.service.decadeYear.DecadeYearService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseFacadeServiceImpl implements DatabaseFacadeService{
    private final RouteInfoService routeInfoService;
    private final GreetingsService greetingsService;
    private final FacilityService facilityService;
    private final ManagerService managerService;
    private final ChildCenterService childCenterService;
    private final FloorPictureClusterService floorPictureClusterService;
    private final FloorPictureService floorPictureService;
    private final SecurityUtils securityUtils;
    private final S3Service s3Service;
    private final DecadeYearService decadeYearService;
    private final UtilService utilService;

    @Override
    @Transactional
    public void createDbWhenSignUp(SignUpDto signUpDto, String s3urlDto) throws IOException {
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

    @Override
    public void createDbWhenUpdateFloorPicute(CreateDbWhenUpdateFloorPictureDto createDbDto) {
        // 1번. 해당 층에 대한 새로운 floorPictureCluster db 생성.
        floorPictureClusterService.createFloorPictureCluster(createDbDto.getFacilityIntroduction(), createDbDto.getFloorPictureDto().getFloor());
        // 2번. floorPicutureDb도 생성해서 FloorPictureDto find의 정보를 db에 넣어줌. + 1번에서 만들어준 floorPictureCluster db의 pk값을 외래키로 설정
        FloorPictureListUpdateRequest floorPictureListUpdateRequest = floorPictureService.createFloorPicture(createDbDto.getFloorPictureDto(), createDbDto.getAuthentication());
        // 3번. 1번에서 만들어준 floorPictureCluster 내부의 floorPictureList에 2번에서 만들어준 floorPicuture를 넣어줌
        floorPictureClusterService.createFloorPictureList(floorPictureListUpdateRequest);
    }

    @Override
    public void updateDbWhenModifyFloorPicture( FloorPictureDto floorPictureDto ) {
        floorPictureService.updateFloorPicture(floorPictureDto);
    }

    @Override
    public void updateGreetings(MultipartFile file, RequestUpdateGreetingOrRouteInfoDto requestUpdateGreetingDto) throws IOException {
        ChildCenter fechedChildCenter = getChildCenterPk();

        Greetings excitingGreeting = fechedChildCenter.getGreetings();

        String imageUrl;

        if(!file.isEmpty()){
            imageUrl = s3Service.uploadImageToS3(file);
        } else {
            imageUrl = utilService.getInitImagePath();
        }


        excitingGreeting.update(imageUrl, requestUpdateGreetingDto.getMemo());

    }

    @Override
    public UpdateRouteInfoDto updateRouteInfo(RequestUpdateGreetingOrRouteInfoDto routeInfoDto) {
        ChildCenter fechedChildCenter = getChildCenterPk();

        RouteInfo oldRouteInfo =  fechedChildCenter.getRouteInfo();

        RouteInfo newRouteInfo = RouteInfo.builder()
                .memo(routeInfoDto.getMemo())
                .build();

        UpdateRouteInfoDto updateRouteInfoDto = UpdateRouteInfoDto.builder()
                .oldRouteInfo(oldRouteInfo)
                .newRouteInf0(newRouteInfo)
                .build();

        return updateRouteInfoDto;
    }

    @Override
    public ResponseGerGreetingsandRouteInfoDto getGreetinsandRouteInfo() {

        ChildCenter fechedChildCenter = getChildCenterPk();
        List<DecadeYear> decadeYearList = decadeYearService.findAllDecadeYearDesc();

        ResponseGerGreetingsandRouteInfoDto responseGerGreetingsandRouteInfo = ResponseGerGreetingsandRouteInfoDto.builder()
                .greeting(fechedChildCenter.getGreetings())
                .decadeYearList(decadeYearList)
                .build();

        return responseGerGreetingsandRouteInfo;
    }

    private ChildCenter getChildCenterPk(){
        Authentication authentication = securityUtils.getAuthenticationEmail();
        return childCenterService.getChildCenterPk(authentication);
    }


}
