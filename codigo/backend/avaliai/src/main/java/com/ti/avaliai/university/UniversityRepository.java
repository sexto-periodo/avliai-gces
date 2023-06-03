package com.ti.avaliai.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University,Long>{

    Optional<University> findByName(String name);

    Optional<University> findById(Long id);

    Optional<University> findByHashId(String hashId);
}
