package com.ti.avaliai.user;

import com.ti.avaliai.global.domain.exceptions.AuthenticationHttpException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class UserContextHolder {
    public static boolean hasUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken);
    }

    public static User getUser() throws AuthenticationHttpException {
        if (!hasUser()) {
            throw new AuthenticationHttpException("Usuário não autenticado.", HttpStatus.UNAUTHORIZED);
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        return user;
    }

}
