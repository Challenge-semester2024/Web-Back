package Challengesemester2024.domain.manager.repository;

import Challengesemester2024.domain.manager.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long>, ManagerRepositoryCustom {
    Optional<Manager> findByEmailId(String emailId);
}
