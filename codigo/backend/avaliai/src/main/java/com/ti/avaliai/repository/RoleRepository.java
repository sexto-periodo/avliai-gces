package com.ti.avaliai.repository;

import java.util.Optional;

import com.ti.avaliai.models.ERole;
import com.ti.avaliai.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
