package Challengesemester2024.Exception.collections.business;

import Challengesemester2024.Exception.message.CommonExceptionMessage;

public class UnVerifiedUserException extends BusinessException{
    public UnVerifiedUserException() {
        super(CommonExceptionMessage.UnVerifiedUserInfo);
    }
}
