package Challengesemester2024.businessProcess.auth.web.redis.repository;

import Challengesemester2024.businessProcess.auth.web.redis.model.RedisAuthCodeDto;
import org.springframework.data.repository.CrudRepository;

public interface AuthenticationCode extends CrudRepository<RedisAuthCodeDto, String>{
}
