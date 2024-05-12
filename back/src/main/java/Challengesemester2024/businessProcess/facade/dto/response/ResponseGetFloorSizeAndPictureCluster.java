package Challengesemester2024.businessProcess.facade.dto.response;

import Challengesemester2024.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import Challengesemester2024.domain.facility.floorSize.model.FloorSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseGetFloorSizeAndPictureCluster {
    List<FloorSize> floorSizeList;
    List<FloorPictureCluster> floorPictureClusterList;
}
