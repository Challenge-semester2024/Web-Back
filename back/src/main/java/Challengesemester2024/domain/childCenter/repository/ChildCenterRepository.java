package Challengesemester2024.domain.childCenter.repository;

import Challengesemester2024.domain.childCenter.model.ChildCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChildCenterRepository extends JpaRepository<ChildCenter, Long>, ChildCenterRepositoryCustom{
    Optional<ChildCenter> findByPhoneNumId(String phoneNumId);
}
