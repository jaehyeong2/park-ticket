package jjfactory.parking.business.repository.ticket;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.parking.business.dto.ticket.TicketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.parking.business.domain.ticket.QTicket.ticket;

@RequiredArgsConstructor
@Repository
public class TicketQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<TicketResponse> findTickets(Pageable pageable){
        List<TicketResponse> tickets = queryFactory.select(Projections.constructor(TicketResponse.class, ticket))
                .from(ticket)
                .orderBy(ticket.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int total = queryFactory.select(Projections.constructor(TicketResponse.class, ticket))
                .fetch().size();

        return new PageImpl<>(tickets,pageable,total);
    }
}
