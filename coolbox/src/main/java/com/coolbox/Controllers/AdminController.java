package com.coolbox.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    // Credenciales de administrador (en producción, esto debería estar en la base de datos)
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    @GetMapping("/admin")
    public String adminLogin() {
        return "admin/login";
    }

    @PostMapping("/admin/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            session.setAttribute("adminLoggedIn", true);
            return "redirect:/admin/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos");
            return "redirect:/admin";
        }
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("adminLoggedIn") == null) {
            redirectAttributes.addFlashAttribute("error", "Debe iniciar sesión para acceder al panel de administración");
            return "redirect:/admin";
        }
        return "admin/dashboard";
    }

    @GetMapping("/admin/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("success", "Sesión cerrada correctamente");
        return "redirect:/admin";
    }
} 