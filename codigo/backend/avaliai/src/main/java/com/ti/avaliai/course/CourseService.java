package com.ti.avaliai.course;

import com.ti.avaliai.course.dto.CourseCreateRequestDTO;
import com.ti.avaliai.course.dto.CourseDTO;
import com.ti.avaliai.course.dto.CourseUpdateRequestDTO;
import com.ti.avaliai.subject.SubjectService;
import com.ti.avaliai.university.University;
import com.ti.avaliai.university.UniversityService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<CourseDTO> getCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDTO> courseDTOs = new ArrayList<>();

        courses.forEach(course -> {
            courseDTOs.add(courseToCourseDTO(course));
        });
        return courseDTOs;
    }

    public void create(CourseCreateRequestDTO courseCreateRequest) {

        Course course = Course.builder()
                .name(courseCreateRequest.getName())
                .university(universityService.findOneById(courseCreateRequest.getUniversityId()))
                .overtime(courseCreateRequest.getOvertime())
                .hashId(generateHash())
                .isDeleted(false)
                .statusCurriculum(true)
                .build();
        courseRepository.save(course);
    }

    public CourseDTO findOneById(long id) {
        return courseToCourseDTO(
                courseRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Curso de id " + id + " não encontrado")
                )
        );

    }

    public void delete(long id) {
        if (!courseRepository.existsById(id))
            throw new EntityNotFoundException("Não conseguimos encontrar o curso");
        courseRepository.deleteById(id);
    }

    @Transactional
    public CourseDTO update(CourseUpdateRequestDTO courseUpdateRequest) {
        Course course = courseRepository
                .findById(courseUpdateRequest.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Não conseguimos encontrar o curso"));

        course.setSubjects(subjectService.findAllByIdIn(courseUpdateRequest.getSubjects()));
        course.setOvertime(courseUpdateRequest.getOvertime());
        course.setName(courseUpdateRequest.getName());

        courseRepository.save(course);
        return courseToCourseDTO(course);
    }

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
}
