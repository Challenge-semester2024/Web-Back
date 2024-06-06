package Challengesemester2024.domain.facility.floorSize.repository;

import Challengesemester2024.domain.center.childCenter.model.ChildCenter;
import Challengesemester2024.domain.facility.floorSize.model.FloorSize;


public interface FloorSizeRepositoryCustom {
    FloorSize isExitsFloorSize (int displayIndex, ChildCenter childCenter);
}
