package Challengesemester2024.domain.facility.floorPicutre.controller;

import Challengesemester2024.Exception.collections.InputValid.BindingErrors;
import Challengesemester2024.config.constant.ControllerConstants;
import Challengesemester2024.domain.facility.facade.FacilityFacadeService;
import Challengesemester2024.domain.facility.floorPicutre.dto.FloorPictureDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/facility/floorPicture")
public class FloorPictureController {
    private final FacilityFacadeService facilityFacadeService;

    @Transactional
    @PostMapping("/create") //층별사진 생성
    public ResponseEntity<?> createFloorPicture(@RequestPart("CreateFloorPictureDto") @Valid List<FloorPictureDto> floorPictureDtoList,
                                                @RequestPart("FloorPictureFile") List<MultipartFile> multipartFile, BindingResult bindingResult){
        handleBindingErrors(bindingResult);
        //컨트롤러가 소수의 서비스 레이어만을 관리하기 위해 파사드 패턴 생성
        facilityFacadeService.createOrUpdateFloorPicture(floorPictureDtoList, multipartFile);

        return new ResponseEntity<>(ControllerConstants.completeUpdateFloorSize, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update") //층별사진 수정
    public ResponseEntity<?> updateFloorPicture(@RequestPart("UpdateFloorPictureDto") @Valid List<FloorPictureDto> floorPictureDtoList,
                                                @RequestPart("FloorPictureFile") List<MultipartFile> multipartFile, BindingResult bindingResult){
        handleBindingErrors(bindingResult);
        //컨트롤러가 소수의 서비스 레이어만을 관리하기 위해 파사드 패턴 생성
        facilityFacadeService.createOrUpdateFloorPicture(floorPictureDtoList, multipartFile);
        return new ResponseEntity<>(ControllerConstants.completeUpdateFloorSize, HttpStatus.OK);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }

}
