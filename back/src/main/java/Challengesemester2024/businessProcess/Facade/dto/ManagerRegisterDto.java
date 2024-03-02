package Challengesemester2024.businessProcess.Facade.dto;

import Challengesemester2024.domain.childCenter.model.ChildCenter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ManagerRegisterDto {
    ChildCenter childCenter;
}
