package Challengesemester2024.businessProcess.s3;

import Challengesemester2024.businessProcess.auth.dto.auth.S3urlDto;
import Challengesemester2024.businessProcess.util.UtilService;
import Challengesemester2024.config.s3.S3Config;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service{
    private final UtilService utilService;
    private final AmazonS3 amazonS3;
    private final S3Config s3Config;

    @Override
    public S3urlDto uploadImageToS3(MultipartFile image) {
        String bucketName = s3Config.getBucketName();
        String originName = image.getOriginalFilename(); //원본 이미지 이름
        String ext = originName.substring(originName.lastIndexOf(".")); //확장자
        String changedName = utilService.getRandomUUID(originName); //새로 생성된 이미지 이름
        ObjectMetadata metadata = new ObjectMetadata(); //메타데이터

        metadata.setContentType("image/"+ext);
        try {
            PutObjectResult putObjectResult = amazonS3.putObject(new PutObjectRequest(
                    bucketName, changedName, image.getInputStream(), metadata
            ).withCannedAcl(CannedAccessControlList.PublicRead));

        } catch (IOException e) {
            //throw new ImageUploadException(); //커스텀 예외 던짐.
        }

        S3urlDto s3urlDto = S3urlDto.builder()
                .S3url(amazonS3.getUrl(bucketName, changedName).toString())
                .build();

        return s3urlDto; //데이터베이스에 저장할 이미지가 저장된 주소
    }

}
