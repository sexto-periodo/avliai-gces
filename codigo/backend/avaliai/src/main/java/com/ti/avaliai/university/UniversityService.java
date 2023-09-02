package com.ti.avaliai.university;

import com.ti.avaliai.course.CourseService;
import com.ti.avaliai.global.domain.exceptions.EntityNotFoundException;
import com.ti.avaliai.university.dto.UniversityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private CourseService courseService;

    public List<UniversityDTO> findAll() {
        List<University> universities = universityRepository.findAll();
        List<UniversityDTO> universityDTOs = new ArrayList<>();

        universities.forEach(university -> {
            universityDTOs.add(universityToUniversityDTO(university));
        });
        return universityDTOs;
    }

    private UniversityDTO universityToUniversityDTO(University university) {
        return UniversityDTO.builder()
                .hashId(university.getHashId())
                .id(university.getId())
                .cnpj(university.getCnpj())
                .name(university.getName())
                .build();
    }

    public UniversityDTO findByHashIdDTO(String hashId) {
        return universityToUniversityDTO(universityRepository.findByHashId(hashId)
                .orElseThrow(() -> new EntityNotFoundException("Universidade do hashId"+hashId+" não encontrada", HttpStatus.NOT_FOUND)));
    }

    public University findByHashId(String hashId) {
        return universityRepository.findByHashId(hashId)
                .orElseThrow(() -> new EntityNotFoundException("Universidade do hashId"+hashId+" não encontrada", HttpStatus.NOT_FOUND));
    }

    public void save(University university) {
        universityRepository.save(university);
    }
}
