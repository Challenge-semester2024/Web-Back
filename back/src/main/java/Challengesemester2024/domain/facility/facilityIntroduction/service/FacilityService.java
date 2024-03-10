package Challengesemester2024.domain.facility.facilityIntroduction.service;

import Challengesemester2024.domain.facility.dto.FacilityFloorSizeUpdateRequest;
import Challengesemester2024.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import org.springframework.stereotype.Service;

@Service
public interface FacilityService {
    FacilityIntroduction createFacilityIntroduction();
    void updateFacilityFloorSizeList(FacilityFloorSizeUpdateRequest facilityFloorSizeUpdateRequest);
}
