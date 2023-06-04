package com.ti.avaliai.user;

import com.ti.avaliai.academicmail.AcademicMailService;
import com.ti.avaliai.user.dto.UserDTO;
import com.ti.avaliai.user.dto.UserDeleteRequestDTO;
import com.ti.avaliai.user.dto.VerifyEmailRequestDTO;
import com.ti.avaliai.user.dto.VerifyEmailResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AcademicMailService academicMailService;

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
                .validEmail(academicMailService.isValidEmail(verifyEmailRequestDTO.getEmail()))
                .email(verifyEmailRequestDTO.getEmail())
                .build();
    }

    public UserDTO getUser() {
        return userToUserDTO(UserContextHolder.getUser());
    }

    private UserDTO userToUserDTO(User user) {

        return UserDTO.builder()
                .courseHashId(user.getCourse().getHashId())
                .universityHashId(user.getUniversity().getHashId())
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .hashId(user.getHashId())
                .id(user.getId())
                .lastname(user.getLastname())
                .role(user.getRole())
                .build();
    }

    public List<User> findAll(){
        return userRepository.findAllByIsDeletedIsFalse();
    }

    public void deleteAll() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            user.setDeleted(true);
            user.setDeleteDate(LocalDateTime.now());
        });
        userRepository.saveAll(users);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
