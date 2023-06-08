package com.ti.avaliai.auth;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ti.avaliai.academicmail.AcademicMailService;
import com.ti.avaliai.auth.dto.AuthenticationRequestDTO;
import com.ti.avaliai.auth.dto.AuthenticationResponseDTO;
import com.ti.avaliai.auth.dto.RegisterRequestDTO;
import com.ti.avaliai.config.JwtService;
import com.ti.avaliai.course.CourseService;
import com.ti.avaliai.global.domain.exceptions.InvalidEmailException;
import com.ti.avaliai.token.Token;
import com.ti.avaliai.token.TokenRepository;
import com.ti.avaliai.token.TokenType;
import com.ti.avaliai.university.UniversityService;
import com.ti.avaliai.user.User;
import com.ti.avaliai.user.UserRepository;
import com.ti.avaliai.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.ti.avaliai.utils.HashUtils.generateHash;

@Service
public class AuthenticationService {


  @Autowired
  private UserRepository repository;
  @Autowired
  private TokenRepository tokenRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private  AuthenticationManager authenticationManager;
  @Autowired
  private UniversityService universityService;
  @Autowired
  private CourseService courseService;
  @Autowired
  private AcademicMailService academicMailService;
  @Autowired
  private UserService userService;

  public AuthenticationResponseDTO register(RegisterRequestDTO request) {

    if(!academicMailService.isValidEmail(request.getEmail())){
      throw new InvalidEmailException("E-mail acadêmico inválido", HttpStatus.UNPROCESSABLE_ENTITY);
    }


    if(userService.existsByEmail(request.getEmail())){
      throw new InvalidEmailException("E-mail já cadastrado", HttpStatus.CONFLICT);
    }

    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(request.getRole())
            .university(universityService.findByHashId(request.getUniversityHashId()))
            .course(courseService.findByHashId(request.getCourseHashId()))
            .hashId(generateHash())
        .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponseDTO.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
        .build();
  }

  public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    User user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponseDTO.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
        .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllByUserAndExpiredIsFalseAndRevokedIsFalse(user);
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void logout(User user){
    this.revokeAllUserTokens(user);
  }

  public void clearAllTokens(){
    tokenRepository.deleteAll();
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.repository.findByEmail(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}
