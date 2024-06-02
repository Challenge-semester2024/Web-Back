package Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.repository;

import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.model.RecruitmentWaiting;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RecruitmentWaitingRepository extends JpaRepository<RecruitmentWaiting, CrudRepository>, RecruitmentWaitingRepositoryCustom {
    @Query("SELECT rw FROM RecruitmentAccept rw JOIN rw.recruitmentDates rd WHERE rd = :date")
    List<RecruitmentWaiting> findByRecruitmentDate(@Param("date") LocalDate date);
    Optional<RecruitmentWaiting> findByRecruitmentIdAndVolunteerIdAndRecruitmentDatesContains(Long recruitmentId, Long volunteerId, LocalDate date);
}
