package com.ti.avaliai.university;

import com.ti.avaliai.course.Course;
import com.ti.avaliai.global.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.List;

import static com.ti.avaliai.utils.HashUtils.generateHash;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_university", uniqueConstraints = {
        @UniqueConstraint(name = "uk_university_hash_id", columnNames = {"hash_id"}),
        @UniqueConstraint(name = "uk_university", columnNames = {"id"})
})
@SQLDelete(sql = "UPDATE t_university SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@ToString(of = {"id", "hashId"})
@EqualsAndHashCode(of = "id", callSuper = false)
public class University extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private long id;

    @Builder.Default
    @Column(name = "hash_id")
    private String hashId = generateHash();

    @Column(name = "name")
    private String name;

    @Column(name = "cnpj")
    private String cnpj;

    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private List<Course> courses;
}

