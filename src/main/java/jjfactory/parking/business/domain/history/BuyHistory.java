package jjfactory.parking.business.domain.history;

import jjfactory.parking.business.domain.user.User;
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
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private User seller;

    private int price;

    @Builder
    public BuyHistory(User buyer, User seller, int price) {
        this.buyer = buyer;
        this.seller = seller;
        this.price = price;
    }
}
