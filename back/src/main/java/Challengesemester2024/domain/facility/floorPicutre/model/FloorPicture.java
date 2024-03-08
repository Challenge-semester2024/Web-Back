package Challengesemester2024.domain.facility.floorPicutre.model;

import Challengesemester2024.domain.facility.floorPictureCluster.model.FloorPictureCluster;
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
    @JoinColumn(name = "floor_picture_cluster_id")
    private FloorPictureCluster floorPictureCluster;
}
