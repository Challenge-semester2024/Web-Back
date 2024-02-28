package Challengesemester2024.domain.facilityIntroduction.repository;


import Challengesemester2024.domain.facilityIntroduction.model.FacilityIntroduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface FacilityIntroductionRepository extends JpaRepository<FacilityIntroduction, CrudRepository> {
}
