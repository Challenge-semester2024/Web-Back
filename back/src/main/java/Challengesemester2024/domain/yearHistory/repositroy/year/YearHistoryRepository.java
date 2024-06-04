package Challengesemester2024.domain.yearHistory.repositroy.year;

import Challengesemester2024.domain.yearHistory.model.YearHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface YearHistoryRepository extends JpaRepository<YearHistory, CrudRepository>, YearHistoryRepositoryCustom {
    YearHistory findByDisplayIndexAndDecadeYear_ChildCenter_Id(int displayIndex, Long childCenterId);
}
