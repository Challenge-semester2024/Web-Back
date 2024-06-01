package Challengesemester2024.Exception.collections.business;

import Challengesemester2024.Exception.message.CommonExceptionMessage;

public class UserAlreadyExistsException extends BusinessException {
    public UserAlreadyExistsException() {
        super(CommonExceptionMessage.UserAlreadyExists);
    }
}