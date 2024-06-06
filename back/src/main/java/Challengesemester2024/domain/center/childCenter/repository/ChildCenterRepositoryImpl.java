package Challengesemester2024.domain.center.childCenter.repository;

import Challengesemester2024.Exception.collections.business.DatabaseNotFoundException;
import Challengesemester2024.Exception.message.DbExceptionMessage;
import Challengesemester2024.domain.center.childCenter.model.ChildCenter;
import Challengesemester2024.domain.center.childCenter.model.QChildCenter;
import Challengesemester2024.domain.manager.model.QManager;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

@RequiredArgsConstructor
public class ChildCenterRepositoryImpl implements ChildCenterRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public ChildCenter getChildCenterPk(Authentication authentication) {
        QManager qManager = QManager.manager;
        QChildCenter qChildCenter = QChildCenter.childCenter;

        ChildCenter fetchedChildCenter = jpaQueryFactory
                .select(qChildCenter)
                .from(qManager)
                .join(qManager.childCenter, qChildCenter)
                .where(qManager.emailId.eq(authentication.getName()))
                .fetchOne();

        if (fetchedChildCenter == null) {
            throw new DatabaseNotFoundException(DbExceptionMessage.ChildCenterDatabaseNotFoundException);
        }

        return fetchedChildCenter;

    }
}
