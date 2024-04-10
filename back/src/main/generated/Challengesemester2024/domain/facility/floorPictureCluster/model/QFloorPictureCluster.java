package Challengesemester2024.domain.facility.floorPictureCluster.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFloorPictureCluster is a Querydsl query type for FloorPictureCluster
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFloorPictureCluster extends EntityPathBase<FloorPictureCluster> {

    private static final long serialVersionUID = 133127017L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFloorPictureCluster floorPictureCluster = new QFloorPictureCluster("floorPictureCluster");

    public final Challengesemester2024.domain.facility.facilityIntroduction.model.QFacilityIntroduction facilityIntroduction;

    public final NumberPath<Integer> floor = createNumber("floor", Integer.class);

    public final ListPath<Challengesemester2024.domain.facility.floorPicutre.model.FloorPicture, Challengesemester2024.domain.facility.floorPicutre.model.QFloorPicture> floorPictureList = this.<Challengesemester2024.domain.facility.floorPicutre.model.FloorPicture, Challengesemester2024.domain.facility.floorPicutre.model.QFloorPicture>createList("floorPictureList", Challengesemester2024.domain.facility.floorPicutre.model.FloorPicture.class, Challengesemester2024.domain.facility.floorPicutre.model.QFloorPicture.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QFloorPictureCluster(String variable) {
        this(FloorPictureCluster.class, forVariable(variable), INITS);
    }

    public QFloorPictureCluster(Path<? extends FloorPictureCluster> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFloorPictureCluster(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFloorPictureCluster(PathMetadata metadata, PathInits inits) {
        this(FloorPictureCluster.class, metadata, inits);
    }

    public QFloorPictureCluster(Class<? extends FloorPictureCluster> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.facilityIntroduction = inits.isInitialized("facilityIntroduction") ? new Challengesemester2024.domain.facility.facilityIntroduction.model.QFacilityIntroduction(forProperty("facilityIntroduction")) : null;
    }

}

