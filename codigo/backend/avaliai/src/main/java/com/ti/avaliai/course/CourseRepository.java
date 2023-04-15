package com.ti.avaliai.course;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long>{

    @Query("SELECT crs FROM Course sub WHERE crs.name = ?1")
    Optional<Course> findCourseByName(String name);

    Course findCourseById(Long id);

}
