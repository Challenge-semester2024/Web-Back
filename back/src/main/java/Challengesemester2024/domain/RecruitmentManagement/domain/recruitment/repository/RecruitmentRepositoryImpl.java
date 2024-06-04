package Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.repository;

import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.model.Recruitment;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.model.QRecruitment.recruitment;

@RequiredArgsConstructor
public class RecruitmentRepositoryImpl implements RecruitmentRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Recruitment> findAllWithPagination(ChildCenter fetchedChildCenter, Pageable pageable) {
        List<Recruitment> recruitments = queryFactory
                .selectFrom(recruitment)
                .where(recruitment.childCenter.eq(fetchedChildCenter))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .selectFrom(recruitment)
                .where(recruitment.childCenter.eq(fetchedChildCenter))
                .fetchCount();

        return new PageImpl<>(recruitments, pageable, total);
    }

    @Override
    public Page<Recruitment> findByNameWithPagination(String recruitmentName, ChildCenter fetchedChildCenter, Pageable pageable) {
        List<Recruitment> recruitments = queryFactory
                .selectFrom(recruitment)
                .where(recruitment.name.contains(recruitmentName)
                        .and(recruitment.childCenter.eq(fetchedChildCenter)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .selectFrom(recruitment)
                .where(recruitment.name.contains(recruitmentName)
                        .and(recruitment.childCenter.eq(fetchedChildCenter)))
                .fetchCount();

        return new PageImpl<>(recruitments, pageable, total);
    }
}
