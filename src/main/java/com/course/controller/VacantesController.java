package com.course.controller;

import com.course.service.IVacantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {

    @Autowired
    private IVacantesService vacantesService;

    @GetMapping("/delete")
    public String eliminarVacante(@RequestParam("id") int idVacante, Model model ){
        System.out.println("Borrando la vacante: "+ idVacante);
        model.addAttribute("idVacante",idVacante);
        return "mensaje";
    }

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") int idVacante, Model model){
        var v = vacantesService.buscarPorId(idVacante);
        System.out.println("vacante: "+ v);

        model.addAttribute("vacante", v);
        return "detalle";
    }
}
