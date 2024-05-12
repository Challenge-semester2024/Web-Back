package Challengesemester2024.domain.facility.floorPicutre.dto;

import Challengesemester2024.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import Challengesemester2024.domain.facility.floorPicutre.model.FloorPicture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateFloorPictureAndClusterDto {
    FloorPicture floorPicture;
    FloorPictureCluster floorPictureCluster;
}
