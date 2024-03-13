package Challengesemester2024.domain.facility.floorPictureCluster.repository;

import Challengesemester2024.Exception.collections.business.DatabaseNotFoundException;
import Challengesemester2024.domain.facility.facilityIntroduction.dto.GetFacilityIntroPKDto;
import Challengesemester2024.domain.facility.facilityIntroduction.model.QFacilityIntroduction;
import Challengesemester2024.domain.facility.floorPictureCluster.dto.GetFloorPictureClusterPKDto;
import Challengesemester2024.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import Challengesemester2024.domain.facility.floorPictureCluster.model.QFloorPictureCluster;
import Challengesemester2024.domain.facility.floorPicutre.model.FloorPicture;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FloorPictureClusterRepositoryImpl implements FloorPictureClusterRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public GetFloorPictureClusterPKDto getFloorPicutreClusterPk (GetFacilityIntroPKDto getFacilityIntroPKDto) {

        QFacilityIntroduction facilityIntroduction = QFacilityIntroduction.facilityIntroduction;
        QFloorPictureCluster floorPictureCluster = QFloorPictureCluster.floorPictureCluster;

        FloorPictureCluster fetchedFloorPictureCluster = jpaQueryFactory
                .select(floorPictureCluster)
                .from(floorPictureCluster)
                .join(floorPictureCluster.facilityIntroduction, facilityIntroduction) // 수정된 부분
                .where(facilityIntroduction.eq(getFacilityIntroPKDto.getFacilityIntroduction())) // 예시 조건
                        //manager.emailId.eq(authenticatedEmail)) -> 해당 내용 제거 -> 이전에 시설소개 PK 가져오는 부분에서 이미 검증된 내용임
                .fetchOne();

        if (fetchedFloorPictureCluster== null) {
            throw new DatabaseNotFoundException();
        }

        GetFloorPictureClusterPKDto floorPictureClusterPKDto = GetFloorPictureClusterPKDto.builder()
                .floorPictureCluster(fetchedFloorPictureCluster)
                .build();

        return floorPictureClusterPKDto;
    }

    @Override
    public void updateFloorPictureList(GetFloorPictureClusterPKDto getFloorPictureClusterPKDto, FloorPicture floorPicture) {
        FloorPictureCluster floorPictureCluster = getFloorPictureClusterPKDto.getFloorPictureCluster();
        floorPictureCluster.getFloorPicture().add(floorPicture);
    }
}
