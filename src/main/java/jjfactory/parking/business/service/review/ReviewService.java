package jjfactory.parking.business.service.review;

import jjfactory.parking.business.dto.review.ReviewDto;
import jjfactory.parking.business.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewService {
    private final ReviewRepository repository;

    public String create(ReviewDto dto){
    return "y";
    }

    public String delete(){
        return "y";
    }

    public String update(){
        return "y";
    }
}
