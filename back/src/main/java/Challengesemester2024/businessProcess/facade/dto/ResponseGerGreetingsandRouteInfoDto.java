package Challengesemester2024.businessProcess.facade.dto;

import Challengesemester2024.domain.greetings.domain.Greetings;
import Challengesemester2024.domain.yearHistory.model.DecadeYear;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseGerGreetingsandRouteInfoDto {
    Greetings greeting;
    List<DecadeYear> decadeYearList;
}
