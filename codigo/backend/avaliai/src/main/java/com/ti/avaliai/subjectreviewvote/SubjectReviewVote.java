package com.ti.avaliai.subjectreviewvote;

import com.ti.avaliai.subjectreview.SubjectReview;
import com.ti.avaliai.user.User;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_subject_review_vote")
public class SubjectReviewVote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "is_upvoted")
    private boolean isUpvoted;


    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;


    @ManyToOne
    @JoinColumn(name = "fk_subject_review")
    private SubjectReview subjectReview;


}