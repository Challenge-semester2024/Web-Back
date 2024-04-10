package Challengesemester2024.domain.greetings.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GreetingRepositoryCustomImpl implements GreetingRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;


}
