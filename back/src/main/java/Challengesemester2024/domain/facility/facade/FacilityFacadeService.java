package Challengesemester2024.domain.facility.facade;

import Challengesemester2024.domain.facility.floorPicutre.dto.UpdateFloorPictureDto;
import Challengesemester2024.domain.facility.floorSize.dto.UpdateFloorSizeDto;

public interface FacilityFacadeService {
    void updateFloorSize(UpdateFloorSizeDto updateFloorSizeDto);
    void updateFloorPicture(UpdateFloorPictureDto updateFloorPictureDto);
}
