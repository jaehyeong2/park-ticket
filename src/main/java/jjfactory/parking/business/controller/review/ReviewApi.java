package jjfactory.parking.business.controller.review;

import jjfactory.parking.business.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReviewApi {
    private final ReviewService reviewService;
}
