package com.ti.avaliai.academicmail;

import jakarta.persistence.*;
import lombok.*;

import static com.ti.avaliai.utils.HashUtils.generateHash;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_academic_mail")
public class AcademicMail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @Column(name = "hash_id", updatable = false)
    private String hashId = generateHash();

    @Column(name="institution")
    private String institution;

    @Column(name="domain", nullable = false, unique = true)
    private String domain;

    @Column(name="valid")
    private boolean valid = true;


}
