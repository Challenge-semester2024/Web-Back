package Challengesemester2024.domain.facilityIntroduction.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED, force = true)
@Table(name = "facility_introduction")
public class FacilityIntroduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_introduction_id")
    private String id;

    @Column(nullable = false, length = 255)
    private int totalArea; //각 층 시설 합계면적

    public FacilityIntroduction(int totalArea) {
        this.id=null;
        this.totalArea = totalArea;
    }
}
