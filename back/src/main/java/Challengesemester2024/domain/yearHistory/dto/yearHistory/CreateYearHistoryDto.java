package Challengesemester2024.domain.yearHistory.dto.yearHistory;

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
public class CreateYearHistoryDto {
    DecadeYear decadeYear;
    List<RequestYearDataDto> yearList;
}
