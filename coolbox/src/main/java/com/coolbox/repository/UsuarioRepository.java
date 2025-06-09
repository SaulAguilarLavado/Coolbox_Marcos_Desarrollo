package com.coolbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.coolbox.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
} 