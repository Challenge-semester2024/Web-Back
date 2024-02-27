package Challengesemester2024.businessProcess.auth.service.Facade;

import Challengesemester2024.SpringSecurity.jwt.dto.AllJwtTokenDto;
import Challengesemester2024.businessProcess.auth.dto.auth.SignInDto;
import Challengesemester2024.businessProcess.auth.dto.auth.SignUpDto;
import Challengesemester2024.businessProcess.auth.service.Email.EmailService;
import Challengesemester2024.businessProcess.auth.service.PhoneNum.PhoneNumService;
import Challengesemester2024.businessProcess.s3.S3Service;
import Challengesemester2024.domain.childCenter.service.ChildCenterService;
import Challengesemester2024.domain.manager.service.MangerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AuthFacadeServiceImpl implements AuthFacade{
    private final MangerService mangerService;
    private final ChildCenterService childCenterService;
    private final EmailService emailService; //역할 인터페이스 의존성 주입받음
    private final PhoneNumService phoneNumService;
    private final S3Service s3Service;

    @Override
    public void authSignup(SignUpDto signUpDto, MultipartFile multipartFile) {
        emailService.checkVerifyNumberByEmail(signUpDto.getCeoInfo().getEmail(),
                signUpDto.getCeoInfo().getEmailVerificationCode());
        phoneNumService.checkVerifyNumberByPhoneNum(signUpDto.getCeoInfo().getPhoneNum(),
                signUpDto.getCeoInfo().getPhoneVerificationCode());

        //각 db에 해당 값이 있는지 확인
        mangerService.checkExits(signUpDto.getCeoInfo());
        childCenterService.checkExits(signUpDto.getCenterInfo());

        //사진 저장 로직
        String fileUrl = s3Service.uploadImageToS3(multipartFile);

        //회원가입 진행
        mangerService.register(signUpDto.getCeoInfo());
        childCenterService.register(signUpDto.getCenterInfo(),fileUrl);
    }

    @Override
    public AllJwtTokenDto authSignIn(SignInDto signDto) {
        return mangerService.signIn(signDto);
    }
}
