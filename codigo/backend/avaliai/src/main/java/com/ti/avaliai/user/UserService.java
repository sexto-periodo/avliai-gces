package com.ti.avaliai.user;

import com.ti.avaliai.user.dto.UserDeleteRequestDTO;
import com.ti.avaliai.user.dto.VerifyEmailRequestDTO;
import com.ti.avaliai.user.dto.VerifyEmailResponseDTO;
import com.ti.avaliai.utils.EmailUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(
                "Não conseguimos encontrar o usuário com este email"));
    }

    public void deleteUser(UserDeleteRequestDTO userDeleteRequest) {
        Optional<User> user = this.userRepository.findByEmail(userDeleteRequest.getEmail());
        if (user.isEmpty()) {
            throw new RuntimeException("Usário inexistente!");
        }
        this.userRepository.deleteById(user.get().getId());
    }

    public VerifyEmailResponseDTO verifyEmail(VerifyEmailRequestDTO verifyEmailRequestDTO) {
        return VerifyEmailResponseDTO.builder()
                .validEmail(EmailUtils.isValidEmail(verifyEmailRequestDTO.getEmail()))
                .email(verifyEmailRequestDTO.getEmail())
                .build();
    }
}
