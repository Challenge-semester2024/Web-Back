package Challengesemester2024.businessProcess.auth.web.redis.service;

import Challengesemester2024.businessProcess.auth.web.redis.model.RedisAuthCodeDto;
import Challengesemester2024.businessProcess.auth.web.redis.repository.AuthenticationCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthRedisServiceImpl implements AuthRedisService {
    private final AuthenticationCode authenticationCode;
    @Override
    public RedisAuthCodeDto save(RedisAuthCodeDto authCodeDto) {
       return authenticationCode.save(authCodeDto);
    }

    @Override
    public Optional<RedisAuthCodeDto> find(String id) {
        return authenticationCode.findById(id);
    }

}

