package Challengesemester2024.businessProcess.facade.service;

import Challengesemester2024.businessProcess.auth.dto.auth.S3urlDto;
import Challengesemester2024.businessProcess.auth.dto.auth.SignUpDto;

import java.io.IOException;

public interface DatabaseFacadeService {
    void createDbAndRelationsWhenSignUp(SignUpDto signUpDto, S3urlDto s3urlDto) throws IOException;
}
