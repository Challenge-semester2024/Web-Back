package Challengesemester2024.domain.facility.facilityIntroduction.controller;

import Challengesemester2024.Exception.collections.InputValid.BindingErrors;
import Challengesemester2024.domain.facility.floorSize.dto.UpdateFloorSizeDto;
import Challengesemester2024.domain.facility.facilityIntroduction.service.FacilityService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/facility")
@RequiredArgsConstructor
@Slf4j
public class FacilityIntroductionController {
    private final FacilityService facilityService;

    @Transactional
    @PostMapping("/update/floorSize")
    public ResponseEntity<?> updateFloorSize(@RequestBody @Valid UpdateFloorSizeDto updateFloorSizeDto, BindingResult bindingResult){
        handleBindingErrors(bindingResult);
        facilityService.UpdateFloorSize(updateFloorSizeDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }

}
