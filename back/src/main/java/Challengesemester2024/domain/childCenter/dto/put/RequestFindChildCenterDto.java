package Challengesemester2024.domain.childCenter.dto.put;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestFindChildCenterDto {

    @NotBlank(message = "검색어를 입력해주세요.")
    private String childCenterName;

    @NotBlank(message = "검색어를 입력해주세요.")
    private String roadAddress;

    @NotNull(message = "검색어의 판단 기준을 알려주세요. (0 : 제목, 1 : 주소)")
    private boolean findWordStandard; //0 : 제목, 1 : 주소

}