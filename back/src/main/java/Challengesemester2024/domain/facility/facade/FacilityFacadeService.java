package Challengesemester2024.domain.facility.facade;

import Challengesemester2024.domain.facility.floorPicutre.dto.FloorPictureDto;
import Challengesemester2024.domain.facility.floorSize.dto.UpdateFloorSizeDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FacilityFacadeService {
    void createOrUpdateFloorSize(List<UpdateFloorSizeDto> updateFloorSizeDtoList);
    void createFloorPicture(List<FloorPictureDto> createFloorPictureDtoList, List<MultipartFile> multipartFile);
    void updateFloorPicuture(List<FloorPictureDto> updateFloorPictureDtoList, List<MultipartFile> multipartFiles);
}
