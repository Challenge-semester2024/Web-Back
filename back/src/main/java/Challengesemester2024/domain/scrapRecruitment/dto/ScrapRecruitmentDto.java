package Challengesemester2024.domain.scrapRecruitment.dto;

import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.model.Recruitment;
import Challengesemester2024.domain.volunteer.model.Volunteer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScrapRecruitmentDto {
    Volunteer volunteer;
    Recruitment recruitment;
}
