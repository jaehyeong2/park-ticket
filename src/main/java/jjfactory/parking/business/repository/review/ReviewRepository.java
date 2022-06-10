package jjfactory.parking.business.repository.review;

import jjfactory.parking.business.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
