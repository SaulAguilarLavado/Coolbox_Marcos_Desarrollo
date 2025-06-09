package com.coolbox.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.coolbox.model.Sugerencia;
import com.coolbox.model.Reclamacion;
import com.coolbox.repository.SugerenciaRepository;
import com.coolbox.repository.ReclamacionRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FormRestController {

    @Autowired
    private SugerenciaRepository sugerenciaRepository;

    @Autowired
    private ReclamacionRepository reclamacionRepository;

    // Endpoints para Sugerencias
    @GetMapping("/sugerencias")
    public ResponseEntity<List<Sugerencia>> getAllSugerencias() {
        return ResponseEntity.ok(sugerenciaRepository.findAll());
    }

    @GetMapping("/sugerencias/{id}")
    public ResponseEntity<Sugerencia> getSugerenciaById(@PathVariable Long id) {
        Optional<Sugerencia> sugerencia = sugerenciaRepository.findById(id);
        return sugerencia.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/sugerencias/{id}")
    public ResponseEntity<Sugerencia> updateSugerencia(@PathVariable Long id, @RequestBody Sugerencia sugerenciaDetails) {
        return sugerenciaRepository.findById(id)
                .map(sugerencia -> {
                    sugerencia.setName(sugerenciaDetails.getName());
                    sugerencia.setDni(sugerenciaDetails.getDni());
                    sugerencia.setSuggestion(sugerenciaDetails.getSuggestion());
                    sugerencia.setCategoria(sugerenciaDetails.getCategoria());
                    return ResponseEntity.ok(sugerenciaRepository.save(sugerencia));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoints para Reclamaciones
    @GetMapping("/reclamaciones")
    public ResponseEntity<List<Reclamacion>> getAllReclamaciones() {
        return ResponseEntity.ok(reclamacionRepository.findAll());
    }

    @GetMapping("/reclamaciones/{id}")
    public ResponseEntity<Reclamacion> getReclamacionById(@PathVariable Long id) {
        Optional<Reclamacion> reclamacion = reclamacionRepository.findById(id);
        return reclamacion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/reclamaciones/{id}")
    public ResponseEntity<Reclamacion> updateReclamacion(@PathVariable Long id, @RequestBody Reclamacion reclamacionDetails) {
        return reclamacionRepository.findById(id)
                .map(reclamacion -> {
                    reclamacion.setName(reclamacionDetails.getName());
                    reclamacion.setDni(reclamacionDetails.getDni());
                    reclamacion.setComplaint(reclamacionDetails.getComplaint());
                    reclamacion.setCategoria(reclamacionDetails.getCategoria());
                    return ResponseEntity.ok(reclamacionRepository.save(reclamacion));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
} 