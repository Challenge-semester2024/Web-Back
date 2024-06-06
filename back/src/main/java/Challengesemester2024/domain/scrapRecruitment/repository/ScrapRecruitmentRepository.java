package Challengesemester2024.domain.scrapRecruitment.repository;

import Challengesemester2024.domain.scrapRecruitment.model.ScrapRecruitment;
import Challengesemester2024.domain.volunteer.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScrapRecruitmentRepository extends JpaRepository<ScrapRecruitment, CrudRepository>, ScrapRecruitmentRepositoryCustom {
    List<ScrapRecruitment> findByVolunteer(Volunteer volunteer);
    List<ScrapRecruitment> findTop3ByVolunteerOrderByIdDesc(Volunteer volunteer);
}
