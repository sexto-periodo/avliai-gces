package com.ti.avaliai.subjectreview;


import com.ti.avaliai.subject.Subject;
import com.ti.avaliai.subject.SubjectService;
import com.ti.avaliai.subject.dto.SubjectDTO;
import com.ti.avaliai.subjectreview.dto.SubjectReviewDTO;
import com.ti.avaliai.subjectreviewvote.SubjectReviewVote;
import com.ti.avaliai.user.SubjectReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectReviewService {

    @Autowired
    private SubjectReviewRepository subjectReviewRepository;

    @Autowired
    private SubjectService subjectService;

    public List<SubjectReviewDTO> findAllBySubjectHashId(String subjectHashId) {
        SubjectDTO subjectDTO = subjectService.findByHashId(subjectHashId);
        Subject subject = subjectService.findById(subjectDTO.getId());
        return subjectReviewRepository.findAllBySubject(subject).stream()
                .map(subjectReview ->
                        subjectReviewToSubjectReviewDTO(subjectReview)
                )
                .collect(Collectors.toList());
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

    private int countReviewVotes(SubjectReview review){
        return (int)review.getVotes().stream()
                .filter(SubjectReviewVote::isUpvoted)
                .count();
    }

    public int getSubjectAverageScore(Subject subject){

        return subject.getReviews().stream()
                .map(SubjectReview::getScore)
                .map(EReviewScore::getValue)
                .reduce(0, Integer::sum);

    }
}
