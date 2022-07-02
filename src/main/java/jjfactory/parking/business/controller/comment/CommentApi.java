package jjfactory.parking.business.controller.comment;

import jjfactory.parking.business.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentApi {
    private CommentService commentService;
}
