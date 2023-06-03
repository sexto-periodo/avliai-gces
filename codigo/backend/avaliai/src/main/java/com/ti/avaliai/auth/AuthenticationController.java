package com.ti.avaliai.auth;

import com.ti.avaliai.auth.dto.AuthenticationRequestDTO;
import com.ti.avaliai.auth.dto.AuthenticationResponseDTO;
import com.ti.avaliai.auth.dto.RegisterRequestDTO;
import com.ti.avaliai.global.response.success.NoPayloadSuccessResponse200;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponseDTO> register(
      @RequestBody RegisterRequestDTO request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponseDTO> authenticate(
      @RequestBody AuthenticationRequestDTO request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }

  @PostMapping("/validate-session")
  public ResponseEntity<NoPayloadSuccessResponse200> validateSession() {
    return new ResponseEntity<>(HttpStatus.OK);
  }




}
