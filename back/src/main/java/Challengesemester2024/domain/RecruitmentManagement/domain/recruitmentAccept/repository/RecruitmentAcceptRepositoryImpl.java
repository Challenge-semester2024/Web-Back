package Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentAccept.repository;

import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RequestVolunteersByDate;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentAccept.model.QRecruitmentAccept;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentAccept.model.RecruitmentAccept;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RequestAssignmentDto;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import Challengesemester2024.domain.volunteer.model.Volunteer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.model.QRecruitment.recruitment;
import static Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentAccept.model.QRecruitmentAccept.recruitmentAccept;

@RequiredArgsConstructor
public class RecruitmentAcceptRepositoryImpl implements RecruitmentAcceptRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Volunteer> findVolunteersByDate(RequestVolunteersByDate request, ChildCenter fetchedChildCenter) {
        return queryFactory
                .select(recruitmentAccept.volunteer)
                .from(recruitmentAccept)
                .join(recruitmentAccept.recruitment, recruitment)
                .where(recruitment.id.eq(request.getRecruitmentId())
                        .and(recruitment.childCenter.eq(fetchedChildCenter))
                        .and(recruitmentAccept.recruitmentDates.any().eq(request.getLocalDate())))
                .fetch();
    }

    @Override
    public Optional<RecruitmentAccept> findByRecruitmentIdAndVolunteerIdAndRecruitmentDate(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate) {
        QRecruitmentAccept recruitmentAccept = QRecruitmentAccept.recruitmentAccept;

        RecruitmentAccept result = queryFactory
                .selectFrom(recruitmentAccept)
                .where(recruitmentAccept.recruitment.id.eq(recruitmentId)
                        .and(recruitmentAccept.volunteer.id.eq(volunteerId))
                        .and(recruitmentAccept.recruitmentDates.any().eq(recruitmentDate)))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public boolean isDuplicateRecruitment(Volunteer volunteer, RequestAssignmentDto requestAssignmentDto) {
        return queryFactory
                .selectFrom(recruitmentAccept)
                .join(recruitmentAccept.recruitment, recruitment)
                .where(recruitment.id.eq(requestAssignmentDto.getRecruitmentId())
                        .and(recruitmentAccept.volunteer.eq(volunteer))
                        .and(recruitmentAccept.recruitmentDates.any().in(requestAssignmentDto.getRecruitmentDates())))
                .fetchCount() > 0;
    }


}
