package Challengesemester2024.businessProcess.facade.service;

import Challengesemester2024.businessProcess.auth.dto.auth.SignUpDto;
import Challengesemester2024.businessProcess.facade.dto.RequestUpdateGreetingOrRouteInfoDto;
import Challengesemester2024.businessProcess.facade.dto.ResponseGerGreetingsandRouteInfoDto;
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
    ResponseGerGreetingsandRouteInfoDto getGreetinsandRouteInfo();
}

