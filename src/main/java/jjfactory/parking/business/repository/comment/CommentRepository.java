package jjfactory.parking.business.repository.comment;

import jjfactory.parking.business.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
