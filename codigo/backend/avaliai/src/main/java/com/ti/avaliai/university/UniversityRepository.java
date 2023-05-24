package com.ti.avaliai.university;

import java.util.Optional;

/* import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.stereotype.Repository;
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University,Long>{

    Optional<University> findUniversityByName(String name);

    University findUniversityById(Long id);

    University findByHashId(String hashId);
}
