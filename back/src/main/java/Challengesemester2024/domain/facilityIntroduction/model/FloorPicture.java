package Challengesemester2024.domain.facilityIntroduction.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor(access =  AccessLevel.PROTECTED)
@Builder
@Table(name = "floor_picture")
public class FloorPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String floor;

    @ManyToOne
    @JoinColumn(name = "floor_picutre_cluster_id")
    private FloorPictureCluster floorPictureCluster;
}
