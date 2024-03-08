package Challengesemester2024.domain.facility.facilityIntroduction.service;

import Challengesemester2024.config.constant.DbInitConstants;
import Challengesemester2024.domain.facility.floorSize.dto.UpdateFloorSizeDto;
import Challengesemester2024.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import Challengesemester2024.domain.facility.facilityIntroduction.repository.FacilityIntroductionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class FacilityServiceImpl implements FacilityService{
    private final FacilityIntroductionRepository facilityRepository;
    @Override
    public FacilityIntroduction createFacilityIntroduction() {

        FacilityIntroduction facilityIntroduction = FacilityIntroduction.builder()
                .totalArea(DbInitConstants.facilityInitTotalArea)
                .floorSizes(new ArrayList<>())
                .floorPictureClusters(new ArrayList<>())
                .build();

        facilityRepository.save(facilityIntroduction);
        return facilityIntroduction;
    }

    @Override
    public void UpdateFloorSize(UpdateFloorSizeDto updateFloorSizeDto) {
        // 1. SecurityContextHolder에서 인증 정보를 얻어 현재 사용자의 이메일 확인하기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getPrincipal();

        //2. 해당 이메일로 관리자db->보육원db로 외래키를 타고 들어가 보육원 필드에 저장된 시설소개의 기본키 가져오기

        //3. 가져온 기본키와 외부에서 받은 updateFloorSizeDto로 floorPicutre db 저장하기

    }
}
