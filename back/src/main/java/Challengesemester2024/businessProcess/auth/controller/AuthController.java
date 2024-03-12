package Challengesemester2024.businessProcess.auth.controller;

import Challengesemester2024.Exception.collections.InputValid.BindingErrors;
import Challengesemester2024.SpringSecurity.jwt.dto.AllJwtTokenDto;
import Challengesemester2024.businessProcess.auth.dto.auth.SignInDto;
import Challengesemester2024.businessProcess.auth.dto.auth.SignUpDto;
import Challengesemester2024.businessProcess.facade.service.AuthFacadeService;
import Challengesemester2024.config.constant.ControllerConstants;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthFacadeService authFacadeService;

    @Transactional //매니저와 보육원의 동시저장을 보장해줄 애노테이션
    @PostMapping("/signUp")
    public ResponseEntity<?> authSignup(@RequestPart("signUpDto") @Valid SignUpDto signUpDto,
                                        @RequestPart("certificateFile") MultipartFile multipartFile,
                                        BindingResult bindingResult ) throws IOException {
        //@Valid 체크
        handleBindingErrors(bindingResult);
        authFacadeService.authSignup(signUpDto, multipartFile);
        return new ResponseEntity<>(ControllerConstants.completeSignUp, HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> authSignIn(@RequestBody @Valid SignInDto signDto, BindingResult bindingResult) {
        // BindingResult 에러 처리
        handleBindingErrors(bindingResult);

        AllJwtTokenDto allJwtTokenDto = authFacadeService.authSignIn(signDto);
        return ResponseEntity.ok(allJwtTokenDto);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }

}
