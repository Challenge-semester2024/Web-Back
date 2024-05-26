package Challengesemester2024.domain.recruitment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestRecruitmentDto {

    @NotNull(message = "봉사 이름은 필수 항목입니다.")
    private String name;

    @NotNull(message = "모집 종료일은 필수 항목입니다.")
    private LocalDate recruitmentEndDate; //모집종료일

    @NotNull(message = "시간 설정 여부는 필수 항목입니다.")
    private boolean isTimeExits; // 시간 설정 여부

    @NotNull(message = "봉사 시작 시간은 필수 항목입니다.")
    private LocalTime startTime; //만약 isTimeExits일 경우, 기본값 (예: 00:00:00) 설정

    @NotNull(message = "봉사 종료 시간은 필수 항목입니다.")
    private LocalTime endTime; //만약 isTimeExits일 경우, 기본값 (예: 00:00:00) 설정

    @NotNull(message = "봉사 시작 날짜는 필수 항목입니다.")
    private LocalDate startDate;

    @NotNull(message = "봉사 종료 날짜는 필수 항목입니다.")
    private LocalDate endDate;

    @NotNull(message = "총 모집 인원은 필수 항목입니다.")
    @Min(value = 1, message = "총 모집 인원은 1명 이상이어야 합니다.")
    private Integer totalApplicants;

    @NotNull(message = "반복 여부는 필수 항목입니다.")
    private Boolean isRepeatedDate;

    @NotNull(message = "반복 요일은 필수 항목입니다.")
    @Size(min = 7, max = 7, message = "반복 요일 리스트는 7개의 항목을 가져야 합니다.")
    private List<Boolean> repeatedDays;

    private String detailInfo; //null이면 db에서 가져온 기본세팅값 저장
}

//java.sql.Date와 java.util.Date는 다르다.