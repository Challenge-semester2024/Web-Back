package Challengesemester2024.domain.facilityIntroduction.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor(access =  AccessLevel.PROTECTED)
@Builder
@Table(name = "facility_introduction")
public class FacilityIntroduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_introduction_id")
    private String id;

    @Column(nullable = false, length = 255)
    private int totalArea; //각 층 시설 합계면적

}
