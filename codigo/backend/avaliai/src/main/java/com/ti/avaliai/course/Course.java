package com.ti.avaliai.course;

import com.ti.avaliai.subject.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.ti.avaliai.utils.HashUtils.generateHash;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
    @Column
    private long id;

    @Column
    private String hash_id = generateHash();
    @Column
    private String name;
    @Column
    private int overtime;
    @Column
    @OneToMany
    private List<Subject> subjects;
    @Column
    private boolean statusCurriculum;

}
