package com.ti.avaliai.university.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UniversityDTO {

    private Long id;
    private String hash_id;
    private String name;
    private String cnpj;
    private ArrayList<Course> courses;

}