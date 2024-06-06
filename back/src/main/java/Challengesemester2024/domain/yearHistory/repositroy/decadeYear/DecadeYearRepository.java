package Challengesemester2024.domain.yearHistory.repositroy.decadeYear;

import Challengesemester2024.domain.center.childCenter.model.ChildCenter;
import Challengesemester2024.domain.yearHistory.model.DecadeYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DecadeYearRepository extends JpaRepository<DecadeYear, CrudRepository>,DecadeYearRepositoryCustom {
    DecadeYear findByDecadeStartYearAndChildCenter_Id(int decadeStartYear, Long childCenterId);
    List<DecadeYear> findByChildCenterOrderByDecadeStartYearAsc(ChildCenter childCenter);

}
