package jjfactory.parking.business.dto.ticket;

import jjfactory.parking.business.domain.ticket.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TicketResponse {
    private String content;
    private String title;
    private int price;
    private Long userId;

    public TicketResponse(Ticket ticket) {
        this.content = ticket.getContent();
        this.title = ticket.getContent();
        this.price = ticket.getPrice();
        this.userId = ticket.getUser().getId();
    }
}
