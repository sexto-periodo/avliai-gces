package com.ti.avaliai.subject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectCreateRequestDTO {
    private String name;
    private String picUrl;
    private String campus;
    private double grade;

}