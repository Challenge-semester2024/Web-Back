package Challengesemester2024.domain.facility.floorPictureCluster.service;


import Challengesemester2024.domain.facility.dto.FloorPictureListUpdateRequest;
import Challengesemester2024.domain.facility.floorPictureCluster.repository.FloorPictureClusterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FloorPictureClusterServiceimpl implements FloorPictureClusterService {
    private final FloorPictureClusterRepository floorPictureClusterRepository;

    @Override
    public void updateFloorPictureList(FloorPictureListUpdateRequest floorPictureListUpdateRequest) {
        floorPictureClusterRepository.updateFloorPictureList(floorPictureListUpdateRequest.getFloorPictureClusterPKDto(),
                floorPictureListUpdateRequest.getFloorPicture());
    }
}
