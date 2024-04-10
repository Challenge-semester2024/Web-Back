package Challengesemester2024.domain.facility.floorPictureCluster.repository;

import Challengesemester2024.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import Challengesemester2024.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import Challengesemester2024.domain.facility.floorPicutre.model.FloorPicture;

public interface FloorPictureClusterRepositoryCustom {
    public FloorPictureCluster getFloorPicutreClusterPk(FacilityIntroduction facilityIntroduction);
    public void createFloorPictureList(FloorPictureCluster floorPictureCluster, FloorPicture floorPicture);
}
