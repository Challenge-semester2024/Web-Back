package Challengesemester2024.businessProcess.auth.service.Email;

import Challengesemester2024.Exception.collections.redis.NotMatchVerificatonCodeByEmail;
import Challengesemester2024.Exception.collections.redis.NotSameEmail;
import Challengesemester2024.businessProcess.auth.dto.EmailDto;
import Challengesemester2024.businessProcess.auth.redis.model.RedisAuthCodeDto;
import Challengesemester2024.businessProcess.auth.redis.service.AuthRedisService;
import Challengesemester2024.businessProcess.auth.util.Util;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    private final AuthRedisService authRedisService;

    private void sendEmail(String email, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("minju9711@naver.com");
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        this.javaMailSender.send(message);
    }

    private void saveRedis(String email, String randomNum) {
        RedisAuthCodeDto authCodeDto = RedisAuthCodeDto.builder()
                .id(email)
                .code(randomNum)
                .build();
        authRedisService.save(authCodeDto);
    }

    @Override
    public void sendVerifyNumberByEmail(EmailDto emailDto) throws MessagingException {
        String randomNum = Util.getRandomNum();
        String email = emailDto.getEmail();
        sendEmail(email, "아지트 이메일 인증",
                String.format("<p>인증번호 : <span style=\"color:red\">%s<span></p>", randomNum)
        );
        saveRedis(email, randomNum);
    }

    @Override
    public void checkVerifyNumberByEmail(String Email, String verifyNum) {
        Optional<RedisAuthCodeDto> authCodeDto = authRedisService.find(Email);

        //이메일 인증 신청할때랑, 최종 제출한 이메일이 다른 경우
        if (!authCodeDto.isPresent()) throw new NotSameEmail();

        System.out.println("인증번호"+verifyNum);
        System.out.println("레디스 속 번호 : "+verifyNum.equals(authCodeDto.get().getCode()));

        //이메일 인증 신청할때랑, 최종 제출한 이메일이 다른 경우, 즉 해당 optionall이 null인 경우 에러필요
        if (!verifyNum.equals(authCodeDto.get().getCode()))
            throw new NotMatchVerificatonCodeByEmail();  //번호 다른 경우 에러 필요
    }

}
