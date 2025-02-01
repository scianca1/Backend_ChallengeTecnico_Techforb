package com.example.backend_challenge_tecnico_techforb.Entitys;

import com.example.backend_challenge_tecnico_techforb.Segurity.Jwt.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario",uniqueConstraints = {@UniqueConstraint(columnNames={"email"})})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(nullable=false)
    private String usuario;
    @Column(nullable=false)
    private String email;
    @Column(nullable=false)
    private String contracenia;
    @Enumerated(EnumType.STRING)
    private Role rol;


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + usuario + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    public String getemail() {
        return this.email;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((rol.name())));
    }

    @Override
    public String getPassword() {
        return this.contracenia;
    }

    @Override
    public String getUsername() {
        return this.getemail();
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
