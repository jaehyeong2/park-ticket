package jjfactory.parking.business.repository.comment;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.parking.business.domain.comment.QComment;
import jjfactory.parking.business.dto.comment.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.parking.business.domain.comment.QComment.*;

@RequiredArgsConstructor
@Repository
public class CommentQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<CommentResponse> findCommentsByTicketId(Long ticketId, Pageable pageable){
        List<CommentResponse> result = queryFactory.select(Projections.constructor(CommentResponse.class, comment))
                .leftJoin(comment.parent).fetchJoin()
                .from(comment)
                .orderBy(comment.parent.id.asc().nullsFirst(), comment.createDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int total = queryFactory.select(Projections.constructor(CommentResponse.class, comment))
                .from(comment).fetch().size();
        return new PageImpl<>(result,pageable,total);
    }
}
