package Challengesemester2024.domain.facilityIntroduction.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    //양방향 설계를 위한 필드들
    @OneToMany(mappedBy = "facility_picture", fetch = FetchType.LAZY)
    private List<FacilityPicture> facilityPictures;

    @OneToMany(mappedBy = "facility_size", fetch = FetchType.LAZY)
    private List<FacilitySize> facilitySizes;

    @OneToMany(mappedBy = "floor_picture_cluster", fetch = FetchType.LAZY)
    private List<FloorPictureCluster> FloorPictureClusters;
}
