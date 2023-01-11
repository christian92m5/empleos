package com.course.controller;

import com.course.model.Vacante;
import com.course.service.IVacantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {

    @Autowired
    private IVacantesService vacantesService;

    

    @GetMapping("/create")
    public String crear(){
        return "vacantes/formVacante";
    }

    @PostMapping("/save")
    public String guardar(Vacante vacante){
        System.out.println("vacante in: "+ vacante);

        vacantesService.guardar(vacante);

        return "vacantes/listVacantes";
    }

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

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
