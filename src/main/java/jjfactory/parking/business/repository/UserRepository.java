package jjfactory.parking.business.repository;

import jjfactory.parking.business.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
