package Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.repository;

import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.model.Recruitment;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecruitmentRepository  extends JpaRepository<Recruitment, CrudRepository>, RecruitmentRepositoryCustom {
    List<Recruitment> findByChildCenter(ChildCenter childCenter);
    Recruitment findById(Long id);

}
