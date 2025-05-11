package com.coolbox.Controllers;
import com.coolbox.model.SuggestionForm;
import com.coolbox.model.ComplaintForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

    // Página de sugerencias
    @GetMapping("/general/sugerencias")
    public String sugerencias(Model model) {
        model.addAttribute("suggestionForm", new SuggestionForm());
        return "general/Sugerencias"; // Carga templates/general/Sugerencias.html
    }

    @PostMapping("/general/sugerencias")
    public String procesarSugerencias(SuggestionForm suggestionForm, Model model) {
        model.addAttribute("message", "Gracias por tu sugerencia.");
        model.addAttribute("suggestionForm", new SuggestionForm()); // Limpia los campos del formulario
        return "general/Sugerencias"; // Carga templates/general/Sugerencias.html
    }

    // Página de reclamaciones
    @GetMapping("/general/reclamaciones")
    public String reclamaciones(Model model) {
        model.addAttribute("complaintForm", new ComplaintForm());
        return "general/Reclamaciones"; // Carga templates/general/Reclamaciones.html
    }

    @PostMapping("/general/reclamaciones")
    public String procesarReclamaciones(ComplaintForm complaintForm, Model model) {
        model.addAttribute("message", "Tu reclamación ha sido enviada.");
        model.addAttribute("complaintForm", new ComplaintForm()); // Limpia los campos del formulario
        return "general/Reclamaciones"; // Carga templates/general/Reclamaciones.html
    }
}