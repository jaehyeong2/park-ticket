package jjfactory.parking.business.repository.history;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.parking.business.domain.history.QBuyHistory;
import jjfactory.parking.business.dto.history.HistoryRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.parking.business.domain.history.QBuyHistory.*;

@RequiredArgsConstructor
@Repository
public class HistoryQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<HistoryRes> findSalesHistories(Long userId, Pageable pageable){
        List<HistoryRes> histories = queryFactory.select(Projections.constructor(HistoryRes.class, buyHistory))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int total = queryFactory.select(Projections.constructor(HistoryRes.class, buyHistory))
                .fetch().size();

        return new PageImpl<>(histories,pageable,total);

    }
}
