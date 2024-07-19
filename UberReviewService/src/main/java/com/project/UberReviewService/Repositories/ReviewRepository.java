package com.project.UberReviewService.Repositories;

import com.project.UberReviewService.modules.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {


}
