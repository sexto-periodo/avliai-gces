package com.ti.avaliai.subjectreview;


import com.ti.avaliai.course.CourseService;
import com.ti.avaliai.global.domain.exceptions.UnauthorizedReviewerException;
import com.ti.avaliai.rabbit.producer.RabbitMQProducer;
import com.ti.avaliai.subject.Subject;
import com.ti.avaliai.subject.SubjectService;
import com.ti.avaliai.subject.dto.SubjectDTO;
import com.ti.avaliai.subjectreview.dto.CreateSubjectReviewRequestDTO;
import com.ti.avaliai.subjectreview.dto.SubjectReviewDTO;
import com.ti.avaliai.subjectreviewvote.SubjectReviewVote;
import com.ti.avaliai.university.UniversityService;
import com.ti.avaliai.user.SubjectReviewRepository;
import com.ti.avaliai.user.User;
import com.ti.avaliai.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return subjectReviewRepository.findAllBySubject(subject).stream()
                .map(subjectReview ->
                        subjectReviewToSubjectReviewDTO(subjectReview)
                )
                .collect(Collectors.toList());
    }

    public void send(CreateSubjectReviewRequestDTO request) {
        rabbitMQProducer.sendReviewMessage(request);
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

    private int countReviewVotes(SubjectReview review) {
        return (int) review.getVotes().stream()
                .filter(SubjectReviewVote::isUpvoted)
                .count();
    }

    public int getSubjectAverageScore(Subject subject) {

        return subject.getReviews().stream()
                .map(SubjectReview::getScore)
                .map(EReviewScore::getValue)
                .reduce(0, Integer::sum);

    }

    public void create(CreateSubjectReviewRequestDTO reviewMessage) {

        User user = userService.findByHashId(reviewMessage.getUserHashId());
        if (user.isBanned()){
            throw new UnauthorizedReviewerException("Usuário não autorizado a fazer Avaliações");
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
