package Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaiting.repository;

import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RequestVolunteersByDate;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RequestAssignmentDto;
import Challengesemester2024.domain.center.childCenter.model.ChildCenter;
import Challengesemester2024.domain.volunteer.model.Volunteer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.model.QRecruitment.recruitment;
import static Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaiting.model.QRecruitmentWaiting.recruitmentWaiting;

@RequiredArgsConstructor
public class RecruitmentWaitingRepositoryImpl implements RecruitmentWaitingRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Volunteer> findVolunteersByDate(RequestVolunteersByDate request, ChildCenter fetchedChildCenter) {
        return queryFactory
                .select(recruitmentWaiting.volunteer)
                .from(recruitmentWaiting)
                .join(recruitmentWaiting.recruitment, recruitment)
                .where(recruitment.id.eq(request.getRecruitmentId())
                        .and(recruitment.childCenter.eq(fetchedChildCenter))
                        .and(recruitmentWaiting.recruitmentDates.any().eq(request.getLocalDate())))
                .fetch();
    }

    @Override
    public boolean isDuplicateRecruitment(Volunteer volunteer, RequestAssignmentDto requestAssignmentDto) {
        return queryFactory
                .selectFrom(recruitmentWaiting)
                .join(recruitmentWaiting.recruitment, recruitment)
                .where(recruitment.id.eq(requestAssignmentDto.getRecruitmentId())
                        .and(recruitmentWaiting.volunteer.eq(volunteer))
                        .and(recruitmentWaiting.recruitmentDates.any().in(requestAssignmentDto.getRecruitmentDates())))
                .fetch().size() > 0;
    }

}
