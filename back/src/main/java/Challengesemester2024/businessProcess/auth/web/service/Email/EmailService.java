package Challengesemester2024.businessProcess.auth.web.service.Email;

import Challengesemester2024.businessProcess.auth.web.dto.smtp.EmailDto;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendVerifyNumberByEmail(EmailDto emailDto) throws MessagingException;
    void checkVerifyNumberByEmail(String Email, String verifyNum);
}
