package jjfactory.parking.business.repository.ticket;

import jjfactory.parking.business.domain.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
