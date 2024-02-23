package Challengesemester2024.businessProcess.auth.controller;

import Challengesemester2024.Exception.collections.InputValid.BindingErrors;
import Challengesemester2024.SpringSecurity.jwt.dto.AllJwtTokenDto;
import Challengesemester2024.businessProcess.auth.dto.SignInDto;
import Challengesemester2024.businessProcess.auth.dto.SignUpDto;
import Challengesemester2024.businessProcess.auth.service.Email.EmailService;
import Challengesemester2024.businessProcess.auth.service.PhoneNum.PhoneNumService;
import Challengesemester2024.domain.childCenter.service.ChildCenterService;
import Challengesemester2024.domain.manager.service.MangerService;
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

    @PostMapping("/signUp")
    public ResponseEntity<?> authSignup(@RequestBody @Valid SignUpDto signUpDto, BindingResult bindingResult){
        handleBindingErrors(bindingResult);

        //각자 인증번호가 맞는지 확인
        emailService.checkVerifyNumberByEmail(signUpDto.getManager().getEmail(),
                signUpDto.getManager().getEmailVerificationCode());
        phoneNumService.checkVerifyNumberByPhoneNum(signUpDto.getManager().getPhoneNum(),
                signUpDto.getManager().getPhoneVerificationCode());

        //모든 인증번호가 맞을 시, 회원가입 진행
        mangerService.register(signUpDto.getManager());
        childCenterService.register(signUpDto.getChildCenter());

        return new ResponseEntity<>(HttpStatus.OK);
    }


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
