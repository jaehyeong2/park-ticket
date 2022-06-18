package jjfactory.parking.business.service.message;

import jjfactory.parking.business.dto.message.MessageDto;
import jjfactory.parking.business.repository.message.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public String create(MessageDto dto){
        return "y";
    }

    public String delete(){
        return "y";
    }

    public String update(){
        return "y";
    }
}
