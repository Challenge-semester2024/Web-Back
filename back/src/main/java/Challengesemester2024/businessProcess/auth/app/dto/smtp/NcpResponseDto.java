package Challengesemester2024.businessProcess.auth.app.dto.smtp;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class NcpResponseDto {
    String requestId;
    LocalDateTime requestTime;
    String statusCode;
    String statusName;
}