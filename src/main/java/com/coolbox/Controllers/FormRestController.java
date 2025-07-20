package com.coolbox.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.coolbox.model.Sugerencia;
import com.coolbox.model.Reclamacion;
import com.coolbox.service.FormService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FormRestController {

    @Autowired
    private FormService formService;

    // Endpoints para Sugerencias
    @GetMapping("/sugerencias")
    public ResponseEntity<List<Sugerencia>> getAllSugerencias() {
        return ResponseEntity.ok(formService.getAllSugerencias());
    }

    @GetMapping("/sugerencias/{id}")
    public ResponseEntity<Sugerencia> getSugerenciaById(@PathVariable Long id) {
        Optional<Sugerencia> sugerencia = formService.getSugerenciaById(id);
        return sugerencia.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/sugerencias/{id}")
    public ResponseEntity<Sugerencia> updateSugerencia(@PathVariable Long id, @RequestBody Sugerencia sugerenciaDetails) {
        try {
            Sugerencia updatedSugerencia = formService.updateSugerencia(id, sugerenciaDetails);
            return ResponseEntity.ok(updatedSugerencia);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoints para Reclamaciones
    @GetMapping("/reclamaciones")
    public ResponseEntity<List<Reclamacion>> getAllReclamaciones() {
        return ResponseEntity.ok(formService.getAllReclamaciones());
    }

    @GetMapping("/reclamaciones/{id}")
    public ResponseEntity<Reclamacion> getReclamacionById(@PathVariable Long id) {
        Optional<Reclamacion> reclamacion = formService.getReclamacionById(id);
        return reclamacion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/reclamaciones/{id}")
    public ResponseEntity<Reclamacion> updateReclamacion(@PathVariable Long id, @RequestBody Reclamacion reclamacionDetails) {
        try {
            Reclamacion updatedReclamacion = formService.updateReclamacion(id, reclamacionDetails);
            return ResponseEntity.ok(updatedReclamacion);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 