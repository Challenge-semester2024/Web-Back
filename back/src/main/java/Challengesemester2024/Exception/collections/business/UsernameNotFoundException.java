package Challengesemester2024.Exception.collections.business;

import Challengesemester2024.Exception.collections.business.BusinessException;
import Challengesemester2024.Exception.message.CommonExceptionMessage;

public class UsernameNotFoundException extends BusinessException {
    public UsernameNotFoundException() {
        super(CommonExceptionMessage.UsernameNotFoundException);
    }
}
