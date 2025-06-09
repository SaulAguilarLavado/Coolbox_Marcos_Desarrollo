package com.coolbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.coolbox.model.Reclamacion;

public interface ReclamacionRepository extends JpaRepository<Reclamacion, Long> {
} 