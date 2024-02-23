package Challengesemester2024.businessProcess.auth.controller;

import Challengesemester2024.Exception.collections.InputValid.BindingErrors;
import Challengesemester2024.SpringSecurity.jwt.dto.AllJwtTokenDto;
import Challengesemester2024.businessProcess.auth.dto.SignInDto;
import Challengesemester2024.businessProcess.auth.dto.SignUpDto;
import Challengesemester2024.businessProcess.auth.service.Email.EmailService;
import Challengesemester2024.businessProcess.auth.service.PhoneNum.PhoneNumService;
import Challengesemester2024.domain.childCenter.service.ChildCenterService;
import Challengesemester2024.domain.manager.service.MangerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final MangerService mangerService;
    private final ChildCenterService childCenterService;
    private final EmailService emailService;
    private final PhoneNumService phoneNumService;

    @Transactional //매니저와 보육원의 동시저장을 보장해줄 애노테이션
    @PostMapping("/signUp")
    public ResponseEntity<?> authSignup(@RequestBody @Valid SignUpDto signUpDto, BindingResult bindingResult){
        handleBindingErrors(bindingResult);

        //각자 인증번호가 맞는지 확인
        emailService.checkVerifyNumberByEmail(signUpDto.getManager().getEmail(),
                signUpDto.getManager().getEmailVerificationCode());
        phoneNumService.checkVerifyNumberByPhoneNum(signUpDto.getManager().getPhoneNum(),
                signUpDto.getManager().getPhoneVerificationCode());

        //각 db에 해당 값이 있는지 확인
        mangerService.checkExits(signUpDto.getManager());
        childCenterService.checkExits(signUpDto.getChildCenter());

        //회원가입 진행
        mangerService.register(signUpDto.getManager());
        childCenterService.register(signUpDto.getChildCenter());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //"파사드 패턴" : 복잡한 서브시스템에 대한 간편한 인터페이스를 제공하는 디자인 패턴
    // 이 패턴은 클라이언트와 복잡한 서브시스템 간의 의존성을 줄이고, 서브시스템을 더 쉽게 사용할 수 있도록 한다.
    // 나중에 리팩토링 과정에서 더 자세하게 알아보면 좋을 것 같다.

    @PostMapping("/signIn")
    public ResponseEntity<?> authSignIn(@RequestBody @Valid SignInDto signDto, BindingResult bindingResult) {
        // BindingResult 에러 처리
        handleBindingErrors(bindingResult);

        AllJwtTokenDto allJwtTokenDto = mangerService.signIn(signDto);
        return ResponseEntity.ok(allJwtTokenDto);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            throw new BindingErrors(fieldErrors);
        }
    }

}
