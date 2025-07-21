package com.coolbox.Controllers;

import com.coolbox.model.Usuario;
import com.coolbox.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UsuarioService usuarioService;

    // Solo manejar el registro desde los modales, NO login
    @PostMapping("/registro")
    public String registro(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String nombre,
                          @RequestParam String apellido,
                          @RequestParam String ciudad,
                          @RequestParam String distrito,
                          @RequestParam String codigoPostal,
                          HttpSession session,
                          Model model) {
        try {
            Usuario nuevoUsuario = usuarioService.registrarUsuario(username, password, nombre, apellido, ciudad, distrito, codigoPostal);
            
            // Auto-login después del registro
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            // Guardar en sesión para que funcione ${session.usuario}
            session.setAttribute("usuario", nuevoUsuario);
            
            return "redirect:/?success=true";
        } catch (Exception e) {
            return "redirect:/?error=true&message=" + e.getMessage();
        }
    }
}