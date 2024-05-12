package Challengesemester2024.domain.yearHistory.repositroy.decadeYear;

import Challengesemester2024.domain.childCenter.model.ChildCenter;
import Challengesemester2024.domain.yearHistory.model.DecadeYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DecadeYearRepository extends JpaRepository<DecadeYear, CrudRepository>,DecadeYearRepositoryCustom {
    DecadeYear findByDecadeStartYear(int decadeStartYear);
    List<DecadeYear> findByChildCenterOrderByDecadeStartYearAsc(ChildCenter childCenter);

}
