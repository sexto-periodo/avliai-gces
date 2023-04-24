package com.ti.avaliai.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseCreateRequestDTO {
    private String name;
    private int overtime;
    private ArrayList<Subjects> subjects;
    private boolean statusCurriculum;

}