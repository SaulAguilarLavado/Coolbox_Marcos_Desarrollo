package com.coolbox.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class Ubicacion {
    private static final Map<String, List<String>> CIUDADES_DISTRITOS = new HashMap<>();

    static {
        // Lima
        CIUDADES_DISTRITOS.put("Lima", Arrays.asList(
            "Lima", "Ate", "Barranco", "Breña", "Carabayllo", "Chaclacayo", "Chorrillos", 
            "Cieneguilla", "Comas", "El Agustino", "Independencia", "Jesús María", 
            "La Molina", "La Victoria", "Lince", "Los Olivos", "Lurigancho", "Lurín", 
            "Magdalena del Mar", "Miraflores", "Pachacamac", "Pucusana", "Pueblo Libre", 
            "Puente Piedra", "Punta Hermosa", "Punta Negra", "Rímac", "San Bartolo", 
            "San Borja", "San Isidro", "San Juan de Lurigancho", "San Juan de Miraflores", 
            "San Luis", "San Martín de Porres", "San Miguel", "Santa Anita", "Santa María del Mar", 
            "Santa Rosa", "Santiago de Surco", "Surquillo", "Villa El Salvador", "Villa María del Triunfo"
        ));

        // Arequipa
        CIUDADES_DISTRITOS.put("Arequipa", Arrays.asList(
            "Arequipa", "Alto Selva Alegre", "Cayma", "Cerro Colorado", "Characato", 
            "Chiguata", "Jacobo Hunter", "José Luis Bustamante y Rivero", "La Joya", 
            "Mariano Melgar", "Miraflores", "Mollebaya", "Paucarpata", "Pocsi", 
            "Polobaya", "Quequeña", "Sabandía", "Sachaca", "San Juan de Siguas", 
            "San Juan de Tarucani", "Santa Isabel de Siguas", "Santa Rita de Siguas", 
            "Socabaya", "Tiabaya", "Uchumayo", "Vitor", "Yanahuara", "Yarabamba", 
            "Yura"
        ));

        // Trujillo
        CIUDADES_DISTRITOS.put("Trujillo", Arrays.asList(
            "Trujillo", "El Porvenir", "Florencia de Mora", "Huanchaco", "La Esperanza", 
            "Laredo", "Moche", "Poroto", "Salaverry", "Simbal", "Victor Larco Herrera"
        ));

        // Chiclayo
        CIUDADES_DISTRITOS.put("Chiclayo", Arrays.asList(
            "Chiclayo", "Chongoyape", "Eten", "José Leonardo Ortíz", "La Victoria", 
            "Lagunas", "Monsefú", "Nueva Arica", "Oyotún", "Picsi", "Pimentel", 
            "Reque", "San José", "Santa Rosa", "Túcume"
        ));

        // Piura
        CIUDADES_DISTRITOS.put("Piura", Arrays.asList(
            "Piura", "Castilla", "Catacaos", "Cura Mori", "El Tallán", "La Arena", 
            "La Unión", "Las Lomas", "Tambo Grande", "Veintiséis de Octubre"
        ));
    }

    public static List<String> getDistritos(String ciudad) {
        return CIUDADES_DISTRITOS.getOrDefault(ciudad, Arrays.asList());
    }

    public static List<String> getCiudades() {
        return Arrays.asList(CIUDADES_DISTRITOS.keySet().toArray(new String[0]));
    }
} 