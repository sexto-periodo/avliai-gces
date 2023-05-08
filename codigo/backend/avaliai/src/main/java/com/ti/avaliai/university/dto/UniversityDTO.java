package com.ti.avaliai.university.dto;

import com.ti.avaliai.course.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UniversityDTO {

    private Long id;
    private String hash_id;
    private String name;
    private String cnpj;
    private List<Course> courses;

}