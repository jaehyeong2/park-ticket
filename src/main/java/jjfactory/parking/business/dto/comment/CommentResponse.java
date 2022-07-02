package jjfactory.parking.business.dto.comment;

import jjfactory.parking.business.domain.comment.Comment;
import jjfactory.parking.global.entity.DeleteStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentResponse {
    private String content;
    private DeleteStatus status;
    private Long userId;
    private Long ticketId;

    public CommentResponse(Comment comment) {
        this.content = comment.getContent();
        this.status = comment.getDeleteStatus();
        this.userId = comment.getUser().getId();
        this.ticketId = comment.getTicket().getId();
    }
}
