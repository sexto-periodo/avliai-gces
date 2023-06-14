package com.ti.avaliai.course;

import com.ti.avaliai.university.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long>{

    Optional<Course> findById(Long id);

    List<Course> findAllByIdIn(List<Long> coursesIds);

    List<Course> findAllByUniversity(University university);

    Optional<Course> findByHashId(String hashId);
}
