package Challengesemester2024.domain.facility.floorPictureCluster.repository;

import Challengesemester2024.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface FloorPictureClusterRepository extends JpaRepository<FloorPictureCluster, CrudRepository>, FloorPictureClusterRepositoryCustom {
}
