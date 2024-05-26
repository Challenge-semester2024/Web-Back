package Challengesemester2024.businessProcess.auth.service.Facade;

import Challengesemester2024.SpringSecurity.jwt.dto.AllJwtTokenDto;
import Challengesemester2024.businessProcess.auth.web.dto.auth.SignInDto;
import Challengesemester2024.businessProcess.auth.web.dto.auth.SignUpDto;
import Challengesemester2024.businessProcess.auth.service.Email.EmailService;
import Challengesemester2024.businessProcess.auth.service.PhoneNum.PhoneNumService;
import Challengesemester2024.businessProcess.facade.service.DatabaseFacadeService;
import Challengesemester2024.businessProcess.s3.S3Service;
import Challengesemester2024.domain.childCenter.service.ChildCenterService;
import Challengesemester2024.domain.manager.service.ManagerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthFacadeServiceImpl implements AuthFacadeService {
    private final ManagerService managerService;
    private final ChildCenterService childCenterService;
    private final EmailService emailService; //역할 인터페이스 의존성 주입받음
    private final PhoneNumService phoneNumService;
    private final S3Service s3Service;
    private final DatabaseFacadeService databaseFacadeService;

    @Override
    @Transactional
    public void authSignup(SignUpDto signUpDto, MultipartFile multipartFile) throws IOException {
        //인증번호 확인
        emailService.checkVerifyNumberByEmail(signUpDto.getCeoInfo().getEmail(),
                signUpDto.getCeoInfo().getEmailVerificationCode());
        phoneNumService.checkVerifyNumberByPhoneNum(signUpDto.getCeoInfo().getPhoneNum(),
                signUpDto.getCeoInfo().getPhoneVerificationCode());

        //각 db에 해당 값이 있는지 확인
        managerService.checkExits(signUpDto.getCeoInfo());
        childCenterService.checkExits(signUpDto.getCenterInfo());

        //사진 저장 로직
        String s3url = s3Service.uploadImageToS3(multipartFile);
        //DB 관계 설정 및 매핑
        databaseFacadeService.createDbWhenSignUp(signUpDto, s3url);
    }

    @Override
    public AllJwtTokenDto authSignIn(SignInDto signDto) {
        return managerService.signIn(signDto);
    }

}
