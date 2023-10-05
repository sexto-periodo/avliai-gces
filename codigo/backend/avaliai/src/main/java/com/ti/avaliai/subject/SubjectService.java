package com.ti.avaliai.subject;

import com.ti.avaliai.course.Course;
import com.ti.avaliai.course.CourseService;
import com.ti.avaliai.global.domain.exceptions.EntityNotFoundException;
import com.ti.avaliai.review.ReviewService;
import com.ti.avaliai.subject.dto.SubjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SubjectService {

    @Autowired
    private ISubjectRepository subjectRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ReviewService reviewService;

    public List<Subject> findAllByIdIn(List<Long> subjectsIds) {
        return subjectRepository.findAllByIdIn(subjectsIds);
    }

    public List<SubjectDTO> findAllByCourseHashId(String coursHashId) {
        Course course = courseService.findByHashId(coursHashId);
        List<Subject> subjects = subjectRepository.findAllByCourse(course);

        return subjects.stream()
                .map(this::subjectToSubjectDTO)
                .collect(Collectors.toList());

    }

    public Subject findByHashId(String hashId) {
        Subject subject = subjectRepository.findByHashId(hashId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Não conseguimos encontrar a disciplina", HttpStatus.NOT_FOUND)
                );
        return subject;
    }

    public SubjectDTO findByHashIdDTO(String hashId) {
        Subject subject = findByHashId(hashId);
        return subjectToSubjectDTO(subject);
    }


    private SubjectDTO subjectToSubjectDTO(Subject subject) {
        return SubjectDTO.builder()
                .hashId(subject.getHashId())
                .id(subject.getId())
                .imageUrl(subject.getImageUrl())
                .name(subject.getName())
                .campus(subject.getCampus())
                .course(subject.getCourse().getName())
                .courseHashId(subject.getCourse().getHashId())
                .university(subject.getCourse().getUniversity().getName())
                .universityHashId(subject.getCourse().getUniversity().getHashId())
                .shortDescription(subject.getShortDescription())
                .longDescription(subject.getLongDescription())
                .score(reviewService.getSubjectAverageScore(subject))
                .build();
    }

    public List<SubjectDTO> findAllDTO() {
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectDTO> subjectDTOs =
                subjects.stream()
                        .map(s -> subjectToSubjectDTO(s)).collect(Collectors.toList());
        return subjectDTOs;
    }

    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }
}
