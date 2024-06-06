package Challengesemester2024.domain.facility.floorSize.repository;

import Challengesemester2024.domain.center.childCenter.model.ChildCenter;
import Challengesemester2024.domain.facility.floorSize.model.FloorSize;
import Challengesemester2024.domain.facility.floorSize.model.QFloorSize;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class    FloorSizeRepositoryImpl implements FloorSizeRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public FloorSize isExitsFloorSize (int displayIndex, ChildCenter childCenter) {
        QFloorSize floorSize = QFloorSize.floorSize;

        return queryFactory.selectFrom(floorSize)
                .where(floorSize.displayIndex.eq(displayIndex)
                        .and(floorSize.facilityIntroduction.eq(childCenter.getFacilityIntroduction())))
                .fetchOne();
    }

}
