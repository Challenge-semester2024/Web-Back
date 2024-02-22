package Challengesemester2024.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "sms")
public class PhoneConfig {
    private String accessKey;
    private String secretKey;
    private String serviceId;
    private String adminphone;
}
