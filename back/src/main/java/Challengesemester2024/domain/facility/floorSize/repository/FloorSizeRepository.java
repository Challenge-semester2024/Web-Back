package Challengesemester2024.domain.facility.floorSize.repository;

import Challengesemester2024.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import Challengesemester2024.domain.facility.floorSize.model.FloorSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FloorSizeRepository extends JpaRepository<FloorSize, Long>, FloorSizeRepositoryCustom {
    List<FloorSize> findByFacilityIntroductionOrderByDisplayIndexAsc(FacilityIntroduction facilityIntroduction);
}
