package com.ti.avaliai.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static com.ti.avaliai.utils.HashUtils.generateHash;

@Data
@Entity
@Inheritance
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "_user")
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name = "_user_sequence", sequenceName = "_user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_user_sequence")
    @Column
    private long id;

    @Column
    private String hash_id = generateHash();
    @Column
    private String name;
    @Column
    private String email;
    @Column
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column
    private boolean deleted;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
