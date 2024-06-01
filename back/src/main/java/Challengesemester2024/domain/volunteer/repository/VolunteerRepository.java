package Challengesemester2024.domain.volunteer.repository;

import Challengesemester2024.domain.volunteer.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long>, QuerydslPredicateExecutor<Volunteer> {
    Optional<Volunteer> findByEmailId(String emailId);
    Optional<Volunteer> findByPhoneNum(String phoneNum);
}

