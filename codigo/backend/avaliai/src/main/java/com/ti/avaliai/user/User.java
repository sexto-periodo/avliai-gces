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
@Table(name = "t_user")
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name = "_user_sequence", sequenceName = "_user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_user_sequence")
    @Column
    private long id;

    @Column(name = "hash_id", updatable = false)
    private String hashId = generateHash();

    @Column(name = "name")
    private String name;

    @Column(name = "email", updatable = false)
    private String email;

    @Column(name = "password")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "is_deleted")
    private boolean isDeleted = false;

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
