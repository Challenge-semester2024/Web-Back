package Challengesemester2024.domain.facility.floorPictureCluster.repository;

import Challengesemester2024.domain.facility.facilityIntroduction.dto.GetFacilityIntroPKDto;
import Challengesemester2024.domain.facility.floorPictureCluster.dto.GetFloorPictureClusterPKDto;
import Challengesemester2024.domain.facility.floorPicutre.model.FloorPicture;

public interface FloorPictureClusterRepositoryCustom {
    public GetFloorPictureClusterPKDto getFloorPicutreClusterPk(GetFacilityIntroPKDto getFacilityIntroPKDto);
    public void updateFloorPictureList(GetFloorPictureClusterPKDto getFloorPictureClusterPKDto, FloorPicture floorPicture);
}
