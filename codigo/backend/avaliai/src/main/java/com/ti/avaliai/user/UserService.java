package com.ti.avaliai.user;

import com.ti.avaliai.academicmail.AcademicEmailService;
import com.ti.avaliai.global.domain.exceptions.EntityNotFoundException;
import com.ti.avaliai.user.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AcademicEmailService academicEmailService;

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(
                "Não conseguimos encontrar o usuário com este email", HttpStatus.NOT_FOUND));
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
                .validEmail(academicEmailService.isValidEmail(verifyEmailRequestDTO.getEmail()))
                .email(verifyEmailRequestDTO.getEmail())
                .build();
    }

    public UserDTO getUserDTO() {
        return userToUserDTO(UserContextHolder.getUser());
    }
    public User getUser() {
        return UserContextHolder.getUser();
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
                .profilePhotoUrl(user.getProfilePhotoUrl())
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

    public User findByHashId(String hashId) {
        return userRepository.findByHashId(hashId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário do HashId "+hashId+" não encontrado.", HttpStatus.NOT_FOUND));
    }

    public UserDTO updateUser(UpdateUserRequestDTO request) {
        User user = getUser();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setProfilePhotoUrl(request.getProfilePhotoUrl());
        return userToUserDTO(userRepository.save(user));
    }
}
