package com.ti.avaliai.user;

//import com.ti.avaliai.user.dto.UserCreateRequestDTO;
import com.ti.avaliai.user.dto.UserDeleteRequestDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    //private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository/*, BCryptPasswordEncoder passwordEncoder*/) {
        this.userRepository = userRepository;
        //this.passwordEncoder = passwordEncoder;
    }



    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(
                "Não conseguimos encontrar o usuário com este email"));
    }

    /*public User createUser(UserCreateRequestDTO userCreateRequest) {
        User user = new User();
        Optional<User> email = this.userRepository.findByEmail(userCreateRequest.getEmail());

        if (email.isPresent()) {
            throw new RuntimeException("Usário ja cadastrado!");
        }
        user.setEmail(userCreateRequest.getEmail());
        user.setName(userCreateRequest.getName());
        user.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));

        return this.userRepository.save(user);
    }*/

    public void deleteUser(UserDeleteRequestDTO userDeleteRequest) {
        Optional<User> user = this.userRepository.findByEmail(userDeleteRequest.getEmail());
        if (user.isEmpty()) {
            throw new RuntimeException("Usário inexistente!");
        }
        this.userRepository.deleteById(user.get().getId());
    }
}
