package com.ti.avaliai.subject;

import com.ti.avaliai.course.Course;
import com.ti.avaliai.review.Review;
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
@Table(name = "t_subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private long id;

    @Column(name="hash_id")
    private String hashId = generateHash();

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name ="campus")
    private String campus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_course")
    private Course course;

    @Column(name = "short_description", columnDefinition = "TEXT")
    private String shortDescription;

    @Column(name = "long_description", columnDefinition = "TEXT")
    private String longDescription;

    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private List<Review> reviews;
}
