package Challengesemester2024.domain.greetings.controller;

import Challengesemester2024.Exception.collections.InputValid.BindingErrors;
import Challengesemester2024.businessProcess.facade.dto.RequestUpdateGreetingOrRouteInfoDto;
import Challengesemester2024.businessProcess.facade.dto.ResponseGerGreetingsandRouteInfoDto;
import Challengesemester2024.businessProcess.facade.service.DatabaseFacadeService;
import Challengesemester2024.config.constant.ControllerConstants;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/greeting")
public class GreetingController {
    private final DatabaseFacadeService databaseFacadeService;

    @Transactional
    @PutMapping("update") //인삿말 db 변경 -> 이미 회원가입때 만들어서 다 update라고 치면 됨
    public ResponseEntity<?> updateGreeting(@RequestBody @Valid  RequestUpdateGreetingOrRouteInfoDto requestUpdateGreetingDto, BindingResult bindingResult) throws IOException {
        handleBindingErrors(bindingResult);

        databaseFacadeService.updateGreetings(requestUpdateGreetingDto);

        return new ResponseEntity<>(ControllerConstants.completeUpdateGreetings, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("get/greeting/and/year")
    public ResponseEntity<?> getAllGreetingsAndYearHistory() {
        ResponseGerGreetingsandRouteInfoDto responseGerGreetingsandRouteInfoDto = databaseFacadeService.getGreetinsandRouteInfo();
        return ResponseEntity.ok(responseGerGreetingsandRouteInfoDto);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }

}
