package com.coolbox.config;

import com.coolbox.security.JwtAuthenticationFilter;
import com.coolbox.service.CustomUserDetailsService;
import com.coolbox.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            // Guardar usuario en sesión para que funcione ${session.usuario}
            String username = authentication.getName();
            var usuario = usuarioService.buscarPorUsername(username);
            if (usuario != null) {
                request.getSession().setAttribute("usuario", usuario);
            }
            response.sendRedirect("/?success=true");
        };
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .maximumSessions(1)
            .maxSessionsPreventsLogin(false)
            .and()
            .sessionFixation().migrateSession()
            .invalidSessionUrl("/?expired=true")
            .and()
            .authorizeHttpRequests(authz -> authz
                // Rutas públicas
                .requestMatchers("/", "/login", "/registro", "/auth/**").permitAll()
                .requestMatchers("/css/**", "/js/**", "/img/**", "/style/**").permitAll()
                .requestMatchers("/categorias/**").permitAll()
                .requestMatchers("/general/acerca", "/general/politicas", "/general/terminos", "/general/reembolsos").permitAll()
                .requestMatchers("/api/ciudades", "/api/distritos").permitAll()
                
                // Rutas de administración - PERMITIR TODAS (el AdminController maneja la seguridad)
                .requestMatchers("/admin", "/admin/**").permitAll()
                
                // Rutas de trabajador - PERMITIR TODAS (el TrabajadorController maneja la seguridad con sesiones)
                .requestMatchers("/trabajador", "/trabajador/**").permitAll()
                
                // API de administración - PERMITIR ACCESO (dashboard las necesita)
                .requestMatchers("/api/sugerencias/**", "/api/reclamaciones/**").permitAll()
                
                // Rutas que requieren autenticación básica (USUARIO o ADMIN)
                .requestMatchers("/general/sugerencias", "/general/reclamaciones").authenticated()
                
                // El resto requiere autenticación
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(successHandler())
                .failureUrl("/?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );
        
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
}