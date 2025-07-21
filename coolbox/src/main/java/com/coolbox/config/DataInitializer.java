package com.coolbox.config;

import com.coolbox.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Override
    public void run(String... args) throws Exception {
        // Inicializar roles
        usuarioService.initializeRoles();
        
        // Crear admin por defecto si no existe
        if (!usuarioService.existsByEmail("admin@coolbox.com")) {
            usuarioService.crearAdmin("admin@coolbox.com", "Administrador", "admin123");
            System.out.println("Admin creado: admin@coolbox.com / admin123");
        }
    }
}