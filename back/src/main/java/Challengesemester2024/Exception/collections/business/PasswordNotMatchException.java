package Challengesemester2024.Exception.collections.business;

import Challengesemester2024.Exception.message.CommonExceptionMessage;
public class PasswordNotMatchException extends BusinessException {
    public PasswordNotMatchException() {
        super(CommonExceptionMessage.ACCOUNT_PASSWORD_NOT_MATCH);
    }
}