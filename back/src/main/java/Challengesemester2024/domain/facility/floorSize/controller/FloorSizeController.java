package Challengesemester2024.domain.facility.floorSize.controller;

import Challengesemester2024.Exception.collections.InputValid.BindingErrors;
import Challengesemester2024.config.constant.ControllerConstants;
import Challengesemester2024.domain.facility.facade.FacilityFacadeService;
import Challengesemester2024.domain.facility.floorSize.dto.UpdateFloorSizeDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/facility/floorSize")
public class FloorSizeController {
    private final FacilityFacadeService facilityFacadeService;

    @PostMapping("/create")
    public ResponseEntity<?> createAndUpdateFloorSize(@RequestBody @Valid List<UpdateFloorSizeDto> updateFloorSizeDtoList, BindingResult bindingResult){
        handleBindingErrors(bindingResult);
        //컨트롤러가 소수의 서비스 레이어만을 관리하기 위해 파사드 패턴 생성
        facilityFacadeService.createOrUpdateFloorSize(updateFloorSizeDtoList);

        return new ResponseEntity<>(ControllerConstants.completeUpdateFloorSize, HttpStatus.OK);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }


}

/*
<파사드 패턴>
* 가독성과 유지보수성 향상: 컨트롤러가 한 개 또는 소수의 서비스만을 호출하게 되므로, 코드의 복잡성이 줄어들고, 각 컴포넌트의 역할이 더 명확해집니다.
재사용성 증가: 특정 비즈니스 로직을 다른 컨트롤러에서도 필요로 할 경우, Facade 서비스를 재사용함으로써 코드의 중복을 줄일 수 있습니다.
변경에 대한 유연성: 내부 서비스의 로직이 변경되더라도, Facade 인터페이스가 동일하다면, 컨트롤러 측의 변경 없이 내부 구현을 수정할 수 있습니다.
* */
