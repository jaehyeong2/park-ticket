package jjfactory.parking.business.domain.comment;

import jjfactory.parking.business.domain.ticket.Ticket;
import jjfactory.parking.business.domain.user.User;
import jjfactory.parking.business.dto.comment.CommentDto;
import jjfactory.parking.global.entity.BaseTimeEntity;
import jjfactory.parking.global.entity.DeleteStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.sql.Delete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ticket ticket;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment parent;

    @OneToMany(mappedBy = "parent",orphanRemoval = true)
    private List<Comment> childList = new ArrayList<>();

    @Builder
    public Comment(User user, Ticket ticket, String content, DeleteStatus deleteStatus, Comment parent, List<Comment> childList) {
        this.user = user;
        this.ticket = ticket;
        this.content = content;
        this.deleteStatus = deleteStatus;
        this.parent = parent;
        this.childList = childList;
    }

    public static Comment create(CommentDto dto,User user,Comment parent,Ticket ticket){
        return builder()
                .content(dto.getContent())
                .deleteStatus(DeleteStatus.NON_DELETED)
                .user(user)
                .parent(parent)
                .ticket(ticket)
                .build();
    }

    public void update(String content) {
        this.content = content;
    }

    public void delete() {
        this.deleteStatus = DeleteStatus.DELETED;
    }
}
