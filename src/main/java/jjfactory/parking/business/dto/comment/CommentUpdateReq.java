package jjfactory.parking.business.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentUpdateReq {
    private Long commentId;
    private String content;
}
