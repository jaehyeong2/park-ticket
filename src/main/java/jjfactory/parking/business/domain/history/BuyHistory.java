package jjfactory.parking.business.domain.history;

import jjfactory.parking.business.domain.ticket.Ticket;
import jjfactory.parking.business.domain.user.User;
import jjfactory.parking.business.dto.history.HistoryDto;
import jjfactory.parking.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class BuyHistory extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ticket ticket;

    @JoinColumn(name = "buyer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User buyer;

    @JoinColumn(name = "seller_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User seller;

    private int price;

    @Builder
    public BuyHistory(User buyer, User seller, int price) {
        this.buyer = buyer;
        this.seller = seller;
        this.price = price;
    }

    public static BuyHistory create(HistoryDto dto,User buyer,User seller){
        return builder()
                .buyer(buyer)
                .seller(seller)
                .price(dto.getPrice())
                .build();
    }
}
