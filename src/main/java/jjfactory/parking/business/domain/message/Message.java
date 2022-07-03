package jjfactory.parking.business.domain.message;

import jjfactory.parking.business.domain.user.User;
import jjfactory.parking.business.dto.message.MessageDto;
import jjfactory.parking.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Message extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    private User receiver;

    @Enumerated(EnumType.STRING)
    private ReadStatus readStatus;

    @Lob
    private String content;

    @Builder
    public Message(User sender, User receiver, ReadStatus readStatus, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.readStatus = readStatus;
        this.content = content;
    }

    public static Message create(MessageDto dto,User sender,User receiver){
        return builder()
                .content(dto.getContent())
                .readStatus(ReadStatus.NON_READ)
                .receiver(receiver)
                .sender(sender)
                .build();
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
