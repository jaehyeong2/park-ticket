package jjfactory.parking.business.domain.ticket;

import jjfactory.parking.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class TicketImage extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ticket ticket;

    private String path;
    private String basePath;

    @Builder
    public TicketImage(Ticket ticket, String path, String basePath) {
        this.ticket = ticket;
        this.path = path;
        this.basePath = basePath;
    }
}
