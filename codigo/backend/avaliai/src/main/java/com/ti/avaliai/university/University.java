package com.ti.avaliai.university;

import com.ti.avaliai.course.Course;
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
public class University {
    @Id
    @SequenceGenerator(name = "university_sequence", sequenceName = "university_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "university_sequence")
    @Column
    private long id;

    @Column
    private String hash_id = generateHash();
    @Column
    private String name;
    @Column
    private String cnpj;
    @Column
    @OneToMany
    private List<Course> courses;

}
