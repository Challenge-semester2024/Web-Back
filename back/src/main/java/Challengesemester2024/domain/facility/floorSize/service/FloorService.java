package Challengesemester2024.domain.facility.floorSize.service;

import Challengesemester2024.SpringSecurity.authentication.AuthenticatedEmailDTO;
import Challengesemester2024.domain.facility.dto.FacilityFloorSizeUpdateRequest;
import Challengesemester2024.domain.facility.floorSize.dto.UpdateFloorSizeDto;

public interface FloorService {
    FacilityFloorSizeUpdateRequest UpdateFloorSize(UpdateFloorSizeDto updateFloorSizeDto, AuthenticatedEmailDTO authenticatedEmailDto);
}
