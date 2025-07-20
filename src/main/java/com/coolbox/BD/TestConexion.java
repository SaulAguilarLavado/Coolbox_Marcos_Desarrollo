package com.coolbox.BD;

import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestConexion implements CommandLineRunner {

    @Autowired
    private Conexion conexion;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Verificando la conexión persistente...");
        
        Connection conn = conexion.getConexion();
        if (conn != null) {
            System.out.println("Conexión activa y funcionando");
            System.out.println("URL: " + conn.getMetaData().getURL());
            System.out.println("Usuario: " + conn.getMetaData().getUserName());
            System.out.println("La conexión permanecerá activa hasta que se detenga la aplicación");
        }
    }
} 