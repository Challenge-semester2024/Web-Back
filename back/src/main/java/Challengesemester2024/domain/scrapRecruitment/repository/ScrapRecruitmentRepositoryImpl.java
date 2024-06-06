package Challengesemester2024.domain.scrapRecruitment.repository;

import Challengesemester2024.domain.scrapRecruitment.dto.ScrapRecruitmentDto;
import Challengesemester2024.domain.scrapRecruitment.model.QScrapRecruitment;
import Challengesemester2024.domain.scrapRecruitment.model.ScrapRecruitment;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScrapRecruitmentRepositoryImpl implements  ScrapRecruitmentRepositoryCustom {
    private final JPQLQueryFactory jpqlQueryFactory;

    @Override
    public ScrapRecruitment findScrapRecruitment(ScrapRecruitmentDto scrapRecruitmentDto){
        QScrapRecruitment qScrapRecruitment = QScrapRecruitment.scrapRecruitment;

        return jpqlQueryFactory
                .selectFrom(qScrapRecruitment)
                .where(qScrapRecruitment.volunteer.eq(scrapRecruitmentDto.getVolunteer())
                        .and(qScrapRecruitment.recruitment.eq(scrapRecruitmentDto.getRecruitment())))
                .fetchOne();
    }

}

