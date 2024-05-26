package Challengesemester2024.domain.recruitment.repository;

import Challengesemester2024.domain.recruitment.model.QRecruitment;
import Challengesemester2024.domain.recruitment.model.Recruitment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class RecruitmentRepositoryImpl implements RecruitmentRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    @Override
    public Page<Recruitment> findAllWithPagination(Pageable pageable) {
        QRecruitment recruitment = QRecruitment.recruitment;

        List<Recruitment> recruitments = queryFactory.selectFrom(recruitment)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.selectFrom(recruitment).fetchCount();

        return new PageImpl<>(recruitments, pageable, total);
    }
}
