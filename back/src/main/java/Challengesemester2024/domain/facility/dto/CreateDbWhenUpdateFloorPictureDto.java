package Challengesemester2024.domain.facility.dto;

import Challengesemester2024.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import Challengesemester2024.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import Challengesemester2024.domain.facility.floorPicutre.dto.FloorPictureDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateDbWhenUpdateFloorPictureDto {
    FloorPictureDto floorPictureDto;
    FloorPictureCluster floorPictureCluster;
    String email;
    FacilityIntroduction facilityIntroduction;
}
