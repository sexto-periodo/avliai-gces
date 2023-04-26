package com.ti.avaliai.subject.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectDTO {

    private Long id;
    private  String hash_id;
    private String name;
    private String picUrl;
    private String campus;
    private double grade;

}