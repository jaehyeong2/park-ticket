package jjfactory.parking.business.service.ticket;

import jjfactory.parking.business.domain.ticket.Ticket;
import jjfactory.parking.business.domain.user.User;
import jjfactory.parking.business.dto.ticket.TicketDto;
import jjfactory.parking.business.dto.ticket.TicketResponse;
import jjfactory.parking.business.dto.user.UserResponse;
import jjfactory.parking.business.repository.ticket.TicketRepository;
import jjfactory.parking.business.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public TicketResponse findTicket(Long ticketId){
        Ticket ticket = getTicket(ticketId);
        return new TicketResponse(ticket);
    }

    public String create(TicketDto dto){
        User user = getUser(dto);
        Ticket ticket = Ticket.create(dto, user);
        ticketRepository.save(ticket);
        return "Y";
    }

    public String delete(Long id){
        Ticket ticket = getTicket(id);
        ticket.deleteTicket();
        return "Y";
    }

    @Transactional(readOnly = true)
    private Ticket getTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("조회실패");
        });
        return ticket;
    }

    @Transactional(readOnly = true)
    private User getUser(TicketDto dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> {
            throw new NoSuchElementException("조회실패");
        });
        return user;
    }
}
