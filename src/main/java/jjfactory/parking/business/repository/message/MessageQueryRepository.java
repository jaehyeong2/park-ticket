package jjfactory.parking.business.repository.message;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MessageQueryRepository {
    private final JPAQueryFactory queryFactory;
}
