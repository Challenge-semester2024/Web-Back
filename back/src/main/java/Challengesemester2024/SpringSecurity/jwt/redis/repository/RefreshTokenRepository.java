package Challengesemester2024.SpringSecurity.jwt.redis.repository;

import Challengesemester2024.SpringSecurity.jwt.redis.model.RedisRefreshTokenDto;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RedisRefreshTokenDto, String> {

}