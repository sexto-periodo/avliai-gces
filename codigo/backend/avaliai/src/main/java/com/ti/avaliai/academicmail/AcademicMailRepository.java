package com.ti.avaliai.academicmail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AcademicMailRepository extends JpaRepository<AcademicMail, Long> {

    Optional<AcademicMail> findByHashId(String hashId);
    Optional<AcademicMail> findByDomain(String domain);
    List<AcademicMail> getAllByValidTrue();
}
