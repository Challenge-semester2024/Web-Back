package Challengesemester2024.businessProcess.Facade;

import Challengesemester2024.businessProcess.auth.dto.auth.S3urlDto;
import Challengesemester2024.businessProcess.auth.dto.auth.SignUpDto;

public interface DatabaseFacadeService {
    void createDbAndRelationsWhenSignUp(SignUpDto signUpDto, S3urlDto s3urlDto);
}
