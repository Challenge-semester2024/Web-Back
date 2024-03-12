package Challengesemester2024.domain.facility.floorPicutre.service;

import Challengesemester2024.SpringSecurity.authentication.AuthenticatedEmailDTO;
import Challengesemester2024.domain.facility.dto.FloorPictureListUpdateRequest;
import Challengesemester2024.domain.facility.floorPictureCluster.dto.GetFloorPictureClusterPKDto;
import Challengesemester2024.domain.facility.floorPictureCluster.repository.FloorPictureClusterRepository;
import Challengesemester2024.domain.facility.floorPicutre.dto.UpdateFloorPictureDto;
import Challengesemester2024.domain.facility.floorPicutre.model.FloorPicture;
import Challengesemester2024.domain.facility.floorPicutre.repository.FloorPictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FloorPictureServiceImpl implements FloorPictureService {
    private final FloorPictureClusterRepository floorPictureClusterRepository;
    private final FloorPictureRepository floorPictureRepository;

    @Override
    public FloorPictureListUpdateRequest updateFloorPicture(UpdateFloorPictureDto updateFloorPictureDto, AuthenticatedEmailDTO authenticatedEmailDTO) {
        //1. 층별사진모음 db의 pk값 가져옴
        GetFloorPictureClusterPKDto getFloorPictureClusterPKDto = floorPictureClusterRepository.getFloorPicutreClusterPk(authenticatedEmailDTO);

        //2. db 업데이트
        FloorPicture floorPicture = FloorPicture.builder()
                .floor(updateFloorPictureDto.getFloor())
                .purpose(updateFloorPictureDto.getPurpose())
                .floorPictureCluster(getFloorPictureClusterPKDto.getFloorPictureCluster())
                .build();

        floorPictureRepository.save(floorPicture);

        //3. 양방향 매핑 위해 floorPicureClusterPk와 업데이트 된 db 반환
        FloorPictureListUpdateRequest floorPictureListUpdateRequest = FloorPictureListUpdateRequest.builder()
                .floorPictureClusterPKDto(getFloorPictureClusterPKDto)
                .floorPicture(floorPicture)
                .build();

        return floorPictureListUpdateRequest;
    }


}
