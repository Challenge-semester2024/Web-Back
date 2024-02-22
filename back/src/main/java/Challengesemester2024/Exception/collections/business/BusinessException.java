package Challengesemester2024.Exception.collections.business;

import java.util.List;

public class    BusinessException extends RuntimeException{
    protected BusinessException(String message) {
        super(message);
    }

    // 새로운 생성자: 여러 개의 에러 메시지를 리스트 형태로 받음
    protected BusinessException(List<String> errorMessages) {
        super(String.join(", ", errorMessages));  // 리스트의 내용을 하나의 문자열로 합침
    }

}
