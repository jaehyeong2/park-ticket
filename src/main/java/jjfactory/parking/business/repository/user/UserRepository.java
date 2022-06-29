package jjfactory.parking.business.repository.user;

import jjfactory.parking.business.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByNickname(String nickname);

}
