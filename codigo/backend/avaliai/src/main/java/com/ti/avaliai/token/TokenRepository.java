package com.ti.avaliai.token;

import java.util.List;
import java.util.Optional;

import com.ti.avaliai.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

//  @Query(value = """
//      select t from Token t inner join UserCredential u\s
//      on t.user.id = u.id\s
//      where u.id = :id and (t.expired = false or t.revoked = false)\s
//      """)
  List<Token> findAllByUserAndExpiredIsFalseAndRevokedIsFalse(User user);

  Optional<Token> findByToken(String token);
}
