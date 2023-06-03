package com.ti.avaliai.subject;

import com.ti.avaliai.course.Course;
import com.ti.avaliai.course.CourseService;
import com.ti.avaliai.subject.dto.SubjectCreateRequestDTO;
import com.ti.avaliai.subject.dto.SubjectDTO;
import com.ti.avaliai.subjectreview.SubjectReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SubjectReviewService subjectReviewService;

    public List<SubjectDTO> getSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectDTO> subjectDTOs = new ArrayList<>();

        subjects.forEach(subject -> {
            subjectDTOs.add(subjectToSubjectDTO(subject));
        });
        return subjectDTOs;
    }

    public void create(SubjectCreateRequestDTO subjectCreateRequest) {

        Subject subject = Subject.builder()
                .campus(subjectCreateRequest.getCampus())
                .imageUrl(subjectCreateRequest.getImageUrl())
                .name(subjectCreateRequest.getName())
                .build();
        subjectRepository.save(subject);
    }

    public SubjectDTO findOneById(long id) {
        return subjectToSubjectDTO(subjectRepository.findSubjectById(id));
    }

    public void delete(long id) {
        if (!subjectRepository.existsById(id))
            throw new EntityNotFoundException("Não conseguimos encontrar a disciplina");
        subjectRepository.deleteById(id);
    }

    @Transactional
    public SubjectDTO update(SubjectDTO subjectUpdateRequest) {
        Subject subject = subjectRepository
                .findById(subjectUpdateRequest.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Não conseguimos encontrar a disciplina"));

        subject.setCampus(subjectUpdateRequest.getCampus());
        subject.setImageUrl(subjectUpdateRequest.getImageUrl());
        subject.setName(subjectUpdateRequest.getName());

        subjectRepository.save(subject);
        return subjectToSubjectDTO(subject);
    }

    public List<Subject> findAllByIdIn(List<Long> subjectsIds) {
        return subjectRepository.findAllByIdIn(subjectsIds);
    }

    public List<SubjectDTO> findAllByCourseHashId(String coursHashId) {
        Course course = courseService.findByHashId(coursHashId);
        List<Subject> subjects = subjectRepository.findAllByCourse(course);

        return subjects.stream()
                .map(subject ->
                        subjectToSubjectDTO(subject)
                )
                .collect(Collectors.toList());

    }

    public Subject findByHashId(String hashId) {
        return subjectRepository.findByHashId(hashId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Não conseguimos encontrar a disciplina")
                );
    }

    private SubjectDTO subjectToSubjectDTO(Subject subject) {
        return SubjectDTO.builder()
                .hashId(subject.getHashId())
                .id(subject.getId())
                .imageUrl(subject.getImageUrl())
                .name(subject.getName())
                .campus(subject.getCampus())
                .courseHashId(subject.getCourse().getHashId())
                .shortDescription(subject.getShortDescription())
                .longDescription(subject.getLongDescription())
                .score(subjectReviewService.getSubjectAverageScore(subject))
                .build();
    }
}

