package Challengesemester2024.domain.facility.floorSize.controller;

import Challengesemester2024.Exception.collections.InputValid.BindingErrors;
import Challengesemester2024.config.constant.ControllerConstants;
import Challengesemester2024.domain.facility.floorSize.dto.UpdateFloorSizeDto;
import Challengesemester2024.domain.facility.floorSize.service.FloorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/facility/floorSize")
public class FloorSizeController {
    private final FloorService floorService;

    @Transactional
    @PostMapping("/update")
    public ResponseEntity<?> updateFloorSize(@RequestBody @Valid UpdateFloorSizeDto updateFloorSizeDto, BindingResult bindingResult){
        handleBindingErrors(bindingResult);
        floorService.UpdateFloorSize(updateFloorSizeDto);
        return new ResponseEntity<>(ControllerConstants.completeUpdateFloorSize, HttpStatus.OK);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }


}
