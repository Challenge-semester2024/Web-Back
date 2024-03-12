package Challengesemester2024.domain.facility.facade;

import Challengesemester2024.SpringSecurity.authentication.AuthenticatedEmailDTO;
import Challengesemester2024.SpringSecurity.authentication.SecurityUtils;
import Challengesemester2024.domain.facility.dto.FacilityFloorSizeUpdateRequest;
import Challengesemester2024.domain.facility.dto.FloorPictureListUpdateRequest;
import Challengesemester2024.domain.facility.facilityIntroduction.service.FacilityService;
import Challengesemester2024.domain.facility.floorPictureCluster.service.FloorPictureClusterService;
import Challengesemester2024.domain.facility.floorPicutre.dto.UpdateFloorPictureDto;
import Challengesemester2024.domain.facility.floorPicutre.service.FloorPictureService;
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
    private final FloorPictureService floorPictureService;
    private final FloorPictureClusterService floorPictureClusterService;

    @Override
    @Transactional
    public void updateFloorSize(UpdateFloorSizeDto updateFloorSizeDto) {
        //1. 인증된 이메일 가져오기
        AuthenticatedEmailDTO authenticatedEmailDto = getAuthenticatedEmail();
        //2. 층별규모에 db 업데이트
        FacilityFloorSizeUpdateRequest facilityFloorSizeUpdateRequest = floorService.UpdateFloorSize(updateFloorSizeDto, authenticatedEmailDto);
        //3. 시설db에 양방향 매핑 위한 db 업데이트
        facilityService.updateFacilityFloorSizeList(facilityFloorSizeUpdateRequest);
    }

    @Transactional
    @Override
    public void updateFloorPicture(UpdateFloorPictureDto updateFloorPictureDto) {
        //1. 인증된 이메일 가져오기
        AuthenticatedEmailDTO authenticatedEmailDto = getAuthenticatedEmail();
        //2. 층별사진에 db 업데이트
        FloorPictureListUpdateRequest floorPictureListUpdateRequest = floorPictureService.updateFloorPicture(updateFloorPictureDto, authenticatedEmailDto);
        //3. 층별사진모음 db에 해당 변경사항 업데이트
        floorPictureClusterService.updateFloorPictureList(floorPictureListUpdateRequest);
    }

    private AuthenticatedEmailDTO getAuthenticatedEmail(){
        return securityUtils.getAuthenticationEmail();
    }
}
