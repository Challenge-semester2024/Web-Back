package Challengesemester2024.Exception.collections.redis;

import Challengesemester2024.Exception.collections.business.BusinessException;
import Challengesemester2024.Exception.message.RedisExceptionMessage;

public class NotMatchVerificatonCodeByEmail extends BusinessException {
    public NotMatchVerificatonCodeByEmail() {
        super(RedisExceptionMessage.NotMatchVerificatonCodeByEmail);
    }
}
