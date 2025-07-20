package com.coolbox.repository;

import com.coolbox.model.NombreRol;
import com.coolbox.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(NombreRol nombre);
    boolean existsByNombre(NombreRol nombre);
}