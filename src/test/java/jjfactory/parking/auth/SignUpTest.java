package jjfactory.parking.auth;

import jjfactory.parking.business.domain.user.User;
import jjfactory.parking.business.repository.user.UserRepository;
import jjfactory.parking.business.service.user.AuthService;
import jjfactory.parking.global.handler.ex.BusinessException;
import jjfactory.parking.global.handler.ex.ErrorCode;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class SignUpTest {

    @Autowired AuthService authService;
    @Autowired
    UserRepository userRepository;


    @Test
    void duplicateCheck(){
        User user = User.builder().username("lee").build();
        userRepository.save(user);

        Assertions.assertThrows(BusinessException.class, () ->{
            authService.duplicateCheck("lee");
        });
    }

}
