package Challengesemester2024.domain.facility.dto;

import Challengesemester2024.domain.facility.floorPictureCluster.dto.GetFloorPictureClusterPKDto;
import Challengesemester2024.domain.facility.floorPicutre.model.FloorPicture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class FloorPictureListUpdateRequest {
    GetFloorPictureClusterPKDto floorPictureClusterPKDto;
    FloorPicture floorPicture;
}
