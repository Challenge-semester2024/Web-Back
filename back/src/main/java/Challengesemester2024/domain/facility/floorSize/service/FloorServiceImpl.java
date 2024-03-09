package Challengesemester2024.domain.facility.floorSize.service;

import Challengesemester2024.SpringSecurity.authentication.AuthenticatedEmailDTO;
import Challengesemester2024.SpringSecurity.authentication.SecurityUtils;
import Challengesemester2024.domain.facility.facilityIntroduction.dto.GetFacilityIntroPKDto;
import Challengesemester2024.domain.facility.facilityIntroduction.repository.FacilityIntroRepository;
import Challengesemester2024.domain.facility.floorSize.dto.UpdateFloorSizeDto;
import Challengesemester2024.domain.facility.floorSize.model.FloorSize;
import Challengesemester2024.domain.facility.floorSize.repository.FloorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FloorServiceImpl implements FloorService{
    private final SecurityUtils securityUtils;
    private final FacilityIntroRepository facilityIntroRepository;
    private final FloorRepository floorRepository;
    @Override
    public void UpdateFloorSize(UpdateFloorSizeDto updateFloorSizeDto) {
        // 1. SecurityContextHolder에서 인증 정보를 얻어 현재 사용자의 이메일 확인하기
        AuthenticatedEmailDTO authenticatedEmailDTO = securityUtils.getAuthenticationEmail();

        //2. 해당 이메일로 관리자db->보육원db로 외래키를 타고 들어가 보육원 필드에 저장된 시설소개의 기본키 가져오기
        GetFacilityIntroPKDto getFacilityIntroPKDto = facilityIntroRepository.getFacilityPk(authenticatedEmailDTO.getEmail());

        //3. 가져온 기본키와 외부에서 받은 updateFloorSizeDto로 floorPicutre db 저장하기
        FloorSize floorSize = FloorSize.builder()
                .floor(updateFloorSizeDto.getFloor())
                .area(updateFloorSizeDto.getArea())
                .purpose(updateFloorSizeDto.getPurpose())
                .mainRoom(updateFloorSizeDto.getMainRoom())
                .facilityIntroduction(getFacilityIntroPKDto.getFacilityIntroduction())
                .build();

        floorRepository.save(floorSize);
    }
}
