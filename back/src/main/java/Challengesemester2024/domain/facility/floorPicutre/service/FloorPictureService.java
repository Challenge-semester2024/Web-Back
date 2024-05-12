package Challengesemester2024.domain.facility.floorPicutre.service;

import Challengesemester2024.domain.facility.floorPicutre.model.FloorPicture;

import java.util.List;

public interface FloorPictureService {
    void updateFloorPicture(FloorPicture updateFloorPicture);
    void createAllFloorPictureList(List<FloorPicture> floorPictureList);
}
