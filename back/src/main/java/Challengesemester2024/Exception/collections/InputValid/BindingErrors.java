package Challengesemester2024.Exception.collections.InputValid;

import static Challengesemester2024.Exception.message.CommonExceptionMessage.BindingErrorMessage;

public class BindingErrors extends InvalidRequestException{
    public BindingErrors() {
        super(BindingErrorMessage);
    }
}
