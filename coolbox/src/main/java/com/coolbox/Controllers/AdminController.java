package com.coolbox.Controllers;

import com.coolbox.model.Usuario;
import com.coolbox.service.UsuarioService;
import com.coolbox.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    // Credenciales de administrador (en producción, esto debería estar en la base de datos)
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FormService formService;

    @GetMapping
    public String adminLogin() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        // Verificar si es el admin
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            session.setAttribute("adminLoggedIn", true);
            session.setAttribute("userRole", "ADMIN");
            return "redirect:/admin/dashboard";
        }
        
        // Verificar si es un trabajador
        Usuario trabajador = usuarioService.autenticarTrabajador(username, password);
        if (trabajador != null) {
            session.setAttribute("trabajadorLoggedIn", true);
            session.setAttribute("trabajadorId", trabajador.getId());
            session.setAttribute("trabajadorNombre", trabajador.getNombre());
            session.setAttribute("userRole", "TRABAJADOR");
            return "redirect:/trabajador/dashboard";
        }
        
        redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos");
        return "redirect:/admin";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        if (session.getAttribute("adminLoggedIn") == null) {
            redirectAttributes.addFlashAttribute("error", "Debe iniciar sesión para acceder al panel de administración");
            return "redirect:/admin";
        }
        
        // Cargar datos para el dashboard
        model.addAttribute("sugerencias", formService.getAllSugerencias());
        model.addAttribute("reclamaciones", formService.getAllReclamaciones());
        model.addAttribute("usuarios", usuarioService.obtenerTodosUsuarios());
        model.addAttribute("trabajadores", usuarioService.obtenerTodosTrabajadores());
        
        return "admin/dashboard";
    }

    @PostMapping("/trabajador/crear")
    public String crearTrabajador(
            @RequestParam String username,
            @RequestParam String nombre,
            @RequestParam String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        if (session.getAttribute("adminLoggedIn") == null) {
            return "redirect:/admin";
        }
        
        try {
            usuarioService.crearTrabajador(username, nombre, password);
            redirectAttributes.addFlashAttribute("success", "Trabajador creado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
        }
        
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/trabajador/actualizar/{id}")
    public String actualizarTrabajador(
            @PathVariable Long id,
            @RequestParam String username,
            @RequestParam String nombre,
            @RequestParam(required = false) String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        if (session.getAttribute("adminLoggedIn") == null) {
            return "redirect:/admin";
        }
        
        try {
            usuarioService.actualizarTrabajador(id, username, nombre, password);
            redirectAttributes.addFlashAttribute("success", "Trabajador actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
        }
        
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/trabajador/eliminar/{id}")
    public String eliminarTrabajador(
            @PathVariable Long id,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        if (session.getAttribute("adminLoggedIn") == null) {
            return "redirect:/admin";
        }
        
        try {
            usuarioService.eliminarTrabajador(id);
            redirectAttributes.addFlashAttribute("success", "Trabajador eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
        }
        
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("success", "Sesión cerrada correctamente");
        return "redirect:/admin";
    }
}