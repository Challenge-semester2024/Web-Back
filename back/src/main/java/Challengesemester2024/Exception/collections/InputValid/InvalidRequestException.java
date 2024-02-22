package Challengesemester2024.Exception.collections.InputValid;

import java.util.List;

public class InvalidRequestException extends RuntimeException {
    protected InvalidRequestException(String message) {
        super(message);
    }
    protected InvalidRequestException(List<String> errorMessages) {
        super(String.join(", ", errorMessages));  // 리스트의 내용을 하나의 문자열로 합침
    }

}