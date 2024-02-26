package Challengesemester2024.businessProcess.s3;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
    String uploadImageToS3(MultipartFile image);
}
