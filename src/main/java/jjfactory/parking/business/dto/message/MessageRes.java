package jjfactory.parking.business.dto.message;

import jjfactory.parking.business.domain.message.ReadStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MessageRes {
    private Long senderId;
    private Long receiverId;
    private String content;
    private ReadStatus status;
}
