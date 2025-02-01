package com.example.backend_challenge_tecnico_techforb.Repositorys;

import com.example.backend_challenge_tecnico_techforb.Entitys.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByEmail(String email);

}
