package Challengesemester2024.domain.center.likeChildCenter.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikeCenter is a Querydsl query type for LikeCenter
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLikeCenter extends EntityPathBase<LikeCenter> {

    private static final long serialVersionUID = -1711214233L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikeCenter likeCenter = new QLikeCenter("likeCenter");

    public final Challengesemester2024.domain.center.childCenter.model.QChildCenter childCenter;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final Challengesemester2024.domain.volunteer.model.QVolunteer volunteer;

    public QLikeCenter(String variable) {
        this(LikeCenter.class, forVariable(variable), INITS);
    }

    public QLikeCenter(Path<? extends LikeCenter> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikeCenter(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikeCenter(PathMetadata metadata, PathInits inits) {
        this(LikeCenter.class, metadata, inits);
    }

    public QLikeCenter(Class<? extends LikeCenter> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.childCenter = inits.isInitialized("childCenter") ? new Challengesemester2024.domain.center.childCenter.model.QChildCenter(forProperty("childCenter"), inits.get("childCenter")) : null;
        this.volunteer = inits.isInitialized("volunteer") ? new Challengesemester2024.domain.volunteer.model.QVolunteer(forProperty("volunteer")) : null;
    }

}

