package Challengesemester2024.domain.facility.floorPicutre.repository;

import Challengesemester2024.domain.facility.floorPicutre.model.FloorPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface FloorPictureRepository extends JpaRepository<FloorPicture, CrudRepository>,  FloorPictureRepositoryCustom {
}
