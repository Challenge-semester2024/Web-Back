package Challengesemester2024.domain.facility.facade;

import Challengesemester2024.Exception.collections.InputValid.InvalidClientRequest;
import Challengesemester2024.SpringSecurity.authentication.SecurityUtils;
import Challengesemester2024.businessProcess.facade.service.DatabaseFacadeService;
import Challengesemester2024.domain.facility.dto.CreateDbWhenUpdateFloorPictureDto;
import Challengesemester2024.domain.facility.dto.FacilityFloorSizeUpdateRequest;
import Challengesemester2024.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import Challengesemester2024.domain.facility.facilityIntroduction.repository.FacilityIntroRepository;
import Challengesemester2024.domain.facility.facilityIntroduction.service.FacilityService;
import Challengesemester2024.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import Challengesemester2024.domain.facility.floorPictureCluster.service.FloorPictureClusterService;
import Challengesemester2024.domain.facility.floorPicutre.dto.FloorPictureDto;
import Challengesemester2024.domain.facility.floorSize.dto.UpdateFloorSizeDto;
import Challengesemester2024.domain.facility.floorSize.service.FloorSizeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FacilityFacadeServiceImpl implements FacilityFacadeService {
    private final SecurityUtils securityUtils;
    private final FacilityService facilityService;
    private final FloorSizeService floorSizeService;
    private final FloorPictureClusterService floorPictureClusterService;
    private final FacilityIntroRepository facilityIntroRepository;
    private final DatabaseFacadeService databaseFacadeService;
    private static Map<String, MultipartFile> multipartFileMap = new HashMap<>();

    @Transactional
    @Override
    public void createFloorSize(List<UpdateFloorSizeDto> updateFloorSizeDtoList) {
        //1. 인증된 이메일 가져오기
        Authentication authenticationEmail = securityUtils.getAuthenticationEmail();

        for(UpdateFloorSizeDto updateFloorSizeDto : updateFloorSizeDtoList){
            //2. 층별규모에 db 생성
            FacilityFloorSizeUpdateRequest facilityFloorSizeUpdateRequest = floorSizeService.createFloorSize(updateFloorSizeDto, authenticationEmail);
            //3. 시설db에 양방향 매핑 위한 db 업데이트
            facilityService.updateFacilityFloorSizeList(facilityFloorSizeUpdateRequest);
        }

    }

    @Transactional
    @Override
    public void createOrUpdateFloorPicture (List<FloorPictureDto> createFloorPictureDto, List<MultipartFile> multipartFile) {
        // 1. 인증된 이메일 가져오기
        Authentication authentication = securityUtils.getAuthenticationEmail();
        // 2. 해당 이메일로 관리자->보육원->시설소개 pk 찾아오기
        FacilityIntroduction facilityIntroduction = facilityIntroRepository.getFacilityPk(authentication);
        //3. 각 객체 매핑시키기

        //3-1 hashMap 변환
        convertHashMap(multipartFile);

        // TODO 1 : 최적화가 무조건 필요한 부분. 반복문 안에 find를(db접근 함수-jpa) 넣는 건 좋지않다. -> db의 접근빈도수가 너무 늘어버림 -> 네트워크 많아짐 -> 멀티스레드임 -> 성능악화 최고원인
        // TODO 2 : SRP가 안되어있는 것 같음. 고칠 필요가 있어보임.
        // 차라리 floorPictureDto에 대한 값을 db로 한 번의 네트워크로 모두 조회를 하되, 해당 결과를 List에 담아서 반환하자.
        //3-2 각 dto 매핑
        for (FloorPictureDto find : createFloorPictureDto) { //db 접근 횟수를 줄여야한다. 네트워크를 줄이는 습관을 길러야함. -> bulk 조회..? -> 한 번에 조회해오는 기능 ->
            MultipartFile file = multipartFileMap.get(find.getImageName());

            if (file == null) {
                throw new InvalidClientRequest();
            }

            FloorPictureCluster floorPictureCluster = floorPictureClusterService.findByFloor(find.getFloor());

            // 1. dto 속 floor의 loorPictureCluster DB 존재하는 경우 -> 수정
            if (floorPictureCluster != null) {
                databaseFacadeService.updateDbWhenModifyFloorPicture(find);
            }
            // 2. dto 속 floor의 floorPictureCluster DB 존재하지 않는 경우 -> 생성
            else {

                CreateDbWhenUpdateFloorPictureDto createDbDto = CreateDbWhenUpdateFloorPictureDto.builder()
                        .floorPictureDto(find)
                        .facilityIntroduction(facilityIntroduction)
                        .authentication(authentication)
                        .build();

                databaseFacadeService.createDbWhenUpdateFloorPicute(createDbDto);
            }
        }
        multipartFileMap.clear();
    }



    private void convertHashMap(List<MultipartFile> multipartFile) {

        for (MultipartFile file : multipartFile) {
            multipartFileMap.put(file.getOriginalFilename(), file); // <파일이름, 파일> 으로 hashMap 변환
        }

    }

}
