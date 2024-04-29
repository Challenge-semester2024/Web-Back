package Challengesemester2024.domain.routeInfo.controller;

import Challengesemester2024.Exception.collections.InputValid.BindingErrors;
import Challengesemester2024.businessProcess.facade.dto.RequestUpdateGreetingOrRouteInfoDto;
import Challengesemester2024.businessProcess.facade.service.DatabaseFacadeService;
import Challengesemester2024.config.constant.ControllerConstants;
import Challengesemester2024.domain.routeInfo.dto.UpdateRouteInfoDto;
import Challengesemester2024.domain.routeInfo.service.RouteInfoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/routeinfo")
public class RouteInfoController {
    private final DatabaseFacadeService databaseFacadeService;
    private final RouteInfoService routeInfoService;
    @Transactional
    @PutMapping("update") //인삿말 db 변경 -> 이미 회원가입때 만들어서 다 update라고 치면 됨
    public ResponseEntity<?> updateRouteInfo(@RequestBody @Valid RequestUpdateGreetingOrRouteInfoDto requestUpdateRouteInfoDto,
                                             BindingResult bindingResult )  {
        handleBindingErrors(bindingResult);
        UpdateRouteInfoDto updateRouteInfoDto = databaseFacadeService.updateRouteInfo(requestUpdateRouteInfoDto);
        routeInfoService.updateRouteInfo(updateRouteInfoDto);

        return new ResponseEntity<>(ControllerConstants.completeUpdateRouteInfo, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("get")
    public ResponseEntity<?> getAllRouteInfo(){

        return new ResponseEntity<>(ControllerConstants.completeUpdateGreetings, HttpStatus.OK);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }

}
