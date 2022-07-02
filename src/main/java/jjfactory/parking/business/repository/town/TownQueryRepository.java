package jjfactory.parking.business.repository.town;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.parking.business.domain.location.QTown;
import jjfactory.parking.business.dto.location.TownRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.parking.business.domain.location.QTown.*;

@RequiredArgsConstructor
@Repository
public class TownQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<TownRes> findTowns(Pageable pageable){
        List<TownRes> result = queryFactory.select(Projections.constructor(TownRes.class, town))
                .from(town)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int total = queryFactory.select(Projections.constructor(TownRes.class,town))
                .fetch().size();

        return new PageImpl<>(result,pageable,total);
    }
}
