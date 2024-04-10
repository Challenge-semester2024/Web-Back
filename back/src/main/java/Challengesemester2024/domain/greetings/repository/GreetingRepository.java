package Challengesemester2024.domain.greetings.repository;

import Challengesemester2024.domain.greetings.domain.Greetings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface GreetingRepository extends JpaRepository<Greetings, CrudRepository>, GreetingRepositoryCustom {
}
