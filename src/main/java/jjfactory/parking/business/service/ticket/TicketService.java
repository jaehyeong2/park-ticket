package jjfactory.parking.business.service.ticket;

import jjfactory.parking.business.domain.ticket.Ticket;
import jjfactory.parking.business.domain.user.User;
import jjfactory.parking.business.dto.ticket.TicketDto;
import jjfactory.parking.business.dto.ticket.TicketResponse;
import jjfactory.parking.business.repository.ticket.TicketQueryRepository;
import jjfactory.parking.business.repository.ticket.TicketRepository;
import jjfactory.parking.business.repository.user.UserRepository;
import jjfactory.parking.global.dto.MyPageRequest;
import jjfactory.parking.global.dto.PagingResponse;
import jjfactory.parking.global.handler.ex.BusinessException;
import jjfactory.parking.global.handler.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketQueryRepository ticketQueryRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public TicketResponse findTicket(Long ticketId){
        Ticket ticket = getTicket(ticketId);
        return new TicketResponse(ticket);
    }

    @Transactional(readOnly = true)
    public PagingResponse<TicketResponse> findTickets(int page){
        Pageable pageRequest = new MyPageRequest(page,10).of();
        Page<TicketResponse> tickets = ticketQueryRepository.findTickets(pageRequest);

        return new PagingResponse<>(tickets);
    }

    public String create(TicketDto dto,User user){
        Ticket ticket = Ticket.create(dto, user);
        ticketRepository.save(ticket);
        return "Y";
    }

    public String delete(Long id,User user){
        Ticket ticket = getTicket(id);
        if (!ticket.getUser().equals(user)) throw new BusinessException(ErrorCode.HANDLE_ACCESS_DENIED);
        ticket.deleteTicket();
        return "Y";
    }

    @Transactional(readOnly = true)
    private Ticket getTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> {
            throw new BusinessException(ErrorCode.ENTITY_NOT_FOUND);
        });
        return ticket;
    }

    @Transactional(readOnly = true)
    private User getUser(TicketDto dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> {
            throw new BusinessException(ErrorCode.ENTITY_NOT_FOUND);
        });
        return user;
    }
}
