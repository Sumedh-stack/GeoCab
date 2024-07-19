package com.project.UberReviewService.Controller;

import com.project.UberReviewService.DTO.CreateRequestDto;
import com.project.UberReviewService.Services.ReviewService;
import com.project.UberReviewService.adapter.RequestReviewAdapter;
import com.project.UberReviewService.adapter.ResponseReviewAdapter;
import com.project.UberReviewService.modules.Review;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class ReviewController {


    @Autowired
    ReviewService reviewService;

    @Autowired
    RequestReviewAdapter requestReviewAdapter;
    @Autowired
    ResponseReviewAdapter responseReviewAdapter;

//    ReviewController(ReviewService reviewService){
//        this.reviewService=reviewService;
//    }

    @GetMapping("review/{id}")
    ResponseEntity<?> findReview(@PathVariable Long id){
        try{
            Review requestedReview = reviewService.findReviewById(id).get();
            return ResponseEntity.status(HttpStatus.OK).body(requestedReview);
        }catch(Exception e){
            log.error("Some error in getting the requested review");
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
        }
    }

    @GetMapping("reviews")
    ResponseEntity<?> findAllReviews(){
        try{
            List<Review> allRequestedReviews = reviewService.findAllReviews();
            return ResponseEntity.status(HttpStatus.OK).body(allRequestedReviews);
        }catch(Exception e){
            log.error("Some error in getting all requested reviews");
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
        }
    }

    @DeleteMapping("review/{id}")
    ResponseEntity<?> deleteReview(@PathVariable Long id){
        try{
            Boolean isDeleted = reviewService.deleteReviewById(id);
            return ResponseEntity.status(HttpStatus.OK).body(isDeleted);
        }catch(Exception e){
            log.error("Some error in deleting the requested review");
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
        }
    }

    @PostMapping("review")
    ResponseEntity<?> addReview(@RequestBody CreateRequestDto requestedReview){
            try{
                Review reqReview = requestReviewAdapter.convertDto(requestedReview);
                Review publishedReview = reviewService.publishReview(reqReview);

                return ResponseEntity.status(HttpStatus.OK).body(responseReviewAdapter.convertDto(publishedReview));
            }catch(Exception e){
                log.error("Some error in getting the requested review");
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
            }
    }

}
