package Challengesemester2024.domain.yearHistory.service.year;

import Challengesemester2024.domain.yearHistory.dto.yearHistory.CreateYearHistoryDto;
import Challengesemester2024.domain.yearHistory.model.YearHistory;

import java.util.List;

public interface YearHistoryService {
    List<YearHistory> saveAllYear( CreateYearHistoryDto createYearHistoryDto);
}
