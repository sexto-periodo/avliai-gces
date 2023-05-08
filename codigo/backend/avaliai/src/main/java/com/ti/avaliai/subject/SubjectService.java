package com.ti.avaliai.subject;

import com.ti.avaliai.subject.dto.SubjectCreateRequestDTO;
import com.ti.avaliai.subject.dto.SubjectDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

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
                .grade(subjectCreateRequest.getGrade())
                .picUrl(subjectCreateRequest.getPicUrl())
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
        subject.setPicUrl(subjectUpdateRequest.getPicUrl());
        subject.setGrade(subjectUpdateRequest.getGrade());
        subject.setName(subjectUpdateRequest.getName());

        subjectRepository.save(subject);
        return subjectToSubjectDTO(subject);
    }

    private SubjectDTO subjectToSubjectDTO(Subject subject) {
        return SubjectDTO.builder()
                .hashId(subject.getHash_id())
                .id(subject.getId())
                .grade(subject.getGrade())
                .picUrl(subject.getPicUrl())
                .name(subject.getName())
                .campus(subject.getCampus())
                .build();
    }

}
