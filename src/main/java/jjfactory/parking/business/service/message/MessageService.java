package jjfactory.parking.business.service.message;

import jjfactory.parking.business.domain.message.Message;
import jjfactory.parking.business.domain.user.User;
import jjfactory.parking.business.dto.message.MessageDto;
import jjfactory.parking.business.dto.message.MessageRes;
import jjfactory.parking.business.dto.message.MessageUpdateReq;
import jjfactory.parking.business.repository.message.MessageRepository;
import jjfactory.parking.business.repository.user.UserRepository;
import jjfactory.parking.global.handler.ex.BusinessException;
import jjfactory.parking.global.handler.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<MessageRes> findSendMessages(){
        return null;
    }

    @Transactional(readOnly = true)
    public Page<MessageRes> findReceiveMessages(){
        return null;
    }

    public String create(MessageDto dto){
        User receiver = getUser(dto.getReceiverId());
        User sender = getUser(dto.getSenderId());
        Message.create(dto,sender,receiver);
        return "y";
    }

    public String delete(Long id){
        Message message = getMessage(id);
        messageRepository.delete(message);
        return "y";
    }

    public String update(MessageUpdateReq dto){
        Message message = getMessage(dto.getSenderId());
        message.updateContent(dto.getContent());
        return "y";
    }

    private User getUser(Long dto) {
        User sender = userRepository.findById(dto).orElseThrow(() -> {
            throw new BusinessException(ErrorCode.NOT_FOUND_USER);
        });
        return sender;
    }

    private Message getMessage(Long id) {
        Message message = messageRepository.findById(id).orElseThrow(() -> {
            throw new BusinessException(ErrorCode.ENTITY_NOT_FOUND);
        });
        return message;
    }
}
