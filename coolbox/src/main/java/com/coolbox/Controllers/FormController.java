package com.coolbox.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.coolbox.model.Sugerencia;
import com.coolbox.model.Reclamacion;
import com.coolbox.repository.SugerenciaRepository;
import com.coolbox.repository.ReclamacionRepository;

@Controller
public class FormController {

    @Autowired
    private SugerenciaRepository sugerenciaRepository;

    @Autowired
    private ReclamacionRepository reclamacionRepository;

    // Página de sugerencias
    @GetMapping("/general/sugerencias")
    public String sugerencias(Model model) {
        model.addAttribute("sugerencia", new Sugerencia());
        return "general/Sugerencias";
    }

    @PostMapping("/general/sugerencias")
    public String procesarSugerencias(Sugerencia sugerencia, Model model) {
        sugerenciaRepository.save(sugerencia);
        model.addAttribute("message", "Gracias por tu sugerencia.");
        model.addAttribute("sugerencia", new Sugerencia());
        return "general/Sugerencias";
    }

    // Página de reclamaciones
    @GetMapping("/general/reclamaciones")
    public String reclamaciones(Model model) {
        model.addAttribute("reclamacion", new Reclamacion());
        return "general/Reclamaciones";
    }

    @PostMapping("/general/reclamaciones")
    public String procesarReclamaciones(Reclamacion reclamacion, Model model) {
        reclamacionRepository.save(reclamacion);
        model.addAttribute("message", "Tu reclamación ha sido enviada.");
        model.addAttribute("reclamacion", new Reclamacion());
        return "general/Reclamaciones";
    }
}