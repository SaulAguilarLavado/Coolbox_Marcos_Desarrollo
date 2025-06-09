package com.coolbox.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coolbox.model.Usuario;
import com.coolbox.repository.UsuarioRepository;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

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
            // Verificar si el usuario ya existe
            if (usuarioRepository.findByUsername(username) != null) {
                redirectAttributes.addFlashAttribute("error", "El nombre de usuario ya está en uso");
                return "redirect:/";
            }

            // Crear nuevo usuario
            Usuario usuario = new Usuario();
            usuario.setUsername(username);
            usuario.setPassword(password); // En producción, esto debería estar encriptado
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setCiudad(ciudad);
            usuario.setDistrito(distrito);
            usuario.setCodigoPostal(codigoPostal);

            // Guardar usuario
            usuarioRepository.save(usuario);
            
            redirectAttributes.addFlashAttribute("success", "Usuario registrado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar usuario: " + e.getMessage());
        }

        return "redirect:/";
    }
} 