package com.ti.avaliai.review;

import com.ti.avaliai.subject.Subject;
import com.ti.avaliai.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findAllBySubject(Subject subject);

    Optional<Review> findBySubjectAndUser(Subject subject, User user);

    List<Review> findAllByUser(User user);

    Optional<Review> findByHashId(String hashId);
}
