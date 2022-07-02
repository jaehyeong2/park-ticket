package jjfactory.parking.ticket;

import jjfactory.parking.business.domain.ticket.Ticket;
import jjfactory.parking.business.domain.user.User;
import jjfactory.parking.business.dto.ticket.TicketResponse;
import jjfactory.parking.business.repository.ticket.TicketRepository;
import jjfactory.parking.business.repository.user.UserRepository;
import jjfactory.parking.business.service.ticket.TicketService;
import jjfactory.parking.global.dto.PagingResponse;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TicketServiceTest {

    @Autowired
    TicketService ticketService;

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    void findTickets() {
        User user = User.builder().name("이재형").build();
        userRepository.save(user);

        for (int i = 0; i < 15; i++) {
            Ticket ticket = Ticket.builder().title("티켓 1 판매").user(user).build();
            ticketRepository.save(ticket);
        }

//        Ticket ticket1 = Ticket.builder().title("티켓 1 판매").user(user).build();
//        Ticket ticket2 = Ticket.builder().title("티켓 2 판매").user(user).build();
//        Ticket ticket3 = Ticket.builder().title("티켓 3 판매").user(user).build();
//        Ticket ticket4 = Ticket.builder().title("티켓 4 판매").user(user).build();
//        Ticket ticket5 = Ticket.builder().title("티켓 5 판매").user(user).build();
//        Ticket ticket6 = Ticket.builder().title("티켓 1 판매").user(user).build();
//        Ticket ticket7 = Ticket.builder().title("티켓 2 판매").user(user).build();
//        Ticket ticket8 = Ticket.builder().title("티켓 3 판매").user(user).build();
//        Ticket ticket9 = Ticket.builder().title("티켓 4 판매").user(user).build();
//        Ticket ticket10 = Ticket.builder().title("티켓 5 판매").user(user).build();
//        Ticket ticket11 = Ticket.builder().title("티켓 1 판매").user(user).build();
//        Ticket ticket2 = Ticket.builder().title("티켓 2 판매").user(user).build();
//        Ticket ticket3 = Ticket.builder().title("티켓 3 판매").user(user).build();
//        Ticket ticket4 = Ticket.builder().title("티켓 4 판매").user(user).build();
//        Ticket ticket5 = Ticket.builder().title("티켓 5 판매").user(user).build();
//
//        List<Ticket> tickets = Arrays.asList(ticket1, ticket2, ticket3, ticket4, ticket5);
//        ticketRepository.saveAll(tickets);

        Long totalCount = ticketService.findTickets(1).getTotalCount();
        int totalPage = ticketService.findTickets(1).getTotalPage();
        int sizeOfPage1 = ticketService.findTickets(1).getDataList().size();
        int sizeOfPage2 = ticketService.findTickets(2).getDataList().size();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(totalCount).isEqualTo(15);
        softly.assertThat(totalPage).isEqualTo(2);
        softly.assertThat(sizeOfPage1).isEqualTo(10);
        softly.assertThat(sizeOfPage2).isEqualTo(5);

        softly.assertAll();
    }
}