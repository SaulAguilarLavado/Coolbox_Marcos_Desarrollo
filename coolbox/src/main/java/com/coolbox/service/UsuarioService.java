package com.coolbox.service;

import com.coolbox.model.NombreRol;
import com.coolbox.model.Rol;
import com.coolbox.model.Usuario;
import com.coolbox.repository.RolRepository;
import com.coolbox.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolRepository rolRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Usuario saveUsuario(Usuario usuario) {
        // Verificar si el username ya existe
        if (usuario.getUsername() != null && usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }
        
        // Si no tiene email, usar username como email
        if (usuario.getEmail() == null) {
            usuario.setEmail(usuario.getUsername());
        }
        
        // Verificar si el email ya existe
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        
        // Encriptar contraseña
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setActivo(true);
        usuario.setFechaCreacion(LocalDateTime.now());
        
        // Asignar rol de usuario por defecto
        Set<Rol> roles = new HashSet<>();
        Rol rolUsuario = rolRepository.findByNombre(NombreRol.ROLE_USUARIO)
                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
        roles.add(rolUsuario);
        usuario.setRoles(roles);
        
        return usuarioRepository.save(usuario);
    }
    
    // Método para registro completo desde modal (7 parámetros)
    public Usuario registrarUsuario(String username, String password, String nombre, String apellido, String ciudad, String distrito, String codigoPostal) {
        // Verificar si el usuario ya existe
        if (usuarioRepository.existsByEmail(username)) {
            throw new RuntimeException("El email ya está registrado");
        }
        
        if (usuarioRepository.existsByUsername(username)) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }
        
        // Crear nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setEmail(username);
        usuario.setUsername(username); // Usar email como username
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCiudad(ciudad);
        usuario.setDistrito(distrito);
        usuario.setCodigoPostal(codigoPostal);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setActivo(true);
        usuario.setFechaCreacion(LocalDateTime.now());
        
        // Asignar rol de usuario por defecto
        Set<Rol> roles = new HashSet<>();
        Rol rolUsuario = rolRepository.findByNombre(NombreRol.ROLE_USUARIO)
                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
        roles.add(rolUsuario);
        usuario.setRoles(roles);
        
        return usuarioRepository.save(usuario);
    }
    
    // Método simple para registro básico (3 parámetros)
    public Usuario registrarUsuarioSimple(String email, String nombre, String password) {
        // Verificar si el usuario ya existe
        if (usuarioRepository.existsByEmail(email)) {
            throw new RuntimeException("El email ya está registrado");
        }
        
        // Crear nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setUsername(email); // Usar email como username
        usuario.setNombre(nombre);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setActivo(true);
        usuario.setFechaCreacion(LocalDateTime.now());
        
        // Asignar rol de usuario por defecto
        Set<Rol> roles = new HashSet<>();
        Rol rolUsuario = rolRepository.findByNombre(NombreRol.ROLE_USUARIO)
                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
        roles.add(rolUsuario);
        usuario.setRoles(roles);
        
        return usuarioRepository.save(usuario);
    }
    
    public Usuario crearAdmin(String email, String nombre, String password) {
        // Verificar si el usuario ya existe
        if (usuarioRepository.existsByEmail(email)) {
            throw new RuntimeException("El email ya está registrado");
        }
        
        // Crear nuevo admin
        Usuario admin = new Usuario();
        admin.setEmail(email);
        admin.setUsername(email);
        admin.setNombre(nombre);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setActivo(true);
        admin.setFechaCreacion(LocalDateTime.now());
        
        // Asignar rol de admin
        Set<Rol> roles = new HashSet<>();
        Rol rolAdmin = rolRepository.findByNombre(NombreRol.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
        roles.add(rolAdmin);
        admin.setRoles(roles);
        
        return usuarioRepository.save(admin);
    }
    
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    
    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
    
    // Método requerido por SecurityConfig
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByEmail(username).orElse(
            usuarioRepository.findByUsername(username).orElse(null)
        );
    }
    
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
    
    public boolean existsByUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }
    
    // Método para inicializar roles por defecto
    public void initializeRoles() {
        if (!rolRepository.existsByNombre(NombreRol.ROLE_USUARIO)) {
            rolRepository.save(new Rol(NombreRol.ROLE_USUARIO));
        }
        if (!rolRepository.existsByNombre(NombreRol.ROLE_ADMIN)) {
            rolRepository.save(new Rol(NombreRol.ROLE_ADMIN));
        }
        if (!rolRepository.existsByNombre(NombreRol.ROLE_TRABAJADOR)) {
            rolRepository.save(new Rol(NombreRol.ROLE_TRABAJADOR));
        }
    }

    // Métodos para gestión de trabajadores
    public Usuario crearTrabajador(String username, String nombre, String password) {
        // Verificar si el usuario ya existe
        if (usuarioRepository.existsByEmail(username)) {
            throw new RuntimeException("El email ya está registrado");
        }
        if (usuarioRepository.existsByUsername(username)) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }
        
        // Crear nuevo trabajador
        Usuario trabajador = new Usuario();
        trabajador.setEmail(username);
        trabajador.setUsername(username);
        trabajador.setNombre(nombre);
        trabajador.setPassword(passwordEncoder.encode(password));
        trabajador.setActivo(true);
        trabajador.setFechaCreacion(LocalDateTime.now());
        
        // Asignar rol de trabajador
        Set<Rol> roles = new HashSet<>();
        Rol rolTrabajador = rolRepository.findByNombre(NombreRol.ROLE_TRABAJADOR)
                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
        roles.add(rolTrabajador);
        trabajador.setRoles(roles);
        
        return usuarioRepository.save(trabajador);
    }

    public java.util.List<Usuario> obtenerTodosTrabajadores() {
        return usuarioRepository.findAll().stream()
                .filter(usuario -> usuario.getRoles().stream()
                        .anyMatch(rol -> rol.getNombre() == NombreRol.ROLE_TRABAJADOR))
                .collect(java.util.stream.Collectors.toList());
    }

    public java.util.List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll().stream()
                .filter(usuario -> usuario.getRoles().stream()
                        .anyMatch(rol -> rol.getNombre() == NombreRol.ROLE_USUARIO))
                .collect(java.util.stream.Collectors.toList());
    }

    public Usuario actualizarTrabajador(Long id, String username, String nombre, String password) {
        Usuario trabajador = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));
        
        // Verificar que sea un trabajador
        boolean esTrabajador = trabajador.getRoles().stream()
                .anyMatch(rol -> rol.getNombre() == NombreRol.ROLE_TRABAJADOR);
        if (!esTrabajador) {
            throw new RuntimeException("El usuario no es un trabajador");
        }
        
        // Actualizar datos
        if (!trabajador.getUsername().equals(username)) {
            if (usuarioRepository.existsByUsername(username)) {
                throw new RuntimeException("El nombre de usuario ya está en uso");
            }
            trabajador.setUsername(username);
            trabajador.setEmail(username);
        }
        
        trabajador.setNombre(nombre);
        if (password != null && !password.trim().isEmpty()) {
            trabajador.setPassword(passwordEncoder.encode(password));
        }
        
        return usuarioRepository.save(trabajador);
    }

    public void eliminarTrabajador(Long id) {
        Usuario trabajador = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));
        
        // Verificar que sea un trabajador
        boolean esTrabajador = trabajador.getRoles().stream()
                .anyMatch(rol -> rol.getNombre() == NombreRol.ROLE_TRABAJADOR);
        if (!esTrabajador) {
            throw new RuntimeException("El usuario no es un trabajador");
        }
        
        usuarioRepository.delete(trabajador);
    }

    public Optional<Usuario> obtenerTrabajadorPorId(Long id) {
        return usuarioRepository.findById(id).filter(usuario -> 
                usuario.getRoles().stream()
                        .anyMatch(rol -> rol.getNombre() == NombreRol.ROLE_TRABAJADOR));
    }

    // Método para autenticar trabajadores
    public Usuario autenticarTrabajador(String username, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsernameAndActivo(username, true);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            
            // Verificar que sea un trabajador
            boolean esTrabajador = usuario.getRoles().stream()
                    .anyMatch(rol -> rol.getNombre() == NombreRol.ROLE_TRABAJADOR);
            
            // Verificar contraseña y que sea trabajador
            if (esTrabajador && passwordEncoder.matches(password, usuario.getPassword())) {
                return usuario;
            }
        }
        
        return null;
    }
}