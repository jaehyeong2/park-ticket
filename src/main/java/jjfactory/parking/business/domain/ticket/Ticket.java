package jjfactory.parking.business.domain.ticket;

import jjfactory.parking.business.domain.user.User;
import jjfactory.parking.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Ticket extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String title;
    @Lob
    private String content;

    private int price;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;


}
