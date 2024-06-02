package Challengesemester2024.domain.volunteer.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVolunteer is a Querydsl query type for Volunteer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVolunteer extends EntityPathBase<Volunteer> {

    private static final long serialVersionUID = -1874714262L;

    public static final QVolunteer volunteer = new QVolunteer("volunteer");

    public final DatePath<java.time.LocalDate> birth = createDate("birth", java.time.LocalDate.class);

    public final StringPath emailId = createString("emailId");

    public final EnumPath<Volunteer.Gender> gender = createEnum("gender", Volunteer.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phoneNum = createString("phoneNum");

    public final ListPath<Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.model.RecruitmentWaiting, Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.model.QRecruitmentWaiting> recruitmentWaitings = this.<Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.model.RecruitmentWaiting, Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.model.QRecruitmentWaiting>createList("recruitmentWaitings", Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.model.RecruitmentWaiting.class, Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.model.QRecruitmentWaiting.class, PathInits.DIRECT2);

    public final EnumPath<Volunteer.UserRoleEnum> role = createEnum("role", Volunteer.UserRoleEnum.class);

    public QVolunteer(String variable) {
        super(Volunteer.class, forVariable(variable));
    }

    public QVolunteer(Path<? extends Volunteer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVolunteer(PathMetadata metadata) {
        super(Volunteer.class, metadata);
    }

}

