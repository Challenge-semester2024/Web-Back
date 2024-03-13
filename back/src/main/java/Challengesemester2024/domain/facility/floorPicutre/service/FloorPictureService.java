package Challengesemester2024.domain.facility.floorPicutre.service;

import Challengesemester2024.SpringSecurity.authentication.AuthenticatedEmailDTO;
import Challengesemester2024.domain.facility.dto.FloorPictureListUpdateRequest;
import Challengesemester2024.domain.facility.floorPicutre.dto.UpdateFloorPictureDto;

public interface FloorPictureService {
    FloorPictureListUpdateRequest updateFloorPicture(UpdateFloorPictureDto updateFloorPictureDto, AuthenticatedEmailDTO authenticatedEmailDTO);
}