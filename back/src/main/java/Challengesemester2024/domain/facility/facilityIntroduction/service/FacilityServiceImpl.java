package Challengesemester2024.domain.facility.facilityIntroduction.service;

import Challengesemester2024.config.constant.DbInitConstants;
import Challengesemester2024.domain.facility.dto.FacilityFloorSizeUpdateRequest;
import Challengesemester2024.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import Challengesemester2024.domain.facility.facilityIntroduction.repository.FacilityIntroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class FacilityServiceImpl implements FacilityService{
    private final FacilityIntroRepository facilityRepository;
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
    public void updateFacilityFloorSizeList(FacilityFloorSizeUpdateRequest facilityFloorSizeUpdateRequest) {
        facilityRepository.updateFacilityFloorSizeList
                (facilityFloorSizeUpdateRequest.getFacilityIntroduction(),
                facilityFloorSizeUpdateRequest.getFloorSize());
    }


}
