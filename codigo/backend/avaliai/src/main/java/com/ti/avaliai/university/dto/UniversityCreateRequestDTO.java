package com.ti.avaliai.university.dto;

import com.ti.avaliai.course.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UniversityCreateRequestDTO {

    private String name;
    private String cnpj;
    private List<Long> courses;

}