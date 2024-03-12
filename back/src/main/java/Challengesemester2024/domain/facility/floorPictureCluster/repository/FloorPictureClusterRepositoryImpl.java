package Challengesemester2024.domain.facility.floorPictureCluster.repository;

import Challengesemester2024.Exception.collections.business.DatabaseNotFoundException;
import Challengesemester2024.SpringSecurity.authentication.AuthenticatedEmailDTO;
import Challengesemester2024.domain.childCenter.model.QChildCenter;
import Challengesemester2024.domain.facility.facilityIntroduction.model.QFacilityIntroduction;
import Challengesemester2024.domain.facility.floorPictureCluster.dto.GetFloorPictureClusterPKDto;
import Challengesemester2024.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import Challengesemester2024.domain.facility.floorPictureCluster.model.QFloorPictureCluster;
import Challengesemester2024.domain.facility.floorPicutre.model.FloorPicture;
import Challengesemester2024.domain.manager.model.QManager;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FloorPictureClusterRepositoryImpl implements FloorPictureClusterRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public GetFloorPictureClusterPKDto getFloorPicutreClusterPk(AuthenticatedEmailDTO authenticatedEmailDTO) {
        String email = authenticatedEmailDTO.getEmail();

        QManager manager = QManager.manager;
        QChildCenter childCenter = QChildCenter.childCenter;
        QFacilityIntroduction facilityIntroduction = QFacilityIntroduction.facilityIntroduction; // 가정한 시설소개 엔티티의 Q 타입
        QFloorPictureCluster floorPictureCluster = QFloorPictureCluster.floorPictureCluster;

        FloorPictureCluster fetchedfloorPictureCluster = jpaQueryFactory
                .select(floorPictureCluster)
                .from(manager)
                .join(manager.childCenter, childCenter)
                .join(childCenter.facilityIntroduction, facilityIntroduction)
                .join(facilityIntroduction.floorPictureClusters, floorPictureCluster)
                .where(manager.emailId.eq(email))
                .fetchOne();

        if (fetchedfloorPictureCluster== null) {
            throw new DatabaseNotFoundException();
        }

        GetFloorPictureClusterPKDto floorPictureClusterPKDto = GetFloorPictureClusterPKDto.builder()
                .floorPictureCluster(fetchedfloorPictureCluster)
                .build();

        return floorPictureClusterPKDto;
    }

    @Override
    public void updateFloorPictureList(GetFloorPictureClusterPKDto getFloorPictureClusterPKDto, FloorPicture floorPicture) {
        FloorPictureCluster floorPictureCluster = getFloorPictureClusterPKDto.getFloorPictureCluster();
        floorPictureCluster.getFloorPicture().add(floorPicture);
    }
}
