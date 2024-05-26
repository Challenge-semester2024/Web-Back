package Challengesemester2024.domain.recruitment.repository;

import Challengesemester2024.domain.recruitment.model.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RecruitmentRepository  extends JpaRepository<Recruitment, CrudRepository>, RecruitmentRepositoryCustom {

}
