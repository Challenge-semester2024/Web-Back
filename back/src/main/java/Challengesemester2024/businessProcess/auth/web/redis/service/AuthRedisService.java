package Challengesemester2024.businessProcess.auth.web.redis.service;

import Challengesemester2024.businessProcess.auth.web.redis.model.RedisAuthCodeDto;

import java.util.Optional;

public interface AuthRedisService {
    RedisAuthCodeDto save(RedisAuthCodeDto authCodeDto);
    //리프레시 토큰을 redis 에 저장

    Optional<RedisAuthCodeDto> find(String id);

}
