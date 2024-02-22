package Challengesemester2024.Exception.message;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CommonExceptionMessage {
    public static final String ManagerAlreadyExists = "이미 존재하는 관리자의 이메일 입니다.";
    public static final String ChildCenterAlreadyExists = "이미 존재하는 보육원 입니다.";
    public static final String UnAuthenticatedUserAccess=  "인증되지 않은 사용자입니다.";
    public static final String  UsernameNotFoundException = "입력하신 이메일의 계정이 존재하지 않습니다.";
    public static final String ACCOUNT_PASSWORD_NOT_MATCH = "입력하신 Password가 일치하지 않습니다.";
    public static final String CenterByManagerNotFound = "해당 관리자와 연결된 보육원을 찾을 수 없습니다.";

}
