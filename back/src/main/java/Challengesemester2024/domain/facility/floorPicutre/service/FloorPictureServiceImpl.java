package Challengesemester2024.domain.facility.floorPicutre.service;

import Challengesemester2024.SpringSecurity.authentication.AuthenticatedEmailDTO;
import Challengesemester2024.domain.facility.dto.FloorPictureListUpdateRequest;
import Challengesemester2024.domain.facility.facilityIntroduction.dto.GetFacilityIntroPKDto;
import Challengesemester2024.domain.facility.facilityIntroduction.repository.FacilityIntroRepository;
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
    private final FacilityIntroRepository facilityIntroRepository; // 이게 꼬롬해. pk값 가져오는 함수만 쓸 건데 굳이 안받아오 되는 인터페이스를 주입 받은 기분이야.

    @Override
    public FloorPictureListUpdateRequest updateFloorPicture(UpdateFloorPictureDto updateFloorPictureDto, AuthenticatedEmailDTO authenticatedEmailDTO) {
        //1. 시설소개 pk값 가져옴
        GetFacilityIntroPKDto getFacilityIntroPKDto = facilityIntroRepository.getFacilityPk(authenticatedEmailDTO); //여기서 현재 이메일과 인증된 이메일 같은 지 검사함
        //2.시설소개 pk 값을 층별사진모음 pk 찾는 함수에 인자로 넣어줌
        GetFloorPictureClusterPKDto getFloorPictureClusterPKDto = floorPictureClusterRepository.getFloorPicutreClusterPk(getFacilityIntroPKDto);

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
