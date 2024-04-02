package Challengesemester2024.businessProcess.facade.service.DataBase;

import Challengesemester2024.businessProcess.auth.dto.auth.S3urlDto;
import Challengesemester2024.businessProcess.auth.dto.auth.SignUpDto;
import Challengesemester2024.domain.facility.dto.CreateDbWhenUpdateFloorPictureDto;
import Challengesemester2024.domain.facility.floorPicutre.dto.FloorPictureDto;

import java.io.IOException;

public interface DatabaseFacadeService {
    void createDbWhenSignUp(SignUpDto signUpDto, S3urlDto s3urlDto) throws IOException;
    void createDbWhenUpdateFloorPicute (CreateDbWhenUpdateFloorPictureDto createDbDto);
    void updateDbWhenModifyFloorPicture ( FloorPictureDto find);

}

