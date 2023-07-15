package ru.skypro.homework.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class UserEntity implements UserDetails{
    @Id
    private int id;
    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String image;
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder();
        return argon2PasswordEncoder.encode("password");
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}


