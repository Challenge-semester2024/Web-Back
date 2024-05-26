package Challengesemester2024.businessProcess.auth.web.controller;

import Challengesemester2024.businessProcess.auth.web.dto.smtp.EmailDto;
import Challengesemester2024.businessProcess.auth.web.dto.smtp.PhoneNumDto;
import Challengesemester2024.businessProcess.auth.web.service.Email.EmailService;
import Challengesemester2024.businessProcess.auth.web.service.PhoneNum.PhoneNumService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("api/verify")
@RequiredArgsConstructor
@Slf4j
public class VerifyCodeController {
    private final EmailService emailService;
    private final PhoneNumService phoneNumService;

    @PostMapping("/Email")
    public ResponseEntity<?> validateEmail(@Valid @RequestBody EmailDto emailDto, BindingResult bindingResult) throws MessagingException {
        emailService.sendVerifyNumberByEmail(emailDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/PhoneNum")
    public ResponseEntity<?> validatePhone(@Valid @RequestBody PhoneNumDto phoneNumDto, BindingResult bindingResult) throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException {
        phoneNumService.sendVerifyNumberByPhoneNum(phoneNumDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
