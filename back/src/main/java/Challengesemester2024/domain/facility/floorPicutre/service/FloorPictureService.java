package Challengesemester2024.domain.facility.floorPicutre.service;

import Challengesemester2024.domain.facility.dto.FloorPictureListUpdateRequest;
import Challengesemester2024.domain.facility.floorPicutre.dto.FloorPictureDto;
import org.springframework.security.core.Authentication;

public interface FloorPictureService {
    FloorPictureListUpdateRequest createFloorPicture(FloorPictureDto floorPictureDto, Authentication authentication);
    void updateFloorPicture(FloorPictureDto floorPictureDto );
}
