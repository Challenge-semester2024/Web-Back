package Challengesemester2024.domain.facility.facilityIntroduction.model;

import Challengesemester2024.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import Challengesemester2024.domain.facility.floorSize.model.FloorSize;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor(access =  AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "facility_introduction")
public class FacilityIntroduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_introduction_id")
    private String id;

    @Column(nullable = false, length = 255)
    private int totalArea; //각 층 시설 합계면적

    //양방향 설계를 위한 필드들
    @OneToMany(mappedBy = "facilityIntroduction", fetch = FetchType.LAZY)
    private List<FloorSize> floorSizes;

    @OneToMany(mappedBy = "facilityIntroduction", fetch = FetchType.LAZY)
    private List<FloorPictureCluster> floorPictureClusters;
}
