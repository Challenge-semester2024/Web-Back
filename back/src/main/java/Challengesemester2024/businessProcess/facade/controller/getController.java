package Challengesemester2024.businessProcess.facade.controller;

import Challengesemester2024.businessProcess.facade.dto.response.ResponseAddressAndRouteInfoDto;
import Challengesemester2024.businessProcess.facade.dto.response.ResponseGetFloorSizeAndPictureCluster;
import Challengesemester2024.businessProcess.facade.dto.response.ResponseGetGreetingsAndYearHistoryDto;
import Challengesemester2024.businessProcess.facade.service.DatabaseFacadeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/get")
public class getController {
    private final DatabaseFacadeService databaseFacadeService;

    @Transactional
    @GetMapping("/greeting/and/year")
    public ResponseEntity<?> getAllGreetingsAndYearHistory() {
        ResponseGetGreetingsAndYearHistoryDto responseGetGreetingsAndYearHistoryDto = databaseFacadeService.getGreetinsandYearHistory();
        return ResponseEntity.ok(responseGetGreetingsAndYearHistoryDto);
    }

    @Transactional
    @GetMapping("/facility/size/and/ficture")
    public ResponseEntity<?> getAllFacilitySizeAndFicture() {
        ResponseGetFloorSizeAndPictureCluster response = databaseFacadeService.getFailciltySizeAndFicture();
        return ResponseEntity.ok(response);
    }

    @Transactional
    @GetMapping("/address/and/routeinfo")
    public ResponseEntity<?> getAddressAndRouteInfo(){
        ResponseAddressAndRouteInfoDto responseAddressAndRouteInfoDto = databaseFacadeService.getAddressAndRouteInfo();
        return ResponseEntity.ok(responseAddressAndRouteInfoDto);
    }




}
