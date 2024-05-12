package Challengesemester2024.domain.facility.floorPictureCluster.repository;

import Challengesemester2024.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import Challengesemester2024.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import Challengesemester2024.domain.facility.floorPicutre.model.FloorPicture;

import java.util.List;

public interface FloorPictureClusterRepositoryCustom {
    FloorPictureCluster getFloorPicutreClusterPk(FacilityIntroduction facilityIntroduction);
    void createFloorPictureList(FloorPictureCluster floorPictureCluster, FloorPicture floorPicture);
    List<FloorPictureCluster> getAllFloorPicutre (FacilityIntroduction facilityIntroduction);
}
