package com.ti.avaliai.subject;

import java.util.List;
import java.util.Optional;

/* import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.stereotype.Repository;
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long>{

    @Query("SELECT sub FROM Subject sub WHERE sub.name = ?1")
    Optional<Subject> findSubjectByName(String name);

    Subject findSubjectById(Long id);

    List<Subject> findAllByIdIn(List<Long> subjectsIds);
}
