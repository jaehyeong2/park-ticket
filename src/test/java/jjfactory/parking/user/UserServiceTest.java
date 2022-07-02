package jjfactory.parking.user;

import jjfactory.parking.business.domain.user.User;
import jjfactory.parking.business.repository.user.UserRepository;
import jjfactory.parking.business.service.user.UserService;
import jjfactory.parking.global.handler.ex.BusinessException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("닉네임 중복체크")
    void nicknameCheck() {
        User user = User.builder().nickname("닉네임1").build();
        userRepository.save(user);

        Assertions.assertThatThrownBy(() -> {
            userService.checkDuplicateNickname("닉네임1");
        }).isInstanceOf(BusinessException.class);
    }

    @Test
    void 닉네임으로찾기() {
        User user = User.builder().nickname("닉네임1").username("유저네임").build();
        userRepository.save(user);

        User findUser = userRepository.findByNickname("닉네임1");

        Assertions.assertThat(findUser.getUsername()).isEqualTo("유저네임");
    }
}
