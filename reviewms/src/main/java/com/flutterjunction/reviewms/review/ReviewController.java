package com.flutterjunction.reviewms.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestParam Long companyId, @RequestBody Review review) {
        boolean isSuccess = reviewService.createReview(companyId, review);
        if (isSuccess) {
            return new ResponseEntity<>("Review added Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review not Saved", HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getReview(reviewId), HttpStatus.OK);

    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
      boolean isDeleted=  reviewService.deleteReview(reviewId);
      if(isDeleted){
          return new ResponseEntity<>("Deleted Review Successfully", HttpStatus.OK);
      }else{
          return new ResponseEntity<>("Review delete failed", HttpStatus.BAD_REQUEST);
      }

    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Review updatedReview) {

//        reviewService.getReviewById(companyId,reviewId);

        boolean isUpdated = reviewService.updateReview(reviewId, updatedReview);
        if (isUpdated)
            return new ResponseEntity<>("Review updated  SuccessFully", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Review update Fail",HttpStatus.NOT_FOUND);
    }


}
