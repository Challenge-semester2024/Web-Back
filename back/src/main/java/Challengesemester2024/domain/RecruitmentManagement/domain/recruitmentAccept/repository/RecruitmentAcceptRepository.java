package Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentAccept.repository;


import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentAccept.model.RecruitmentAccept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RecruitmentAcceptRepository extends JpaRepository<RecruitmentAccept, Long>, RecruitmentAcceptRepositoryCustom {
    boolean existsByVolunteerIdAndRecruitmentDatesContains(Long volunteerId, LocalDate date);
}