package Challengesemester2024.domain.facility.dto;

import Challengesemester2024.domain.facility.facilityIntroduction.dto.GetFacilityIntroPKDto;
import Challengesemester2024.domain.facility.floorSize.model.FloorSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class FacilityFloorSizeUpdateRequest {
    GetFacilityIntroPKDto facilityIntroPKDto;
    FloorSize floorSize;
}
