package Challengesemester2024.domain.facility.floorSize.service;

import Challengesemester2024.domain.facility.dto.FacilityFloorSizeUpdateRequest;
import Challengesemester2024.domain.facility.floorSize.dto.UpdateFloorSizeDto;
import org.springframework.security.core.Authentication;

public interface FloorSizeService {
    FacilityFloorSizeUpdateRequest createFloorSize(UpdateFloorSizeDto updateFloorSizeDto, Authentication authentication);
}
