package Challengesemester2024.SpringSecurity.jwt.redis.model;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 3) //3일 뒤 자동삭제
public class RedisRefreshTokenDto {

    @Id
    private String id; //여기에 해당 리프래시 토큰 가진 managerEmailId 들어감
    private String refrehToken;

}
