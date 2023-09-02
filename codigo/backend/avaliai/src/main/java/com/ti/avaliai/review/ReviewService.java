package com.ti.avaliai.review;


import com.ti.avaliai.course.CourseService;
import com.ti.avaliai.global.domain.exceptions.AlreadyReviewedByUserException;
import com.ti.avaliai.global.domain.exceptions.EntityNotFoundException;
import com.ti.avaliai.global.domain.exceptions.UnauthorizedReviewerException;
import com.ti.avaliai.subject.Subject;
import com.ti.avaliai.subject.SubjectService;
import com.ti.avaliai.subject.dto.SubjectDTO;
import com.ti.avaliai.review.dto.CreateReviewRequestDTO;
import com.ti.avaliai.review.dto.ReviewByUserDTO;
import com.ti.avaliai.review.dto.ReviewDTO;
import com.ti.avaliai.vote.Vote;
import com.ti.avaliai.university.UniversityService;
import com.ti.avaliai.user.User;
import com.ti.avaliai.user.UserService;
import com.ti.avaliai.vote.VoteService;
import com.ti.avaliai.vote.dto.VoteDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ti.avaliai.utils.HashUtils.generateHash;

@Service
@Log4j2
@Component
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private VoteService voteService;

    public List<ReviewDTO> findAllBySubjectHashId(String subjectHashId) {
        SubjectDTO subjectDTO = subjectService.findByHashIdDTO(subjectHashId);
        Subject subject = subjectService.findByHashId(subjectDTO.getHashId());

        User user = userService.getUser();
        List<Review> reviews = reviewRepository.findAllBySubject(subject);

        List<ReviewDTO> reviewsDTO = reviews.stream()
                .map(subjectReview ->
                        reviewToReviewDTO(subjectReview)
                )
                .collect(Collectors.toList());

        Optional<Review> reviewByUser = reviewRepository.findBySubjectAndUser(subject, user);
        if (reviewByUser.isPresent()) {
            ReviewByUserDTO reviewByUserDTO = reviewToReviewByUserDTO(reviewByUser.get());
            reviewsDTO.removeIf(r -> r.getHashId().equals(reviewByUserDTO.getHashId()));
            Collections.reverse(reviewsDTO);
            reviewsDTO.add(reviewByUserDTO);
            Collections.reverse(reviewsDTO);
        }
        return reviewsDTO;

    }

    public boolean haveUserAlreadyReviewedSubject(String subjectHashId) {
        Subject subject = subjectService.findByHashId(subjectHashId);
        User user = userService.getUser();
        Optional<Review> subjectReview =
                reviewRepository.findBySubjectAndUser(subject, user);
        if (subjectReview.isPresent()) {
            return true;
        }
        return false;
    }

    public void send(CreateReviewRequestDTO request) {
        if (haveUserAlreadyReviewedSubject(request.getSubjectHashId())) {
            throw new AlreadyReviewedByUserException("Disciplina já avaliada pelo usuário", HttpStatus.CONFLICT);
        } else {
            this.create(request);
        }
    }

    private ReviewDTO reviewToReviewDTO(
            Review review
    ) {

        ReviewDTO reviewDTO = ReviewDTO.builder()
                .reviewText(review.getReviewText())
                .hashId(review.getHashId())
                .id(review.getId())
                .vote(buildVoteDTOByReview(review))
                .build();

        return reviewDTO;
    }

    private ReviewByUserDTO reviewToReviewByUserDTO(
            Review review
    ) {
        return (ReviewByUserDTO) ReviewByUserDTO.builder()
                .firstname(review.getUser().getFirstname())
                .lastname(review.getUser().getLastname())
                .reviewText(review.getReviewText())
                .hashId(review.getHashId())
                .id(review.getId())
                .vote(buildVoteDTOByReview(review))
                .build();

    }

    private VoteDTO buildVoteDTOByReview(Review review){

        User user = userService.getUser();

        VoteDTO voteDTO = VoteDTO.builder()
                .voteCount(voteService.countReviewVotes(review))
                .reviewHashId(review.getHashId())
                .build();

        if(voteService.existisByReviewAndUser(review,user)){
            Vote vote = voteService.findByReviewAndUser(review,user);
            voteDTO.setVoted(vote.isVoted());
            voteDTO.setVoteUpDown(vote.isVoteUpDown());
        }else{
            voteDTO.setVoted(false);
            voteDTO.setVoteUpDown(false);
        }

        return voteDTO;
    }

    public double getSubjectAverageScore(Subject subject) {

        List<Review> reviews = subject.getReviews();
        if (reviews.size() == 0) {
            return 0;
        }
        int sum = reviews.stream()
                .map(Review::getScore)
                .map(EReviewScore::getValue)
                .reduce(0, Integer::sum);

        double average = sum / reviews.size();

        return average;

    }

    public void create(CreateReviewRequestDTO reviewMessage) {

        User user = userService.findByHashId(reviewMessage.getUserHashId());
        if (user.isBanned()) {
            throw new UnauthorizedReviewerException("Usuário não autorizado a fazer Avaliações", HttpStatus.UNAUTHORIZED);
        }

        Subject subject = subjectService.findByHashId(reviewMessage.getSubjectHashId());

        Review review = Review.builder()
                .subject(subject)
                .user(user)
                .reviewText(reviewMessage.getReviewText())
                .score(reviewMessage.getScore())
                .hashId(generateHash())
                .build();

        reviewRepository.save(review);
        log.info("Avaliação processada com sucesso");


    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public List<ReviewDTO> findAllByLoggedUser() {
        User user = userService.getUser();
        return reviewRepository.findAllByUser(user).stream()
                .map(subjectReview ->
                        reviewToReviewByUserDTO(subjectReview)
                )
                .collect(Collectors.toList());
    }

    public Review findByHashId(String hashId) {
        return reviewRepository.findByHashId(hashId)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação "+hashId+" não encontrada.", HttpStatus.NOT_FOUND));
    }
}
