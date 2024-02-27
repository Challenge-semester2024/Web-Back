package Challengesemester2024.domain.routeInfo.repository;

import Challengesemester2024.domain.routeInfo.domain.RouteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RouteInfoRepository extends JpaRepository<RouteInfo, CrudRepository> {
}
