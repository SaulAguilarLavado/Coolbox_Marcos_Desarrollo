package com.coolbox.Controllers;

import com.coolbox.service.FormService;
import com.coolbox.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/trabajador")
public class TrabajadorController {

    @Autowired
    private FormService formService;
    
    @Autowired
    private UsuarioService usuarioService;

    // Mostrar página de login para trabajadores
    @GetMapping("/login")
    public String loginForm(Model model) {
        return "trabajador/login";
    }

    // Procesar login de trabajador
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        
        try {
            // Autenticar trabajador usando el servicio
            var trabajador = usuarioService.autenticarTrabajador(username, password);
            
            if (trabajador != null) {
                // Establecer sesión del trabajador
                session.setAttribute("trabajadorLoggedIn", true);
                session.setAttribute("trabajadorId", trabajador.getId());
                session.setAttribute("trabajadorNombre", trabajador.getNombre());
                session.setAttribute("trabajadorEmail", trabajador.getEmail());
                
                // Redirigir al dashboard
                return "redirect:/trabajador/dashboard";
            } else {
                redirectAttributes.addFlashAttribute("error", "Credenciales incorrectas");
                return "redirect:/trabajador/login";
            }
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al iniciar sesión: " + e.getMessage());
            return "redirect:/trabajador/login";
        }
    }

    // Logout del trabajador
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("success", "Sesión cerrada exitosamente");
        return "redirect:/trabajador/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        // Verificar si el trabajador está loggeado
        if (session.getAttribute("trabajadorLoggedIn") == null) {
            redirectAttributes.addFlashAttribute("error", "Debe iniciar sesión como trabajador");
            return "redirect:/trabajador/login";
        }
        
        // Cargar sugerencias y reclamaciones para que el trabajador las vea
        model.addAttribute("sugerencias", formService.getAllSugerencias());
        model.addAttribute("reclamaciones", formService.getAllReclamaciones());
        model.addAttribute("trabajadorNombre", session.getAttribute("trabajadorNombre"));
        
        return "trabajador/dashboard";
    }

    @GetMapping("/sugerencias")
    public String verSugerencias(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("trabajadorLoggedIn") == null) {
            return "redirect:/trabajador/login";
        }
        
        model.addAttribute("sugerencias", formService.getAllSugerencias());
        return "trabajador/sugerencias";
    }

    @GetMapping("/reclamaciones")
    public String verReclamaciones(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("trabajadorLoggedIn") == null) {
            return "redirect:/trabajador/login";
        }
        
        model.addAttribute("reclamaciones", formService.getAllReclamaciones());
        return "trabajador/reclamaciones";
    }

    @GetMapping("/sugerencias/{id}")
    @ResponseBody
    public Object verSugerencia(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("trabajadorLoggedIn") == null) {
            return "{\"error\": \"No autorizado\"}";
        }
        
        return formService.getSugerenciaById(id).orElse(null);
    }

    @GetMapping("/reclamaciones/{id}")
    @ResponseBody
    public Object verReclamacion(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("trabajadorLoggedIn") == null) {
            return "{\"error\": \"No autorizado\"}";
        }
        
        return formService.getReclamacionById(id).orElse(null);
    }
}
