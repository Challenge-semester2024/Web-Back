package Challengesemester2024.businessProcess.redis.service;

import Challengesemester2024.SpringSecurity.jwt.redis.model.RedisRefreshTokenDto;

import java.util.Optional;

public interface JwtRedisService {
    RedisRefreshTokenDto save(RedisRefreshTokenDto redisRefreshTokenDto);
    //리프레시 토큰을 redis 에 저장

    Optional<RedisRefreshTokenDto> find(String id);

}
