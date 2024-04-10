package Challengesemester2024.domain.routeInfo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor(access=AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "route_info")
public class RouteInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "route_info_id")
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String memo;
}
