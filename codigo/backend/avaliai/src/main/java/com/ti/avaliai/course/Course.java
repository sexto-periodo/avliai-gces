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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_course")
public class Course {
    @Id
    @SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
    @Column(name = "id")
    private long id;

    @Column(name = "hash_id", updatable = false)
    private String hashId = generateHash();
    @Column(name="name")
    private String name;
    @Column(name="overtime")
    private int overtime;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<Subject> subjects;
    @Column(name = "status_curriculum")
    private boolean statusCurriculum;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

}
