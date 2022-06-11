package jjfactory.parking.business.domain.ticket;

import jjfactory.parking.business.domain.user.User;
import jjfactory.parking.business.dto.ticket.TicketDto;
import jjfactory.parking.global.entity.BaseTimeEntity;
import jjfactory.parking.global.entity.DeleteStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

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

    @Comment("ON : 판매중 , COMP : 판매완료")
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

    public void deleteTicket() {
        this.deleteStatus = DeleteStatus.DELETED;
    }

    @Builder
    public Ticket(User user, String title, String content, int price, TicketStatus ticketStatus, DeleteStatus deleteStatus) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.price = price;
        this.ticketStatus = ticketStatus;
        this.deleteStatus = deleteStatus;
    }

    public static Ticket create(TicketDto dto,User user){
        return builder()
                .user(user)
                .ticketStatus(TicketStatus.ON)
                .deleteStatus(DeleteStatus.NON_DELETED)
                .price(dto.getPrice())
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }
}
