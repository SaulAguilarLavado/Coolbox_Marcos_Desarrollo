package com.coolbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coolbox.model.Sugerencia;
import com.coolbox.model.Reclamacion;
import com.coolbox.repository.SugerenciaRepository;
import com.coolbox.repository.ReclamacionRepository;
import java.util.List;
import java.util.Optional;

@Service
public class FormService {

    @Autowired
    private SugerenciaRepository sugerenciaRepository;

    @Autowired
    private ReclamacionRepository reclamacionRepository;

    // Métodos para Sugerencias
    public List<Sugerencia> getAllSugerencias() {
        return sugerenciaRepository.findAll();
    }

    public Optional<Sugerencia> getSugerenciaById(Long id) {
        return sugerenciaRepository.findById(id);
    }

    public Sugerencia saveSugerencia(Sugerencia sugerencia) {
        return sugerenciaRepository.save(sugerencia);
    }

    public Sugerencia updateSugerencia(Long id, Sugerencia sugerenciaDetails) {
        return sugerenciaRepository.findById(id)
                .map(sugerencia -> {
                    sugerencia.setName(sugerenciaDetails.getName());
                    sugerencia.setDni(sugerenciaDetails.getDni());
                    sugerencia.setSuggestion(sugerenciaDetails.getSuggestion());
                    sugerencia.setCategoria(sugerenciaDetails.getCategoria());
                    return sugerenciaRepository.save(sugerencia);
                })
                .orElseThrow(() -> new RuntimeException("Sugerencia no encontrada con id: " + id));
    }

    // Métodos para Reclamaciones
    public List<Reclamacion> getAllReclamaciones() {
        return reclamacionRepository.findAll();
    }

    public Optional<Reclamacion> getReclamacionById(Long id) {
        return reclamacionRepository.findById(id);
    }

    public Reclamacion saveReclamacion(Reclamacion reclamacion) {
        return reclamacionRepository.save(reclamacion);
    }

    public Reclamacion updateReclamacion(Long id, Reclamacion reclamacionDetails) {
        return reclamacionRepository.findById(id)
                .map(reclamacion -> {
                    reclamacion.setName(reclamacionDetails.getName());
                    reclamacion.setDni(reclamacionDetails.getDni());
                    reclamacion.setComplaint(reclamacionDetails.getComplaint());
                    reclamacion.setCategoria(reclamacionDetails.getCategoria());
                    return reclamacionRepository.save(reclamacion);
                })
                .orElseThrow(() -> new RuntimeException("Reclamación no encontrada con id: " + id));
    }
} 