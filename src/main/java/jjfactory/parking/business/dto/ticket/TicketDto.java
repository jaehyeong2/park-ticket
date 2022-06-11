package jjfactory.parking.business.dto.ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TicketDto {
    private int price;
    private String title;
    private String content;
    private Long userId;
}
