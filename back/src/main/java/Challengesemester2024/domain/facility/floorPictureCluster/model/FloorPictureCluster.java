package Challengesemester2024.domain.facility.floorPictureCluster.model;

import Challengesemester2024.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import Challengesemester2024.domain.facility.floorPicutre.model.FloorPicture;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor(access =  AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "floor_picture_cluster")
public class FloorPictureCluster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "floor_picture_cluster_id")
    private Long id;

    @Column(nullable = false, length = 255)
    private String picuture;

    @ManyToOne
    @JoinColumn(name = "facility_introduction_id")
    private FacilityIntroduction facilityIntroduction;

    @OneToMany(mappedBy = "floorPictureCluster")
    private List<FloorPicture> floorPicture;
}
