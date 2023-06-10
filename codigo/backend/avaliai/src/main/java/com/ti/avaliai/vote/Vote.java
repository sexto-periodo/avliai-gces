package com.ti.avaliai.vote;

import com.ti.avaliai.review.Review;
import com.ti.avaliai.user.User;
import jakarta.persistence.*;
import lombok.*;

import static com.ti.avaliai.utils.HashUtils.generateHash;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "is_voted", nullable = false)
    private boolean isVoted;

    @Column(name = "vote_up_down", nullable = false)
    private boolean voteUpdDown;

    @Column(name = "hash_id", nullable = false, updatable = false)
    private String hashId = generateHash();

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;


    @ManyToOne
    @JoinColumn(name = "fk_review")
    private Review review;


}