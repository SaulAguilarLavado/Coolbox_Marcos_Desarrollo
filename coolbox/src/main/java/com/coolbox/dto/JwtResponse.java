package com.coolbox.dto;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String email;
    private String nombre;
    private String role;
    
    // Constructores
    public JwtResponse() {}
    
    public JwtResponse(String token, String email, String nombre, String role) {
        this.token = token;
        this.email = email;
        this.nombre = nombre;
        this.role = role;
    }
    
    // Getters y Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}