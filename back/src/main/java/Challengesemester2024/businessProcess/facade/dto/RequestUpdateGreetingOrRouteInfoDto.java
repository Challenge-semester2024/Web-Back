package Challengesemester2024.businessProcess.facade.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateGreetingOrRouteInfoDto {
    @NotNull
    String memo;
}
