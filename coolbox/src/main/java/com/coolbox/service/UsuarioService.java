package com.coolbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coolbox.model.Usuario;
import com.coolbox.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario getUsuarioByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public Usuario saveUsuario(Usuario usuario) {
        // Verificar si el usuario ya existe
        if (usuarioRepository.findByUsername(usuario.getUsername()) != null) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    // Verificar si el nuevo username ya existe (si se está cambiando)
                    if (!usuario.getUsername().equals(usuarioDetails.getUsername()) &&
                        usuarioRepository.findByUsername(usuarioDetails.getUsername()) != null) {
                        throw new RuntimeException("El nombre de usuario ya está en uso");
                    }
                    
                    usuario.setUsername(usuarioDetails.getUsername());
                    usuario.setPassword(usuarioDetails.getPassword());
                    usuario.setNombre(usuarioDetails.getNombre());
                    usuario.setApellido(usuarioDetails.getApellido());
                    usuario.setCiudad(usuarioDetails.getCiudad());
                    usuario.setDistrito(usuarioDetails.getDistrito());
                    usuario.setCodigoPostal(usuarioDetails.getCodigoPostal());
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    public boolean validateLogin(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        return usuario != null && usuario.getPassword().equals(password);
    }
} 