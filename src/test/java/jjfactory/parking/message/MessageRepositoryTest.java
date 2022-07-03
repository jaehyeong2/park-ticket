package jjfactory.parking.message;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.parking.business.domain.message.Message;
import jjfactory.parking.business.domain.message.QMessage;
import jjfactory.parking.business.domain.user.User;
import jjfactory.parking.business.dto.message.MessageDto;
import jjfactory.parking.business.dto.message.MessageRes;
import jjfactory.parking.business.repository.message.MessageQueryRepository;
import jjfactory.parking.business.repository.message.MessageRepository;
import jjfactory.parking.business.repository.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
//@DataJpaTest
public class MessageRepositoryTest {

    @Autowired
    EntityManager em;
    JPAQueryFactory queryFactory;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @BeforeEach
    void init(){
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    void createMessage(){
        User user1 = User.builder().name("유저1").build();
        User user2 = User.builder().name("유저2").build();
        List<User> users = Arrays.asList(user1, user2);

        userRepository.saveAll(users);

        MessageDto dto = new MessageDto(user1.getId(), user2.getId(), "안녕 !");

        Message message = Message.create(dto, user1, user2);
        messageRepository.save(message);

        List<Message> all = messageRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }


    @Test
    void findMessageByReceiverId(){
        User user1 = User.builder().name("유저1").build();
        User user2 = User.builder().name("유저2").build();
        List<User> users = Arrays.asList(user1, user2);
        userRepository.saveAll(users);

        MessageDto dto = new MessageDto(user1.getId(), user2.getId(), "안녕 !");

        Message message = Message.create(dto, user1, user2);
        messageRepository.save(message);

        MessageRes res = queryFactory.select(Projections.constructor(MessageRes.class, QMessage.message))
                .from(QMessage.message)
                .where(QMessage.message.sender.id.eq(user1.getId()))
                .fetchOne();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(res.getContent()).isEqualTo("안녕 !");
        softAssertions.assertThat(res.getSenderId()).isEqualTo(user1.getId());
        softAssertions.assertThat(res.getReceiverId()).isEqualTo(user2.getId());
        softAssertions.assertAll();
    }
}
