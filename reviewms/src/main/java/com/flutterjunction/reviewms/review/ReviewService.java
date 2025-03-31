package com.flutterjunction.reviewms.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean createReview(Long companyId,Review review);
    Review getReview(Long reviewId);


    boolean deleteReview(Long reviewId);
    boolean updateReview( Long reviewId,Review review);

}
