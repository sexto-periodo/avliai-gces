package com.ti.avaliai.subjectreview;


import com.ti.avaliai.course.CourseService;
import com.ti.avaliai.global.domain.exceptions.AlreadyReviewedByUserException;
import com.ti.avaliai.global.domain.exceptions.UnauthorizedReviewerException;
import com.ti.avaliai.rabbit.producer.RabbitMQProducer;
import com.ti.avaliai.subject.Subject;
import com.ti.avaliai.subject.SubjectService;
import com.ti.avaliai.subject.dto.SubjectDTO;
import com.ti.avaliai.subjectreview.dto.CreateSubjectReviewRequestDTO;
import com.ti.avaliai.subjectreview.dto.SubjectReviewByUserDTO;
import com.ti.avaliai.subjectreview.dto.SubjectReviewDTO;
import com.ti.avaliai.subjectreviewvote.SubjectReviewVote;
import com.ti.avaliai.university.UniversityService;
import com.ti.avaliai.user.SubjectReviewRepository;
import com.ti.avaliai.user.User;
import com.ti.avaliai.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@Component
public class SubjectReviewService {

    @Autowired
    private SubjectReviewRepository subjectReviewRepository;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @Autowired
    private UserService userService;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private CourseService courseService;

    public List<SubjectReviewDTO> findAllBySubjectHashId(String subjectHashId) {
        SubjectDTO subjectDTO = subjectService.findByHashIdDTO(subjectHashId);
        Subject subject = subjectService.findById(subjectDTO.getId());

        User user = userService.getUser();
        List<SubjectReview> reviews = subjectReviewRepository.findAllBySubject(subject);



        List<SubjectReviewDTO> reviewsDTO = reviews.stream()
                .map(subjectReview ->
                        subjectReviewToSubjectReviewDTO(subjectReview)
                )
                .collect(Collectors.toList());

        Optional<SubjectReview> reviewByUser = subjectReviewRepository.findBySubjectAndUser(subject,user);
        if( reviewByUser.isPresent() ){
            SubjectReviewByUserDTO reviewByUserDTO = subjectReviewToSubjectReviewByUserDTO(reviewByUser.get());
            reviewsDTO.add(0, reviewByUserDTO);
        }
        return reviewsDTO;

    }

    public void send(CreateSubjectReviewRequestDTO request) {
        Subject subject = subjectService.findByHashId(request.getSubjectHashId());
        User user = userService.getUser();
        Optional<SubjectReview> subjectReview =
                subjectReviewRepository.findBySubjectAndUser(subject, user);

        if( subjectReview.isPresent() ){
            throw new AlreadyReviewedByUserException("Disciplina já avaliada pelo usuário", HttpStatus.CONFLICT);
        }else{
            rabbitMQProducer.sendReviewMessage(request);
        }
    }

    private SubjectReviewDTO subjectReviewToSubjectReviewDTO(
            SubjectReview review
    ) {
        return SubjectReviewDTO.builder()
                .reviewText(review.getReviewText())
                .hashId(review.getHashId())
                .id(review.getId())
                .voteCount(this.countReviewVotes(review))
                .build();

    }

    private SubjectReviewByUserDTO subjectReviewToSubjectReviewByUserDTO(
            SubjectReview review
    ) {
        return (SubjectReviewByUserDTO) SubjectReviewByUserDTO.builder()
                .firstname(review.getUser().getFirstname())
                .lastname(review.getUser().getLastname())
                .reviewText(review.getReviewText())
                .hashId(review.getHashId())
                .id(review.getId())
                .voteCount(this.countReviewVotes(review))
                .build();

    }


    private int countReviewVotes(SubjectReview review) {
        return (int) review.getVotes().stream()
                .filter(SubjectReviewVote::isUpvoted)
                .count();
    }

    public double getSubjectAverageScore(Subject subject) {

        List<SubjectReview> reviews = subject.getReviews();
        if( reviews.size() == 0){
            return 0;
        }
        int sum = reviews.stream()
                .map(SubjectReview::getScore)
                .map(EReviewScore::getValue)
                .reduce(0, Integer::sum);


        double average = sum / reviews.size();

        return average;

    }

    public void create(CreateSubjectReviewRequestDTO reviewMessage) {

        User user = userService.findByHashId(reviewMessage.getUserHashId());
        if (user.isBanned()) {
            throw new UnauthorizedReviewerException("Usuário não autorizado a fazer Avaliações", HttpStatus.UNAUTHORIZED);
        }

        Subject subject = subjectService.findByHashId(reviewMessage.getSubjectHashId());

        SubjectReview subjectReview = SubjectReview.builder()
                .subject(subject)
                .user(user)
                .reviewText(reviewMessage.getReviewText())
                .score(reviewMessage.getScore())
                .build();

        subjectReviewRepository.save(subjectReview);
        log.info("Avaliação processada com sucesso");


    }

    public List<SubjectReview> findAll() {
        return subjectReviewRepository.findAll();
    }
}
