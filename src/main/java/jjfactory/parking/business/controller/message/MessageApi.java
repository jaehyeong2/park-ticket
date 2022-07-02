package jjfactory.parking.business.controller.message;

import jjfactory.parking.business.service.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MessageApi {
    private final MessageService messageService;
}
