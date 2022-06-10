package jjfactory.parking.business.repository.message;

import jjfactory.parking.business.domain.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
