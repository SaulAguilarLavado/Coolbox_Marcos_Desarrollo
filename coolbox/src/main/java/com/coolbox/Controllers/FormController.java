package com.coolbox.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.coolbox.model.Sugerencia;
import com.coolbox.model.Reclamacion;
import com.coolbox.service.FormService;

@Controller
public class FormController {

    @Autowired
    private FormService formService;

    // Página de sugerencias
    @GetMapping("/general/sugerencias")
    public String sugerencias(Model model) {
        model.addAttribute("sugerencia", new Sugerencia());
        return "general/Sugerencias";
    }

    @PostMapping("/general/sugerencias")
    public String procesarSugerencias(Sugerencia sugerencia, Model model) {
        formService.saveSugerencia(sugerencia);
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
        formService.saveReclamacion(reclamacion);
        model.addAttribute("message", "Tu reclamación ha sido enviada.");
        model.addAttribute("reclamacion", new Reclamacion());
        return "general/Reclamaciones";
    }
}