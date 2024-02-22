package Challengesemester2024.domain.manager.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDto {
    private String ManagerEmailId;
    private String Role;
}
