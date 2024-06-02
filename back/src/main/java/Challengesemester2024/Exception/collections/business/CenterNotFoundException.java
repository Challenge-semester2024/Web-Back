package Challengesemester2024.Exception.collections.business;

import Challengesemester2024.Exception.message.CommonExceptionMessage;

public class CenterNotFoundException extends BusinessException {
    public CenterNotFoundException() {
        super(CommonExceptionMessage.CenterByManagerNotFound );
    }
}
