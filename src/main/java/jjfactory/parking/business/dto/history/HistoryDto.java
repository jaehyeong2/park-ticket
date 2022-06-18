package jjfactory.parking.business.dto.history;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HistoryDto {
    private Long buyerId;
    private Long sellerId;
    private int price;
}
