package com.ti.avaliai.subject;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ti.avaliai.utils.HashUtils.generateHash;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject {
    @Id
    @SequenceGenerator(name = "subject_sequence", sequenceName = "subject_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_sequence")
    @Column
    private long id;

    @Column
    private String hash_id = generateHash();
    @Column
    private String name;
    @Column
    private String picUrl;
    @Column
    private String campus;
    @Column
    private double grade;

}
