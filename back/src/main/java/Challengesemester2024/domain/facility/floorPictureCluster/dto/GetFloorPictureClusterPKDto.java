package Challengesemester2024.domain.facility.floorPictureCluster.dto;

import Challengesemester2024.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class GetFloorPictureClusterPKDto {
    FloorPictureCluster floorPictureCluster;
}
