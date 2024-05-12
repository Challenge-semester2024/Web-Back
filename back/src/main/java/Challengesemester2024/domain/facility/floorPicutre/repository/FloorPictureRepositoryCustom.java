package Challengesemester2024.domain.facility.floorPicutre.repository;


import Challengesemester2024.domain.facility.dto.UpdateFloorPictureDto;
import Challengesemester2024.domain.facility.floorPicutre.dto.FloorPictureDto;

public interface FloorPictureRepositoryCustom {
    UpdateFloorPictureDto updateFloorPicture(FloorPictureDto updateFloorPicture);
  }
