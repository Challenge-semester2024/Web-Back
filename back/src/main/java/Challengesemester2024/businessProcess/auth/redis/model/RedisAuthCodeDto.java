package Challengesemester2024.businessProcess.auth.redis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash(value = "refreshToken", timeToLive = 60 * 5* 12) //5분 뒤 자동삭제
public class RedisAuthCodeDto {
    private String id; // 이메일 또는 전화번호
    private String code; // 인증 코드
}