package Challengesemester2024.businessProcess.facade.service;

import Challengesemester2024.businessProcess.auth.web.dto.WebSignUpDto;
import Challengesemester2024.businessProcess.facade.dto.request.RequestUpdateGreetingOrRouteInfoDto;
import Challengesemester2024.businessProcess.facade.dto.response.HomeInAppResponseDto;
import Challengesemester2024.businessProcess.facade.dto.response.ResponseAddressAndRouteInfoDto;
import Challengesemester2024.businessProcess.facade.dto.response.ResponseGetFloorSizeAndPictureCluster;
import Challengesemester2024.businessProcess.facade.dto.response.ResponseGetGreetingsAndYearHistoryDto;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RecruitmentReservationDto;
import Challengesemester2024.domain.center.childCenter.dto.get.ResponseChildCenterFacilityInfoDto;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RecruitmentSummaryDto;
import Challengesemester2024.domain.routeInfo.dto.UpdateRouteInfoDto;

import java.io.IOException;
import java.util.List;

public interface DatabaseFacadeService {
    void createDbWhenSignUp(WebSignUpDto webSignUpDto, String s3Url) throws IOException;
    void updateGreetings(RequestUpdateGreetingOrRouteInfoDto GreetingDto) throws IOException;
    UpdateRouteInfoDto updateRouteInfo(RequestUpdateGreetingOrRouteInfoDto routeInfoDto);
    ResponseGetGreetingsAndYearHistoryDto getGreetingsandYearHistory();
    ResponseAddressAndRouteInfoDto getAddressAndRouteInfo();
    ResponseGetFloorSizeAndPictureCluster getFailciltySizeAndFicture();
    ResponseChildCenterFacilityInfoDto getChildCenterFailcilityInfo(Long childCenterId);
    List<RecruitmentSummaryDto> getChildCenterRecruitmentPreveiw(Long childCenterId);
    RecruitmentReservationDto getRecruitmentReservation(Long recruitmentId);
    HomeInAppResponseDto getHomeInApp();
}

