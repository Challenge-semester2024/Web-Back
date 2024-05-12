package Challengesemester2024.domain.facility.floorPictureCluster.service;

import Challengesemester2024.domain.facility.dto.FloorPictureListUpdateRequest;
import Challengesemester2024.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import Challengesemester2024.domain.facility.floorPictureCluster.model.FloorPictureCluster;

import java.util.List;

public interface FloorPictureClusterService {
//    void createFloorPictureList(FloorPictureListUpdateRequest floorPictureListUpdateRequest);
    FloorPictureCluster createFloorPictureCluster(FacilityIntroduction facilityIntroduction, int floor);
    FloorPictureCluster findByFloor(int floor);
    List<FloorPictureCluster> getAllFloorPictureCluster(FacilityIntroduction facilityIntroduction);


}
