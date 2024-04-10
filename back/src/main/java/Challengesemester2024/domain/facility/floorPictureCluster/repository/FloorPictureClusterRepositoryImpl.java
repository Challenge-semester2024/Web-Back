package Challengesemester2024.domain.facility.floorPictureCluster.repository;

import Challengesemester2024.Exception.collections.business.DatabaseNotFoundException;
import Challengesemester2024.Exception.message.DbExceptionMessage;
import Challengesemester2024.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import Challengesemester2024.domain.facility.facilityIntroduction.model.QFacilityIntroduction;
import Challengesemester2024.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import Challengesemester2024.domain.facility.floorPictureCluster.model.QFloorPictureCluster;
import Challengesemester2024.domain.facility.floorPicutre.model.FloorPicture;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FloorPictureClusterRepositoryImpl implements FloorPictureClusterRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public FloorPictureCluster getFloorPicutreClusterPk (FacilityIntroduction facilityIntro) {

        QFacilityIntroduction facilityIntroduction = QFacilityIntroduction.facilityIntroduction;
        QFloorPictureCluster floorPictureCluster = QFloorPictureCluster.floorPictureCluster;

        //todo : 한 방 쿼리로 해결하는 방법도 있음. 뭐가 더 최선인지는 아직 모르겠음 나중에 프로파일링 등을 통해 어느것이 더 성능이 좋은지 확인해보기
        FloorPictureCluster fetchedFloorPictureCluster = jpaQueryFactory
                .select(floorPictureCluster)
                .from(floorPictureCluster)
                .join(floorPictureCluster.facilityIntroduction, facilityIntroduction) // 수정된 부분
                .where(facilityIntroduction.eq(facilityIntro)) // 예시 조건
                        //manager.emailId.eq(authenticatedEmail)) -> 해당 내용 제거 -> 이전에 시설소개 PK 가져오는 부분에서 이미 검증된 내용임
                .fetchOne();

        if (fetchedFloorPictureCluster== null) {
            throw new DatabaseNotFoundException(DbExceptionMessage.FloorPictureDatabaseNotFoundException);
        }

        return fetchedFloorPictureCluster;
    }

    @Override
    public void createFloorPictureList(FloorPictureCluster floorPictureCluster, FloorPicture floorPicture) {
        List<FloorPicture> floorPictureList = floorPictureCluster.getFloorPictureList();
        floorPictureList.add(floorPicture);
    }



}
