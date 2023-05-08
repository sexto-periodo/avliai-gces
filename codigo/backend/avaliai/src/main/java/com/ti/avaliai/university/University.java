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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table( name = "t_university")
public class University {
    @Id
    @SequenceGenerator(name = "university_sequence", sequenceName = "university_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "university_sequence")
    @Column
    private long id;

    @Column(name = "hash_id")
    private String hashId = generateHash();

    @Column(name = "name")
    private String name;

    @Column(name = "cnpj")
    private String cnpj;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<Course> courses;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

}

