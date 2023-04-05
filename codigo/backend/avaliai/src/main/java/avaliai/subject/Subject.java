package avaliai.subject;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Subject {
    @Id
    @SequenceGenerator(name = "subject_sequence", sequenceName = "subject_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_sequence")
    private long id;
    private String name;
    private String picUrl;
    private String campus;
    private double grade;
    // private List<Comment> comments

    public Subject() {

    }

    public Subject(String name, String picUrl, String campus, double grade) {
        setName(name);
        setPicUrl(picUrl);
        setCampus(campus);
        setGrade(grade);
    }

}
