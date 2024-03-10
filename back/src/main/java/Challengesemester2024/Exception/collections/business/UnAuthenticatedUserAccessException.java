package Challengesemester2024.Exception.collections.business;

import Challengesemester2024.Exception.message.CommonExceptionMessage;

public class UnAuthenticatedUserAccessException extends BusinessException{
    public UnAuthenticatedUserAccessException() {
        super(CommonExceptionMessage.UnAuthenticatedUserAccess);
    }
}
