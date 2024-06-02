package Challengesemester2024.domain.childCenter.dto.put;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseChildCenterDto {
    private Long id;
    private String centerName;
    private String roadAddress;
    private String detailAddress;
    private String phoneNumber;
}