package jjfactory.parking.business.service.ticket;

import jjfactory.parking.business.repository.ticket.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class TicketService {
    private final TicketRepository ticketRepository;
}
