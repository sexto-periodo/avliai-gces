package com.ti.avaliai.university;

import com.ti.avaliai.university.dto.UniversityCreateRequestDTO;
import com.ti.avaliai.university.dto.UniversityDTO;
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
                .courses(universityCreateRequest.getCourses())
                .name(universityCreateRequest.getName())
                .build();
        universityRepository.save(university);
    }

    public UniversityDTO findOneById(long id) {
        return universityToUniversityDTO(universityRepository.findUniversityById(id));
    }

    public void delete(long id) {
        if (!universityRepository.existsById(id))
            throw new EntityNotFoundException("Não conseguimos encontrar a universidade");
        universityRepository.deleteById(id);
    }

    @Transactional
    public UniversityDTO update(UniversityDTO universityUpdateRequest) {
        University university = universityRepository
                .findById(universityUpdateRequest.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Não conseguimos encontrar a universidade"));

        university.setCnpj(universityUpdateRequest.getCnpj());
        university.setCourses(universityUpdateRequest.getCourses());
        university.setName(universityUpdateRequest.getName());

        universityRepository.save(university);
        return universityToUniversityDTO(university);
    }

    private UniversityDTO universityToUniversityDTO(University university) {
        return UniversityDTO.builder()
                .hashId(university.getHashId())
                .id(university.getId())
                .cnpj(university.getCnpj())
                .courses(university.getCourses())
                .name(university.getName())
                .build();
    }

}
