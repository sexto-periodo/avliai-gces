package com.ti.avaliai.user;


import com.ti.avaliai.course.Course;
import com.ti.avaliai.subjectreview.SubjectReview;
import com.ti.avaliai.subjectreviewvote.SubjectReviewVote;
import com.ti.avaliai.token.Token;
import com.ti.avaliai.university.University;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static com.ti.avaliai.utils.HashUtils.generateHash;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "hash_id")
    private String hashId = generateHash();

    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "fk_university")
    private University university;

    @OneToOne
    @JoinColumn(name = "fk_course")
    private Course course;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @Column(name = "is_banned")
    private boolean isBanned = false;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<SubjectReview> subjectReviews;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private  List<SubjectReviewVote> votes;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
