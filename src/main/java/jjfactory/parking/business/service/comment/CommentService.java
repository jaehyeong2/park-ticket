package jjfactory.parking.business.service.comment;

import jjfactory.parking.business.domain.comment.Comment;
import jjfactory.parking.business.domain.ticket.Ticket;
import jjfactory.parking.business.domain.user.User;
import jjfactory.parking.business.dto.comment.CommentDto;
import jjfactory.parking.business.dto.comment.CommentResponse;
import jjfactory.parking.business.dto.comment.CommentUpdateReq;
import jjfactory.parking.business.repository.comment.CommentQueryRepository;
import jjfactory.parking.business.repository.comment.CommentRepository;
import jjfactory.parking.global.handler.ex.BusinessException;
import jjfactory.parking.global.handler.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentQueryRepository commentQueryRepository;
    @Transactional(readOnly = true)
    public CommentResponse findComment(Long id){
        Comment comment = getComment(id);
        return new CommentResponse(comment);
    }

    @Transactional(readOnly = true)
    public Page<CommentResponse> findCommentsByTicketId(Long ticketId, Pageable pageable){
        return commentQueryRepository.findCommentsByTicketId(ticketId,pageable);
    }

    public String createComment(CommentDto dto, User user, Ticket ticket){
        Comment parent = commentRepository.findById(dto.getParentId()).orElseThrow(() -> {
            throw new BusinessException(ErrorCode.ENTITY_NOT_FOUND);}
        );
        Comment comment = Comment.create(dto, user, parent, ticket);
        commentRepository.save(comment);
        return "y";
    }

    public String deleteComment(Long id){
        Comment comment = getComment(id);
        comment.delete();
        return "y";
    }

    public String updateContent(CommentUpdateReq dto){
        Comment comment = getComment(dto.getCommentId());

        comment.update(dto.getContent());
        return "y";
    }

    private Comment getComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> {
            throw new BusinessException(ErrorCode.ENTITY_NOT_FOUND);
        });
        return comment;
    }


}
