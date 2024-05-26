package Challengesemester2024.businessProcess.auth.app.dto.smtp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

//java 객체를 json으로 변환시, getter 및 setter 필요
@Data
@AllArgsConstructor
@Builder
public class NcpRequestDto {
    String type;
    String contentType;
    String countryCode;
    String from;
    String content;
    List<MessageDto> messages;
}