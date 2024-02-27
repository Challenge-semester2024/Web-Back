package Challengesemester2024.businessProcess.s3;

import Challengesemester2024.businessProcess.auth.dto.auth.S3urlDto;
import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
    S3urlDto uploadImageToS3(MultipartFile image);
}
