package com.ti.avaliai.vote;



import com.ti.avaliai.global.domain.exceptions.EntityNotFoundException;
import com.ti.avaliai.review.Review;
import com.ti.avaliai.review.ReviewService;
import com.ti.avaliai.user.User;
import com.ti.avaliai.user.UserService;
import com.ti.avaliai.vote.dto.VoteDTO;
import com.ti.avaliai.vote.dto.VoteRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public VoteDTO vote(VoteRequestDTO request) {
        User user = userService.getUser();
        Review review = reviewService.findByHashId(request.getReviewHashId());
        Vote vote;

        Optional<Vote> existVote = voteRepository.findByReviewAndUser(review, user);
        if(existVote.isPresent()){
            existVote.get().setVoted(request.isVoted());
            existVote.get().setVoteUpdDown(request.isVoteUpDown());
            vote = voteRepository.save(existVote.get());
        }else{
            Vote newVote = Vote.builder()
                    .isVoted(request.isVoted())
                    .voteUpdDown(request.isVoteUpDown())
                    .review(review)
                    .user(user)
                    .hashId(generateHash())
                    .build();
            vote = voteRepository.save(newVote);
        }
        return voteToVoteDTO(vote);
    }

    private VoteDTO voteToVoteDTO(Vote vote) {
        Review review = vote.getReview();
        return VoteDTO.builder()
                .reviewHashId(review.getHashId())
                .voteCount(this.countReviewVotes(review))
                .voteUpDown(vote.isVoteUpdDown())
                .isVoted(vote.isVoted())
                .build();
    }

    public int countReviewVotes(Review review) {
        List<Vote> votes = review.getVotes();
        votes = votes.stream().filter(v -> v.isVoted()).collect(Collectors.toList());
        long upvotes = votes.stream().filter(Vote::isVoteUpdDown).count();
        long downvotes = votes.stream().filter(vote -> !vote.isVoteUpdDown()).count();

        int voteBalance = (int) (upvotes - downvotes);
        return voteBalance;
    }

    public boolean existisByReviewAndUser(Review review, User user) {
        return voteRepository.existsByReviewAndUser(review, user);
    }
}
