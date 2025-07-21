package com.coolbox.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.coolbox.model.Ubicacion;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UbicacionController {

    @GetMapping("/ciudades")
    public List<String> getCiudades() {
        return Ubicacion.getCiudades();
    }

    @GetMapping("/distritos")
    public List<String> getDistritos(@RequestParam String ciudad) {
        return Ubicacion.getDistritos(ciudad);
    }
} 