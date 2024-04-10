package Challengesemester2024.domain.yearHistory.facade;

import Challengesemester2024.domain.yearHistory.dto.decadeYear.RequestDecadeDataDto;

import java.util.List;

public interface YearFacadeService {
    void createYearHistory(List<RequestDecadeDataDto> yearDataList);
    void updateYearHistory(List<RequestDecadeDataDto> yearDataList);
}
