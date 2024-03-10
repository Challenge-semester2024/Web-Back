package Challengesemester2024.domain.facility.floorSize.repository;

import Challengesemester2024.domain.facility.floorSize.model.FloorSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface FloorRepository extends JpaRepository<FloorSize,CrudRepository>, FloorRepositoryCustom {
}
