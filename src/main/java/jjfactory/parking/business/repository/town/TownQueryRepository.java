package jjfactory.parking.business.repository.town;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.parking.business.dto.location.TownRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TownQueryRepository {
    private final JPAQueryFactory queryFactory;

//    List<TownRes> findTownsByRegion(String region){
//        return queryFactory.select(Projections.constructor(TownRes.class,QTOW))
//    }
}
