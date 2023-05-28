package com.ti.avaliai.security.services;

import com.ti.avaliai.models.UserCredential;
import com.ti.avaliai.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserCredentialRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserCredential userCredential = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("UserCredential Not Found with username: " + username));

    return UserDetailsImpl.build(userCredential);
  }

}
