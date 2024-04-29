package Challengesemester2024.Exception.collections.business;

import Challengesemester2024.Exception.message.SmsExceptionMessage;

public class FailedSendSmsToClient extends BusinessException{
    public FailedSendSmsToClient() {
        super(SmsExceptionMessage.FailedSendSmsToClient);
    }
}
