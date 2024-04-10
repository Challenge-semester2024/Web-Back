package Challengesemester2024.domain.facility.floorSize.service;

import Challengesemester2024.domain.facility.dto.FacilityFloorSizeUpdateRequest;
import Challengesemester2024.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import Challengesemester2024.domain.facility.facilityIntroduction.repository.FacilityIntroRepository;
import Challengesemester2024.domain.facility.floorSize.dto.UpdateFloorSizeDto;
import Challengesemester2024.domain.facility.floorSize.model.FloorSize;
import Challengesemester2024.domain.facility.floorSize.repository.FloorSizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FloorServiceImpl implements FloorSizeService{
    private final FacilityIntroRepository facilityIntroRepository;
    private final FloorSizeRepository floorRepository;

    @Override
    public FacilityFloorSizeUpdateRequest createFloorSize(UpdateFloorSizeDto updateFloorSizeDto, Authentication authentication) {
        //1. 해당 이메일로 관리자db->보육원db로 외래키를 타고 들어가 보육원 필드에 저장된 시설소개의 기본키 가져오기
         FacilityIntroduction facilityIntroduction  = facilityIntroRepository.getFacilityPk(authentication);

        //2. 가져온 기본키와 외부에서 받은 updateFloorSizeDto로 floorPicutre db 저장하기
        FloorSize floorSize = FloorSize.builder()
                .floor(updateFloorSizeDto.getFloor())
                .area(updateFloorSizeDto.getArea())
                .purpose(updateFloorSizeDto.getPurpose())
                .mainRoom(updateFloorSizeDto.getRemark())
                .facilityIntroduction(facilityIntroduction)
                .build();

        //3. 층별db에 내용 저장
        floorRepository.save(floorSize);

        //4. 양방향 매핑 위해 dto 생성
        FacilityFloorSizeUpdateRequest facilityFloorSizeUpdateRequest = FacilityFloorSizeUpdateRequest.builder()
                .facilityIntroduction(facilityIntroduction)
                .floorSize(floorSize)
                .build();

        return facilityFloorSizeUpdateRequest;
    }
}
