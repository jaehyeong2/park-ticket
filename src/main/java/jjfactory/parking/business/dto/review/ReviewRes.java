package jjfactory.parking.business.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReviewRes {
    private String content;
    private Long userId;
    private int score;
}
