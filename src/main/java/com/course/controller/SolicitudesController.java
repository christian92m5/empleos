package com.course.controller;

import com.course.model.Solicitud;
import com.course.service.IVacantesService;
import com.course.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;


@Controller
@RequestMapping("/solicitudes")
public class SolicitudesController {

    @Value("${empleosapp.ruta.cv}")
    private String rutaCV;
    @Qualifier("vacantesServiceJpa")
    @Autowired
    private IVacantesService vacantesService;


    
    @GetMapping("/create/{idVacante}")
    public String crear(Solicitud solicitud, @PathVariable("idVacante") Integer idVacante, Model model){
        System.out.println("idVacante: "+ idVacante);
        var vacante = vacantesService.buscarPorId(idVacante);
        model.addAttribute("vacante", vacante);
        return "solicitudes/formSolicitud";
    }

    @PostMapping("/save")
    public String guardar(Solicitud solicitud, BindingResult result, @RequestParam("archivoCV")MultipartFile multipartFile){


        if(result.hasErrors()){
            for(var error: result.getAllErrors()){
                System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
            }
            return "solicitudes/formSolicitud";
        }

        if(!multipartFile.isEmpty()){
        var nombreArchivo    = Utileria.guardarArchivo(multipartFile, rutaCV);
            if(nombreArchivo != null){
                solicitud.setArchivo(nombreArchivo);
            }
        }

        System.out.println("solicitud: "+ solicitud);




        return "redirect:/";
    }
}
