package Challengesemester2024.domain.facility.floorSize.model;

import Challengesemester2024.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor(access =  AccessLevel.PROTECTED)
@Builder
@Table(name = "floor_size")
public class FloorSize {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String floor;

    @Column(nullable = false, length = 255)
    private String area;

    @Column(nullable = false, length = 255)
    private String usage;

    @Column(nullable = false, length = 255)
    private String mainRoom;

    @ManyToOne
    @JoinColumn(name = "facility_introduction_id")
    private FacilityIntroduction facilityIntroduction; //외래키가 있는 현재 테이블이 둘 관계의 주인이다!
}
