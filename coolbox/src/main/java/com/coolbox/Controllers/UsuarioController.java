package com.coolbox.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coolbox.model.Usuario;
import com.coolbox.service.UsuarioService;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public String registrarUsuario(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String ciudad,
            @RequestParam String distrito,
            @RequestParam String codigoPostal,
            RedirectAttributes redirectAttributes) {
        
        try {
            // Crear nuevo usuario
            Usuario usuario = new Usuario();
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setCiudad(ciudad);
            usuario.setDistrito(distrito);
            usuario.setCodigoPostal(codigoPostal);

            // Guardar usuario usando el servicio
            usuarioService.saveUsuario(usuario);
            
            redirectAttributes.addFlashAttribute("success", "Usuario registrado exitosamente");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/";
    }
} 