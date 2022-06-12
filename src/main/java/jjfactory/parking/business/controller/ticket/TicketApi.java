package jjfactory.parking.business.controller.ticket;

import jjfactory.parking.business.dto.ticket.TicketDto;
import jjfactory.parking.business.dto.ticket.TicketResponse;
import jjfactory.parking.business.service.ticket.TicketService;
import jjfactory.parking.global.config.auth.PrincipalDetails;
import jjfactory.parking.global.dto.ApiPagingResponse;
import jjfactory.parking.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/tickets")
@RestController
public class TicketApi {
    private final TicketService ticketService;

    @GetMapping("/{ticketId}")
    public ApiResponse<TicketResponse> findTicket(@PathVariable Long ticketId){
        return new ApiResponse<>(ticketService.findTicket(ticketId));
    }

    @GetMapping("")
    public ApiPagingResponse<TicketResponse> findTickets(@RequestParam(required = false, defaultValue = "1") int page){
        return new ApiPagingResponse<>(ticketService.findTickets(page));
    }

    @PostMapping("")
    public ApiResponse<String> createTicket(@RequestBody TicketDto dto,
                                            @AuthenticationPrincipal PrincipalDetails principal){
        return new ApiResponse<>(ticketService.create(dto,principal.getUser()));
    }

    @DeleteMapping("/{ticketId}")
    public ApiResponse<String> deleteTicket(@PathVariable Long ticketId,
                                            @AuthenticationPrincipal PrincipalDetails principal){
        return new ApiResponse<>(ticketService.delete(ticketId,principal.getUser()));
    }
}
