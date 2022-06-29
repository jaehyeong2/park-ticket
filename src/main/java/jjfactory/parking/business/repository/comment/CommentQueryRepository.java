package jjfactory.parking.business.repository.comment;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.parking.business.domain.comment.QComment;
import jjfactory.parking.business.dto.comment.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.parking.business.domain.comment.QComment.*;

@RequiredArgsConstructor
@Repository
public class CommentQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<CommentResponse> findCommentsByTicketId(Long ticketId){
        return queryFactory.select(Projections.constructor(CommentResponse.class, comment))
                .leftJoin(comment.parent).fetchJoin()
                .from(comment)
                .orderBy(comment.parent.id.asc().nullsFirst(),comment.createDate.asc())
                .fetch();
    }
}
