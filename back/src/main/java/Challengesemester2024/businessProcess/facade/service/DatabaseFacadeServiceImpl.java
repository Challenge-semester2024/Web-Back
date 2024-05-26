package Challengesemester2024.businessProcess.facade.service;

import Challengesemester2024.SpringSecurity.authentication.SecurityUtils;
import Challengesemester2024.businessProcess.facade.dto.*;
import Challengesemester2024.businessProcess.auth.web.dto.auth.SignUpDto;
import Challengesemester2024.businessProcess.facade.dto.request.RequestUpdateGreetingOrRouteInfoDto;
import Challengesemester2024.businessProcess.facade.dto.response.ResponseAddressAndRouteInfoDto;
import Challengesemester2024.businessProcess.facade.dto.response.ResponseGetFloorSizeAndPictureCluster;
import Challengesemester2024.businessProcess.facade.dto.response.ResponseGetGreetingsAndYearHistoryDto;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import Challengesemester2024.domain.childCenter.service.ChildCenterService;
import Challengesemester2024.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import Challengesemester2024.domain.facility.facilityIntroduction.service.FacilityService;
import Challengesemester2024.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import Challengesemester2024.domain.facility.floorPictureCluster.service.FloorPictureClusterService;
import Challengesemester2024.domain.facility.floorSize.model.FloorSize;
import Challengesemester2024.domain.facility.floorSize.service.FloorSizeService;
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
    private final SecurityUtils securityUtils;
    private final DecadeYearService decadeYearService;
    private final FloorSizeService floorSizeService;

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

//    @Override
//    public void createDbWhenUpdateFloorPicute(CreateDbWhenUpdateFloorPictureDto createDbDto) {
//        // 1번. 해당 층에 대한 새로운 floorPictureCluster db 생성.
//        floorPictureClusterService.createFloorPictureCluster(createDbDto.getFacilityIntroduction(), createDbDto.getFloorPictureDto().getFloor());
//        // 2번. floorPicutureDb도 생성해서 FloorPictureDto find의 정보를 db에 넣어줌. + 1번에서 만들어준 floorPictureCluster db의 pk값을 외래키로 설정
//        FloorPictureListUpdateRequest floorPictureListUpdateRequest = floorPictureService.createFloorPicture(createDbDto.getFloorPictureDto(), createDbDto.getAuthentication());
//        // 3번. 1번에서 만들어준 floorPictureCluster 내부의 floorPictureList에 2번에서 만들어준 floorPicuture를 넣어줌
//        floorPictureClusterService.createFloorPictureList(floorPictureListUpdateRequest);
//    }

//    @Override
//    public void updateDbWhenModifyFloorPicture ( FloorPictureDto floorPictureDto, Authentication authentication ) {
//        //기존 객체 삭제 및 수정
//        FloorPictureListUpdateRequest updateFloorPicture = floorPictureService.updateFloorPicture(floorPictureDto, authentication);
//        //floorCluster에 재연결
//        floorPictureClusterService.createFloorPictureList(updateFloorPicture);
//    }

    @Override
    public void updateGreetings(RequestUpdateGreetingOrRouteInfoDto requestUpdateGreetingDto) {
        ChildCenter fechedChildCenter = getChildCenterPk();
        Greetings excitingGreeting = fechedChildCenter.getGreetings();
        excitingGreeting.update(requestUpdateGreetingDto.getMemo());
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
    public ResponseGetGreetingsAndYearHistoryDto getGreetinsandYearHistory() {

        ChildCenter fechedChildCenter = getChildCenterPk();
        List<DecadeYear> decadeYearList = decadeYearService.findAllDecadeYearDesc(fechedChildCenter);

        ResponseGetGreetingsAndYearHistoryDto responseGetGreetingsAndHistoryInfo = ResponseGetGreetingsAndYearHistoryDto.builder()
                .greeting(fechedChildCenter.getGreetings())
                .decadeYearList(decadeYearList)
                .build();

        return responseGetGreetingsAndHistoryInfo;
    }

    @Override
    public ResponseAddressAndRouteInfoDto getAddressAndRouteInfo() {
        ChildCenter fetchedChildCenter = getChildCenterPk();

        ResponseAddressAndRouteInfoDto responseAddressAndRouteInfoDto = ResponseAddressAndRouteInfoDto.builder()
                .Address(fetchedChildCenter.getRoadAddress()+" "+fetchedChildCenter.getDetailAddress())
                .routeInfo(fetchedChildCenter.getRouteInfo())
                .build();

        return responseAddressAndRouteInfoDto;

    }

    @Override
    public ResponseGetFloorSizeAndPictureCluster getFailciltySizeAndFicture() {
        ChildCenter fetchedChildCenter = getChildCenterPk();
        List<FloorSize> floorSizes = floorSizeService.getAllFloorSize(fetchedChildCenter.getFacilityIntroduction());
        List<FloorPictureCluster> floorPictureClusters = floorPictureClusterService.getAllFloorPictureCluster(fetchedChildCenter.getFacilityIntroduction());

        ResponseGetFloorSizeAndPictureCluster response = ResponseGetFloorSizeAndPictureCluster.builder()
                .floorSizeList(floorSizes)
                .floorPictureClusterList(floorPictureClusters)
                .build();

        return response;

    }

    private ChildCenter getChildCenterPk(){
        Authentication authentication = securityUtils.getAuthenticationEmail();
        return childCenterService.getChildCenterPk(authentication);
    }


}
