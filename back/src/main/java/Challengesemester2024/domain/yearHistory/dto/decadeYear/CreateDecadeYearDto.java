package Challengesemester2024.domain.yearHistory.dto.decadeYear;

import Challengesemester2024.domain.childCenter.model.ChildCenter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDecadeYearDto {
    ChildCenter childCenter;
    int decadeStartYear;
}
