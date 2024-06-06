package Challengesemester2024.domain.yearHistory.service.decadeYear;

import Challengesemester2024.domain.center.childCenter.model.ChildCenter;
import Challengesemester2024.domain.yearHistory.dto.decadeYear.CreateDecadeYearDto;
import Challengesemester2024.domain.yearHistory.model.DecadeYear;

import java.util.List;

public interface DecadeYearService {
    DecadeYear createDecadeYear(CreateDecadeYearDto createDecadeYearDto);
    List<DecadeYear> findAllDecadeYearDesc(ChildCenter fechedChildCenter);
}
