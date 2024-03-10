package Challengesemester2024.domain.facility.facade;

import Challengesemester2024.SpringSecurity.authentication.AuthenticatedEmailDTO;
import Challengesemester2024.SpringSecurity.authentication.SecurityUtils;
import Challengesemester2024.domain.facility.dto.FacilityFloorSizeUpdateRequest;
import Challengesemester2024.domain.facility.facilityIntroduction.service.FacilityService;
import Challengesemester2024.domain.facility.floorSize.dto.UpdateFloorSizeDto;
import Challengesemester2024.domain.facility.floorSize.service.FloorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacilityFacadeServiceImpl implements FacilityFacadeService{
    private final SecurityUtils securityUtils;
    private final FacilityService facilityService;
    private final FloorService floorService;
    @Override
    @Transactional
    public void updateFloorSize(UpdateFloorSizeDto updateFloorSizeDto) {

        //1. 인증된 이메일 가져오기
        AuthenticatedEmailDTO authenticatedEmailDto = securityUtils.getAuthenticationEmail();
        //2. 층별규모에 db 업데이트
        FacilityFloorSizeUpdateRequest facilityFloorSizeUpdateRequest = floorService.UpdateFloorSize(updateFloorSizeDto, authenticatedEmailDto);
        //3. 시설db에 양방향 매핑 위한 db 업데이트
        facilityService.updateFacilityFloorSizeList(facilityFloorSizeUpdateRequest);

    }
}
