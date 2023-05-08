package com.ti.avaliai.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ti.avaliai.course.dto.CourseCreateRequestDTO;
import com.ti.avaliai.course.dto.CourseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

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
                .build();
        courseRepository.save(course);
    }

    public CourseDTO findOneById(long id) {
            return courseToCourseDTO(
                    courseRepository.findById(id).orElseThrow(
                            () -> new EntityNotFoundException("Curso de id "+id+" não encontrado")
                    )
            );

    }

    public void delete(long id) {
        if (!courseRepository.existsById(id))
            throw new EntityNotFoundException("Não conseguimos encontrar o curso");
        courseRepository.deleteById(id);
    }

    @Transactional
    public CourseDTO update(CourseDTO courseUpdateRequest) {
        Course course = courseRepository
                .findById(courseUpdateRequest.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Não conseguimos encontrar o curso"));

        course.setSubjects(courseUpdateRequest.getSubjects());
        course.setOvertime(courseUpdateRequest.getOvertime());
        course.setName(courseUpdateRequest.getName());

        courseRepository.save(course);
        return courseToCourseDTO(course);
    }

    private CourseDTO courseToCourseDTO(Course course) {
        return CourseDTO.builder()
                .hashId(course.getHash_id())
                .id(course.getId())
                .subjects(course.getSubjects())
                .overtime(course.getOvertime())
                .name(course.getName())
                .build();
    }

}
