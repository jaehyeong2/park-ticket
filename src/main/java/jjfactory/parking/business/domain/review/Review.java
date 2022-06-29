package jjfactory.parking.business.domain.review;

import jjfactory.parking.business.domain.ticket.Ticket;
import jjfactory.parking.business.domain.user.User;
import jjfactory.parking.business.dto.review.ReviewDto;
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

    @JoinColumn(name = "buyer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User buyer;

    @JoinColumn(name = "seller_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User seller;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ticket ticket;
    
    @Comment("별점")
    private int score;

    private String content;

    @Builder
    public Review(User buyer, User seller, Ticket ticket, int score, String content) {
        this.buyer = buyer;
        this.seller = seller;
        this.ticket = ticket;
        this.score = score;
        this.content = content;
    }

    public static Review create(ReviewDto dto,User seller, User buyer,Ticket ticket){
        return builder()
                .content(dto.getContent())
                .buyer(buyer)
                .seller(seller)
                .ticket(ticket)
                .score(dto.getScore())
                .build();
    }
}
