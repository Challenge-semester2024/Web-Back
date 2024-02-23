package Challengesemester2024.Exception.collections.business;

import Challengesemester2024.Exception.message.CommonExceptionMessage;

public class DuplicateUniqueKeyException extends BusinessException{
    public DuplicateUniqueKeyException() {
        super(CommonExceptionMessage.DuplicatedUniqueKey);
    }
}
