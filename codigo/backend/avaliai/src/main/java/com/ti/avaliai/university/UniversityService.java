package com.ti.avaliai.university;

import com.ti.avaliai.course.CourseService;
import com.ti.avaliai.university.dto.UniversityCreateRequestDTO;
import com.ti.avaliai.university.dto.UniversityDTO;
import com.ti.avaliai.university.dto.UniversityUpdateRequestDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private CourseService courseService;

    public List<UniversityDTO> getUniversities() {
        List<University> universities = universityRepository.findAll();
        List<UniversityDTO> universityDTOs = new ArrayList<>();

        universities.forEach(university -> {
            universityDTOs.add(universityToUniversityDTO(university));
        });
        return universityDTOs;
    }

    public void create(UniversityCreateRequestDTO universityCreateRequest) {

        University university = University.builder()
                .cnpj(universityCreateRequest.getCnpj())
                .courses(courseService.findAllByIdIn(universityCreateRequest.getCourses()))
                .name(universityCreateRequest.getName())
                .build();
        universityRepository.save(university);
    }

    public University findOneById(long id) {
        return universityRepository.findUniversityById(id);
    }

    public void delete(long id) {
        if (!universityRepository.existsById(id))
            throw new EntityNotFoundException("Não conseguimos encontrar a universidade");
        universityRepository.deleteById(id);
    }

    @Transactional
    public UniversityDTO update(UniversityUpdateRequestDTO universityUpdateRequest) {
        University university = universityRepository
                .findById(universityUpdateRequest.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Não conseguimos encontrar a universidade"));

        university.setCnpj(universityUpdateRequest.getCnpj());
        university.setCourses(courseService.findAllByIdIn(universityUpdateRequest.getCoursesIds()));
        university.setName(universityUpdateRequest.getName());

        universityRepository.save(university);
        return universityToUniversityDTO(university);
    }

    private UniversityDTO universityToUniversityDTO(University university) {
        return UniversityDTO.builder()
                .hashId(university.getHashId())
                .id(university.getId())
                .cnpj(university.getCnpj())
                .name(university.getName())
                .build();
    }

    public UniversityDTO findOneByHashId(String hashId) {
        return universityToUniversityDTO(universityRepository.findByHashId(hashId)
                .orElseThrow(() -> new EntityNotFoundException()));
    }

    public University findByHashId(String hashId) {
        return universityRepository.findByHashId(hashId)
                .orElseThrow(() -> new EntityNotFoundException("Universidade não encontrada"));
    }
}
