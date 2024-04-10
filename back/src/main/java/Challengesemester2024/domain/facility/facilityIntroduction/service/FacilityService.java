package Challengesemester2024.domain.facility.facilityIntroduction.service;

import Challengesemester2024.domain.facility.dto.FacilityFloorSizeUpdateRequest;
import Challengesemester2024.domain.facility.facilityIntroduction.model.FacilityIntroduction;

public interface FacilityService {
    FacilityIntroduction createFacilityIntroduction();
    void updateFacilityFloorSizeList(FacilityFloorSizeUpdateRequest facilityFloorSizeUpdateRequest);
}
