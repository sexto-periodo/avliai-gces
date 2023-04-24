package com.ti.avaliai.university.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UniversityCreateRequestDTO {
    private String name;
    private String cnpj;
    private ArrayList<Course> courses;

}