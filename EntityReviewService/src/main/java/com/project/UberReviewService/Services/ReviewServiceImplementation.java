package com.project.UberReviewService.Services;

import com.project.UberReviewService.Repositories.ReviewRepository;
import com.project.UberReviewService.modules.Review;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImplementation implements ReviewService{

     @Autowired
    ReviewRepository reviewRepository;

    ReviewServiceImplementation(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }
    @Override
    public Optional<Review> findReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public boolean deleteReviewById(Long id) {
        try{
            reviewRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Review publishReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Long id, Review newReviewData) {
        Review review = this.reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(newReviewData.getRating() != null){
            review.setRating(newReviewData.getRating());
        }
        if(newReviewData.getContent() != null){
            review.setContent(newReviewData.getContent());
        }
        return this.reviewRepository.save(review);
    }
}
