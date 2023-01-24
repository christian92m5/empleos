package com.course.controller;

import com.course.model.Vacante;
import com.course.service.IVacantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


import java.util.Date;
import java.util.LinkedList;

@Controller
public class HomeController {

    @Autowired
    @Qualifier("vacantesServiceJpa")
    private IVacantesService vacantesService;

    @GetMapping("/tabla")
    public String showTable(Model model){
        var lista = vacantesService.buscarTodas();
        model.addAttribute("vacantes", lista);
        return "tabla";
    }

    @GetMapping("/detalle")
    public String showDetail(Model model){
        var vacante = new Vacante();
        vacante.setNombre("Ingeniero de Comunicaciones");
        vacante.setDescripcion("Se soliocita ingeniero para dar soporte a la intranet");
        vacante.setFecha(new Date());
        vacante.setSalario(9700.0);
        model.addAttribute("vacante", vacante);
        return "detalle";

    }

    @GetMapping("/listado")
    public String showList(Model model){
        var list = new LinkedList<String>();
        list.add("Ingeniero de Sistemas");
        list.add("Auxiliar de Contabilidad");
        list.add("Vendedor");
        list.add("Arquitecto");

        model.addAttribute("empleos", list);

        return "listado";
    }

    @GetMapping("/")
    public String showHome(){

        return "home";
    }

    @ModelAttribute
    public void setGenericos(Model model){

        model.addAttribute("vacantes", vacantesService.buscarDestacadas());

    }


}
