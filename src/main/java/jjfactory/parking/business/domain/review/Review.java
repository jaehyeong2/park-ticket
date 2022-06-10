package jjfactory.parking.business.domain.review;

import jjfactory.parking.business.domain.ticket.Ticket;
import jjfactory.parking.business.domain.user.User;
import jjfactory.parking.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ticket ticket;
    
    @Comment("별점")
    private int score;

    private String content;

    @Builder
    public Review(User user, Ticket ticket, int score, String content) {
        this.user = user;
        this.ticket = ticket;
        this.score = score;
        this.content = content;
    }
}
