package Challengesemester2024.domain.manager.repository;

import Challengesemester2024.domain.manager.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

//QueryDSL을 사용하여 동적인 쿼리를 처리하기 위한 QuerydslPredicateExecutor를 구현한다. (Spring Data JPA의 기본 기능 정의)
public interface ManagerRepository extends JpaRepository<Manager, Long>, QuerydslPredicateExecutor<Manager> {
    Optional<Manager> findByEmailId(String emailId);
    // QueryDSL을 사용한 동적인 쿼리
    List<Manager> findAll(Predicate predicate);
    //Predicate는 QueryDSL에서 제공하는 인터페이스로, 불리언(boolean) 값을 반환하는 함수를 나타냅니다.
    // 즉, 어떤 조건을 표현하는데 사용됩니다.
}
