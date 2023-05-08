package com.ti.avaliai.course.dto;

import com.ti.avaliai.subject.Subject;
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
public class CourseDTO {

    private Long id;
    private  String hashId;
    private String name;
    private int overtime;
    private List<Subject> subjects;
    private boolean statusCurriculum;

}