package Challengesemester2024.businessProcess.facade.service;

import Challengesemester2024.businessProcess.auth.dto.auth.SignUpDto;
import Challengesemester2024.businessProcess.facade.dto.request.RequestUpdateGreetingOrRouteInfoDto;
import Challengesemester2024.businessProcess.facade.dto.response.ResponseAddressAndRouteInfoDto;
import Challengesemester2024.businessProcess.facade.dto.response.ResponseGetFloorSizeAndPictureCluster;
import Challengesemester2024.businessProcess.facade.dto.response.ResponseGetGreetingsAndYearHistoryDto;
import Challengesemester2024.domain.facility.dto.CreateDbWhenUpdateFloorPictureDto;
import Challengesemester2024.domain.facility.floorPicutre.dto.FloorPictureDto;
import Challengesemester2024.domain.routeInfo.dto.UpdateRouteInfoDto;
import org.springframework.security.core.Authentication;

import java.io.IOException;

public interface DatabaseFacadeService {
    void createDbWhenSignUp(SignUpDto signUpDto, String s3Url) throws IOException;
    void createDbWhenUpdateFloorPicute (CreateDbWhenUpdateFloorPictureDto createDbDto);
    void updateDbWhenModifyFloorPicture (FloorPictureDto find, Authentication authentication);
    void updateGreetings(RequestUpdateGreetingOrRouteInfoDto GreetingDto) throws IOException;
    UpdateRouteInfoDto updateRouteInfo(RequestUpdateGreetingOrRouteInfoDto routeInfoDto);
    ResponseGetGreetingsAndYearHistoryDto getGreetinsandYearHistory();
    ResponseAddressAndRouteInfoDto getAddressAndRouteInfo();
    ResponseGetFloorSizeAndPictureCluster getFailciltySizeAndFicture();

}

