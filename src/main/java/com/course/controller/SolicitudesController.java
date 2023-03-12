package com.course.controller;

import com.course.service.IVacantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/solicitudes")
public class SolicitudesController {

    @Qualifier("vacantesServiceJpa")
    @Autowired
    private IVacantesService vacantesService;
    
    @GetMapping("/create/{idVacante}")
    public String crear(@PathVariable("idVacante") Integer idVacante, Model model){
        System.out.println("idVacante: "+ idVacante);
        var vacante = vacantesService.buscarPorId(idVacante);
        model.addAttribute("vacante", vacante);
        return "solicitudes/formSolicitud";
    }
}
