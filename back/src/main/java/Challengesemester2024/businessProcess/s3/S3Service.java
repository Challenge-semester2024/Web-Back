package Challengesemester2024.businessProcess.s3;

import Challengesemester2024.Exception.collections.IoException.ImageInputException;
import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
    String uploadImageToS3(MultipartFile image) throws ImageInputException;
}
