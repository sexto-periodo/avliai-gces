package com.ti.avaliai.review;

import com.ti.avaliai.subject.Subject;
import com.ti.avaliai.vote.Vote;
import com.ti.avaliai.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static com.ti.avaliai.utils.HashUtils.generateHash;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "hash_id")
    private String hashId = generateHash();

    @Column(name = "score")
    private EReviewScore score;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_subject")
    private Subject subject;

    @Column(name = "review_text", columnDefinition = "TEXT")
    private String reviewText;

    @OneToMany(mappedBy = "review", fetch = FetchType.EAGER)
    private List<Vote> votes;

}
