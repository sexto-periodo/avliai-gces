package com.ti.avaliai.vote;



import com.ti.avaliai.global.domain.exceptions.EntityNotFoundException;
import com.ti.avaliai.review.Review;
import com.ti.avaliai.review.ReviewService;
import com.ti.avaliai.user.User;
import com.ti.avaliai.user.UserService;
import com.ti.avaliai.vote.dto.VoteRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.ti.avaliai.utils.HashUtils.generateHash;

@Service
@Component
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    public Vote findByReviewAndUser(Review review, User user){
        return voteRepository.findByReviewAndUser(review, user)
                .orElseThrow(() -> new EntityNotFoundException("Upvote ou downvote do usuário não encontrado.", HttpStatus.NOT_FOUND));
    }

    public void vote(VoteRequestDTO request) {
        User user = userService.getUser();
        Review review = reviewService.findByHashId(request.getReviewHashId());

        Optional<Vote> reviewVote = voteRepository.findByReviewAndUser(review, user);
        if(reviewVote.isPresent()){
            reviewVote.get().setUpvoted(request.isUpvoted());
            voteRepository.save(reviewVote.get());
            return;
        }

        Vote vote = Vote.builder()
                .isUpvoted(request.isUpvoted())
                .review(review)
                .user(user)
                .build();
        voteRepository.save(vote);
    }
}
