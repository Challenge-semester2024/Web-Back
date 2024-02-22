package Challengesemester2024.Exception.collections.business;

import Challengesemester2024.Exception.message.TokenExceptonMessage;

public class TokenMissingException extends BusinessException {
    public TokenMissingException() {
        super(TokenExceptonMessage.TOKEN_MISSING_HEADER);
    }

    public TokenMissingException(String message){
        super(message);

    }
}
