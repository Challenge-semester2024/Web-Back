package Challengesemester2024.domain.recruitment.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecruitment is a Querydsl query type for Recruitment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecruitment extends EntityPathBase<Recruitment> {

    private static final long serialVersionUID = 1553344206L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecruitment recruitment = new QRecruitment("recruitment");

    public final Challengesemester2024.domain.childCenter.model.QChildCenter childCenter;

    public final NumberPath<Integer> currentApplicants = createNumber("currentApplicants", Integer.class);

    public final StringPath detailInfo = createString("detailInfo");

    public final DatePath<java.sql.Date> endDate = createDate("endDate", java.sql.Date.class);

    public final TimePath<java.sql.Time> endTime = createTime("endTime", java.sql.Time.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isRepeatedDate = createBoolean("isRepeatedDate");

    public final StringPath name = createString("name");

    public final DatePath<java.sql.Date> startDate = createDate("startDate", java.sql.Date.class);

    public final TimePath<java.sql.Time> startTime = createTime("startTime", java.sql.Time.class);

    public final NumberPath<Integer> totalApplicants = createNumber("totalApplicants", Integer.class);

    public final NumberPath<Integer> view = createNumber("view", Integer.class);

    public QRecruitment(String variable) {
        this(Recruitment.class, forVariable(variable), INITS);
    }

    public QRecruitment(Path<? extends Recruitment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecruitment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecruitment(PathMetadata metadata, PathInits inits) {
        this(Recruitment.class, metadata, inits);
    }

    public QRecruitment(Class<? extends Recruitment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.childCenter = inits.isInitialized("childCenter") ? new Challengesemester2024.domain.childCenter.model.QChildCenter(forProperty("childCenter"), inits.get("childCenter")) : null;
    }

}

