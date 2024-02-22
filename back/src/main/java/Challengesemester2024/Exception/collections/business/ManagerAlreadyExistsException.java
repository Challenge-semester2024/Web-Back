package Challengesemester2024.Exception.collections.business;

import Challengesemester2024.Exception.collections.business.BusinessException;
import Challengesemester2024.Exception.message.CommonExceptionMessage;

public class ManagerAlreadyExistsException extends BusinessException {
    public ManagerAlreadyExistsException() {
        super(CommonExceptionMessage.ManagerAlreadyExists);
    }
}