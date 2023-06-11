package com.ti.avaliai.course;

import com.ti.avaliai.course.dto.CourseCreateRequestDTO;
import com.ti.avaliai.course.dto.CourseDTO;
import com.ti.avaliai.course.dto.CourseUpdateRequestDTO;
import com.ti.avaliai.global.domain.exceptions.EntityNotFoundException;
import com.ti.avaliai.subject.SubjectService;
import com.ti.avaliai.university.University;
import com.ti.avaliai.university.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ti.avaliai.utils.HashUtils.generateHash;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UniversityService universityService;

    private CourseDTO courseToCourseDTO(Course course) {
        return CourseDTO.builder()
                .hashId(course.getHashId())
                .id(course.getId())
                .overtime(course.getOvertime())
                .name(course.getName())
                .build();
    }

    public List<Course> findAllByIdIn(List<Long> coursesIds) {
        return courseRepository.findAllByIdIn(coursesIds);
    }

    public List<CourseDTO> findAllByUniversityHashId(String univesityHashId) {
        University university = universityService.findByHashId(univesityHashId);
        List<Course> courses = courseRepository.findAllByUniversity(university);

        return courses.stream()
                .map(course ->
                        courseToCourseDTO(course)
                )
                .collect(Collectors.toList());
    }

    public Course findByHashId(String hashId) {
        return courseRepository.findByHashId(hashId)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado", HttpStatus.NOT_FOUND));
    }
}
