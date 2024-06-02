package Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentAccept.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerAcceptanceDto {
    private Long recruitmentId;
    private Long volunteerId;
    private LocalDate recruitmentDate;
}