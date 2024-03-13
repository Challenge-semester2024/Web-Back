package Challengesemester2024.domain.facility.facilityIntroduction.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFacilityIntroduction is a Querydsl query type for FacilityIntroduction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFacilityIntroduction extends EntityPathBase<FacilityIntroduction> {

    private static final long serialVersionUID = -1320390913L;

    public static final QFacilityIntroduction facilityIntroduction = new QFacilityIntroduction("facilityIntroduction");

    public final ListPath<Challengesemester2024.domain.facility.floorPictureCluster.model.FloorPictureCluster, Challengesemester2024.domain.facility.floorPictureCluster.model.QFloorPictureCluster> floorPictureClusters = this.<Challengesemester2024.domain.facility.floorPictureCluster.model.FloorPictureCluster, Challengesemester2024.domain.facility.floorPictureCluster.model.QFloorPictureCluster>createList("floorPictureClusters", Challengesemester2024.domain.facility.floorPictureCluster.model.FloorPictureCluster.class, Challengesemester2024.domain.facility.floorPictureCluster.model.QFloorPictureCluster.class, PathInits.DIRECT2);

    public final ListPath<Challengesemester2024.domain.facility.floorSize.model.FloorSize, Challengesemester2024.domain.facility.floorSize.model.QFloorSize> floorSizes = this.<Challengesemester2024.domain.facility.floorSize.model.FloorSize, Challengesemester2024.domain.facility.floorSize.model.QFloorSize>createList("floorSizes", Challengesemester2024.domain.facility.floorSize.model.FloorSize.class, Challengesemester2024.domain.facility.floorSize.model.QFloorSize.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> totalArea = createNumber("totalArea", Integer.class);

    public QFacilityIntroduction(String variable) {
        super(FacilityIntroduction.class, forVariable(variable));
    }

    public QFacilityIntroduction(Path<? extends FacilityIntroduction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFacilityIntroduction(PathMetadata metadata) {
        super(FacilityIntroduction.class, metadata);
    }

}

