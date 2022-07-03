package jjfactory.parking.business.repository.message;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.parking.business.domain.message.QMessage;
import jjfactory.parking.business.dto.message.MessageRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.parking.business.domain.message.QMessage.*;

@Repository
@RequiredArgsConstructor
public class MessageQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<MessageRes> findMessageBySender(Long senderId, Pageable pageable){
        List<MessageRes> messageResList = queryFactory.select(Projections.constructor(MessageRes.class, message))
                .from(message)
                .where(message.sender.id.eq(senderId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(message.createDate.desc())
                .fetch();

        int total = queryFactory.select(Projections.constructor(MessageRes.class, message))
                .from(message).fetch().size();

        return new PageImpl<>(messageResList,pageable,total);
    }

    public Page<MessageRes> findMessageByReceiver(Long receiverId, Pageable pageable){
        List<MessageRes> messageResList = queryFactory.select(Projections.constructor(MessageRes.class, message))
                .from(message)
                .where(message.receiver.id.eq(receiverId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(message.createDate.desc())
                .fetch();

        int total = queryFactory.select(Projections.constructor(MessageRes.class, message))
                .from(message).fetch().size();

        return new PageImpl<>(messageResList,pageable,total);
    }
}
