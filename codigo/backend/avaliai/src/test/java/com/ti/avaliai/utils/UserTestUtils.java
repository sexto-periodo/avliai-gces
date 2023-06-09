package com.ti.avaliai.utils;

import com.ti.avaliai.auth.AuthenticationService;
import com.ti.avaliai.auth.dto.RegisterRequestDTO;
import com.ti.avaliai.user.User;
import com.ti.avaliai.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.ti.avaliai.user.Role.ADMIN;

@Service
public class UserTestUtils {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    public User createDefaultTestUser(){
        var user = RegisterRequestDTO.builder()
                .firstname("User")
                .lastname("User")
                .email("testuser@sga.pucminas.br")
                .password("1234")
                .universityHashId("543b45c583bfff6c30e44a751103a24f")
                .courseHashId("eb5ed7359d0bc0df70e6b7abf8584c5e")
                .role(ADMIN)
                .build();

        authenticationService.register(user);
        return userService.findByEmail(user.getEmail());
    }

    public User createRandomTestUser(){
        var user = RegisterRequestDTO.builder()
                .firstname("User")
                .lastname("User")
                .email("randomuser@sga.pucminas.br")
                .password("1234")
                .universityHashId("543b45c583bfff6c30e44a751103a24f")
                .courseHashId("eb5ed7359d0bc0df70e6b7abf8584c5e")
                .role(ADMIN)
                .build();

        authenticationService.register(user);
        return userService.findByEmail(user.getEmail());
    }

    public void setUserContextHolder(User user){
        Authentication authentication = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return user;
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return null;
            }
        };
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void clearUserContextHolder(){
        SecurityContextHolder.clearContext();
    }
}
