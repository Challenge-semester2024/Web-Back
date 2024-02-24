package Challengesemester2024.businessProcess.auth.service.PhoneNum;

import Challengesemester2024.Exception.collections.business.UnVerifiedUserException;
import Challengesemester2024.Exception.collections.redis.NotMatchVerificatonCodeByPhoneNum;
import Challengesemester2024.Exception.collections.redis.NotSamePhoneNum;
import Challengesemester2024.businessProcess.auth.dto.MessageDto;
import Challengesemester2024.businessProcess.auth.dto.NcpRequestDto;
import Challengesemester2024.businessProcess.auth.dto.NcpResponseDto;
import Challengesemester2024.businessProcess.auth.dto.PhoneNumDto;
import Challengesemester2024.businessProcess.auth.redis.model.RedisAuthCodeDto;
import Challengesemester2024.businessProcess.auth.redis.service.AuthRedisService;
import Challengesemester2024.businessProcess.auth.util.Util;
import Challengesemester2024.config.smtp.PhoneConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhoneNumServiceImpl implements PhoneNumService{
    private final PhoneConfig phoneConfig;
    private final AuthRedisService authRedisService;

    private String makeSignature(Long time) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String space = " ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/" + phoneConfig.getServiceId() + "/messages";
        String timestamp = time.toString();
        String accessKey = phoneConfig.getAccessKey();
        String secretKey = phoneConfig.getSecretKey();

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);

        return encodeBase64String;
    }

    private void saveRedis(String phoneNum,String randomNum){
        RedisAuthCodeDto authCodeDto = RedisAuthCodeDto.builder()
                .id(phoneNum)
                .code(randomNum)
                .build();
        authRedisService.save(authCodeDto);
    }

    @Override
    public void sendVerifyNumberByPhoneNum(PhoneNumDto phoneNumDto) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException, URISyntaxException {
        String randomNum = Util.getRandomNum();

        Long time = System.currentTimeMillis();
        String accessKey = phoneConfig.getAccessKey();
        String phone = phoneConfig.getAdminphone();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", accessKey);
        headers.set("x-ncp-apigw-signature-v2", makeSignature(time));

        MessageDto messageDto = new MessageDto(); // 메시지 생성
        messageDto.setTo(phoneNumDto.getPhoneNum());

        List<MessageDto> messages = new ArrayList<>();
        messages.add(messageDto);

        String content = "아지트 인증번호:" + randomNum;

        NcpRequestDto request = NcpRequestDto.builder()
                .type("SMS")
                .contentType("COMM")
                .countryCode("82")
                .from(phone)
                .content(content)
                .messages(messages)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        HttpEntity<String> httpBody = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/" + phoneConfig.getServiceId() + "/messages"), httpBody, NcpResponseDto.class);

        saveRedis(phoneNumDto.getPhoneNum(), randomNum);
    }

    @Override
    public void checkVerifyNumberByPhoneNum(String PhoneNum, String verifyNum) {
        Optional<RedisAuthCodeDto> authCodeDto = authRedisService.find(PhoneNum);

        //인증번호 전송을 안한 경우
        if(authCodeDto.isEmpty()) throw new UnVerifiedUserException();

        //전화번호 인증 신청할때랑, 최종 제출한 전화번호가 다른 경우
        if(!authCodeDto.isPresent()) throw new NotSamePhoneNum();

        //인증 코드가 다른 경우 에러 필요
        if(!verifyNum.equals(authCodeDto.get().getCode())) throw new NotMatchVerificatonCodeByPhoneNum();
    }

}
