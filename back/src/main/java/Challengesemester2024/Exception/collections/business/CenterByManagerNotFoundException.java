package Challengesemester2024.Exception.collections.business;

import Challengesemester2024.Exception.collections.business.BusinessException;
import Challengesemester2024.Exception.message.CommonExceptionMessage;

public class CenterByManagerNotFoundException extends BusinessException {
    public CenterByManagerNotFoundException() {
        super(CommonExceptionMessage.CenterByManagerNotFound );
    }

}
