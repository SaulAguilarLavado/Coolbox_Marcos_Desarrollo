package com.coolbox.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index"; // Carga templates/index.html
    }

    @GetMapping("/categorias/audio")
    public String audio() {
        return "categorias/audio"; // Carga templates/categorias/audio.html
    }

    @GetMapping("/categorias/celulares")
    public String celulares() {
        return "categorias/celulares"; // Carga templates/categorias/celulares.html
    }

    @GetMapping("/categorias/computo")
    public String computo() {
        return "categorias/computo"; // Carga templates/categorias/computo.html
    }

    @GetMapping("/categorias/fotografia")
    public String fotografia() {
        return "categorias/fotografia"; // Carga templates/categorias/fotografia.html
    }

    @GetMapping("/categorias/gaming")
    public String gaming() {
        return "categorias/gaming"; // Carga templates/categorias/gaming.html
    }

    @GetMapping("/categorias/movilidad")
    public String movilidad() {
        return "categorias/movilidad"; // Carga templates/categorias/movilidad.html
    }

    @GetMapping("/categorias/tv")
    public String tv() {
        return "categorias/tv"; // Carga templates/categorias/tv.html
    }

    @GetMapping("/categorias/pilas")
    public String pilas() {
        return "categorias/pilas"; // Carga templates/categorias/pilas.html
    }

    @GetMapping("/categorias/oficina")
    public String oficina() {
        return "categorias/oficina"; // Carga templates/categorias/oficina.html
    }

    @GetMapping("/categorias/smartHome")
    public String smartHome() {
        return "categorias/smartHome"; // Carga templates/categorias/smartHome.html
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