package com.coolbox.repository;

import com.coolbox.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmailAndActivo(String email, boolean activo);
    Optional<Usuario> findByUsernameAndActivo(String username, boolean activo);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}