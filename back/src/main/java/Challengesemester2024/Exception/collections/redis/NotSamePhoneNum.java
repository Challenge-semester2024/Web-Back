package Challengesemester2024.Exception.collections.redis;

import Challengesemester2024.Exception.collections.business.BusinessException;
import Challengesemester2024.Exception.message.RedisExceptionMessage;

public class NotSamePhoneNum extends BusinessException {
    public NotSamePhoneNum() {
        super(RedisExceptionMessage.NotSamePhoneNum);
    }
}
