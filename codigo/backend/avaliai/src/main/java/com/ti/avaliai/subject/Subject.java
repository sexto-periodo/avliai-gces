package com.ti.avaliai.subject;

import com.ti.avaliai.course.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ti.avaliai.utils.HashUtils.generateHash;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_subeject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private long id;

    @Column(name="hash_id")
    private String hashId = generateHash();

    @Column(name = "name")
    private String name;

    @Column(name = "pic_url")
    private String picUrl;

    @Column(name ="campus")
    private String campus;

    @Column(name = "grade")
    private double grade;

    @ManyToOne
    @JoinColumn(name = "fk_course", nullable = false)
    private Course course;

}
