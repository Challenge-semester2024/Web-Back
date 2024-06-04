package Challengesemester2024.domain.volunteer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class VolunteerResponseDto {
    private Long id;
    private String name;
}