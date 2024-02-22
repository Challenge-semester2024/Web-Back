package Challengesemester2024.Exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDto {
    private String success; // 성공 여부
    private String errorMessage; // 발생한 에러 메시지
    private LocalDateTime occurredTime; // 에러 발생 시각
    private Integer statusCode; // 발생한 상태코드
    @Builder.Default
    private String stackTrace = "서버에서 정의 된 예외(무시 가능)";
}
