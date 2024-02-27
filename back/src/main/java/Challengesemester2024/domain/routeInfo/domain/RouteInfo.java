package Challengesemester2024.domain.routeInfo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED, force = true)
@Table(name = "route_info")
public class RouteInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "route_info_id")
    private Long id;

    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
    private String memo;

    public RouteInfo(String memo) {
        this.id = null;
        this.memo = memo;
    }
}
