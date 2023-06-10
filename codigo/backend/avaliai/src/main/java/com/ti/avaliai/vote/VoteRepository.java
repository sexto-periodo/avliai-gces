package com.ti.avaliai.vote;

import com.ti.avaliai.review.Review;
import com.ti.avaliai.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findByReviewAndUser(Review review, User user);

    boolean existsByReviewAndUser(Review review, User user);

    Vote save(Vote vote);

    Vote findByReview(Review review);
}
