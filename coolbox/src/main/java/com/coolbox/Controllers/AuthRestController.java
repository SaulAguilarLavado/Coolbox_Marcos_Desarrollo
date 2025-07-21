package com.coolbox.Controllers;

import com.coolbox.dto.JwtResponse;
import com.coolbox.dto.LoginRequest;
import com.coolbox.dto.RegisterRequest;
import com.coolbox.model.Usuario;
import com.coolbox.security.JwtTokenProvider;
import com.coolbox.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthRestController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateJwtToken(authentication);
            
            Usuario usuario = (Usuario) authentication.getPrincipal();
            String role = usuario.getRoles().iterator().next().getNombre().name();
            
            return ResponseEntity.ok(new JwtResponse(jwt, usuario.getEmail(), usuario.getNombre(), role));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: Credenciales inválidas");
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            if (usuarioService.existsByEmail(registerRequest.getEmail())) {
                return ResponseEntity.badRequest().body("Error: El email ya está en uso");
            }
            
            // Usar el método correcto con 3 parámetros
            Usuario usuario = usuarioService.registrarUsuarioSimple(
                registerRequest.getEmail(),
                registerRequest.getNombre(), 
                registerRequest.getPassword()
            );
            
            return ResponseEntity.ok("Usuario registrado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}