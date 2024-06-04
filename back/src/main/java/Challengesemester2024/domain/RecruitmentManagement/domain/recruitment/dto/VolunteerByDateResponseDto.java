package Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto;

import Challengesemester2024.domain.volunteer.dto.VolunteerResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VolunteerByDateResponseDto {
    private List<VolunteerResponseDto> waitingVolunteers;
    private List<VolunteerResponseDto> acceptVolunteers;
}
