package Challengesemester2024.Exception.collections.InputValid;

import Challengesemester2024.Exception.message.InvalidRequestExceptionMessage;

public class InvalidClientRequest extends InvalidRequestException{

    public InvalidClientRequest() {
        super(InvalidRequestExceptionMessage.InvalidClientRequest);
    }
}
