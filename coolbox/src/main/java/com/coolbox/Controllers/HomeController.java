package com.coolbox.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

     @GetMapping("/")
    public String index() {
        return "index"; // Carga templates/index.html
    }

    @GetMapping("/productos/audio")
    public String audio() {
        return "productos/audio"; // Carga templates/productos/audio.html
    }

    @GetMapping("/productos/celulares")
    public String celulares() {
        return "productos/celulares"; // Carga templates/productos/celulares.html
    }

    @GetMapping("/productos/computo")
    public String computo() {
        return "productos/computo"; // Carga templates/productos/computo.html
    }

    @GetMapping("/productos/fotografia")
    public String fotografia() {
        return "productos/fotografia"; // Carga templates/productos/fotografia.html
    }

    @GetMapping("/productos/gaming")
    public String gaming() {
        return "productos/gaming"; // Carga templates/productos/gaming.html
    }

    @GetMapping("/productos/movilidad")
    public String movilidad() {
        return "productos/movilidad"; // Carga templates/productos/movilidad.html
    }

    @GetMapping("/productos/tv")
    public String tv() {
        return "productos/tv"; // Carga templates/productos/tv.html
    }

    @GetMapping("/productos/pilas")
    public String pilas() {
        return "productos/pilas"; // Carga templates/productos/pilas.html
    }

    @GetMapping("/productos/oficina")
    public String oficina() {
        return "productos/oficina"; // Carga templates/productos/oficina.html
    }

    @GetMapping("/productos/smartHome")
    public String smartHome() {
        return "productos/smartHome"; // Carga templates/productos/smartHome.html
    }

    @GetMapping("/general/acerca")
    public String acerca() {
        return "general/acerca"; // Carga templates/general/acerca.html
    }

    @GetMapping("/general/politicas")
    public String politicas() {
        return "general/politicas"; // Carga templates/general/politicas.html
    }

    @GetMapping("/general/terminos")
    public String terminos() {
        return "general/terminos"; // Carga templates/general/terminos.html
    }

    @GetMapping("/general/reembolsos")
    public String reembolsos() {
        return "general/reembolsos"; // Carga templates/general/reembolsos.html
    }
}
