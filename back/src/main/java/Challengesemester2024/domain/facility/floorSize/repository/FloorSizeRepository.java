package Challengesemester2024.domain.facility.floorSize.repository;

import Challengesemester2024.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import Challengesemester2024.domain.facility.floorSize.model.FloorSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FloorSizeRepository extends JpaRepository<FloorSize,CrudRepository>, FloorSizeRepositoryCustom {
    FloorSize findByDisplayIndex(int displayIndex);
    List<FloorSize> findByFacilityIntroductionOrderByDisplayIndexAsc(FacilityIntroduction facilityIntroduction);
}
