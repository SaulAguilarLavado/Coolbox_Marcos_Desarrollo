package com.coolbox.service;

import com.coolbox.model.Usuario;
import com.coolbox.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // Buscar por email primero, luego por username
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailAndActivo(usernameOrEmail, true);
        
        if (usuarioOpt.isEmpty()) {
            usuarioOpt = usuarioRepository.findByUsernameAndActivo(usernameOrEmail, true);
        }
        
        Usuario usuario = usuarioOpt.orElseThrow(() -> 
            new UsernameNotFoundException("Usuario no encontrado: " + usernameOrEmail));
        
        // Actualizar último acceso
        usuario.setUltimoAcceso(LocalDateTime.now());
        usuarioRepository.save(usuario);
        
        return usuario;
    }
}