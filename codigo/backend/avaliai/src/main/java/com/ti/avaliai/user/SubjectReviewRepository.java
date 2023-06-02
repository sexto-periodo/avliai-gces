package com.ti.avaliai.user;

import com.ti.avaliai.subject.Subject;
import com.ti.avaliai.subjectreview.SubjectReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectReviewRepository extends JpaRepository<SubjectReview, Integer> {

    List<SubjectReview> findAllBySubject(Subject subject);
}
