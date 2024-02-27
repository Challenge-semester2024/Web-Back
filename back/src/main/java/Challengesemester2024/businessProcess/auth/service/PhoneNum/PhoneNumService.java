package Challengesemester2024.businessProcess.auth.service.PhoneNum;

import Challengesemester2024.businessProcess.auth.dto.smtp.PhoneNumDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public interface PhoneNumService {
    void sendVerifyNumberByPhoneNum(PhoneNumDto phoneNumDto) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException, URISyntaxException;
    void checkVerifyNumberByPhoneNum(String PhoneNum, String verifyNum);
}
