package com.ti.avaliai.course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long>{

    Optional<Course> findById(Long id);

    List<Course> findAllByIdIn(List<Long> coursesIds);
}
