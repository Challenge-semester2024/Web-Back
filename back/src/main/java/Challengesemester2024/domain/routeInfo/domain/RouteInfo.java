package Challengesemester2024.domain.routeInfo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor(access=AccessLevel.PROTECTED)
@Builder
@Table(name = "route_info")
public class RouteInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "route_info_id")
    private Long id;

    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
    private String memo;

}
