package Challengesemester2024.domain.center.likeChildCenter.repository;

import Challengesemester2024.domain.center.likeChildCenter.model.LikeCenter;
import Challengesemester2024.domain.volunteer.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikeCenterRepository extends JpaRepository<LikeCenter, CrudRepository>, LikeCenterRepositoryCustom {
    List<LikeCenter> findByVolunteer(Volunteer volunteer);
}
