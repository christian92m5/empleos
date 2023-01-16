package com.course.controller;

import com.course.model.Vacante;
import com.course.service.ICategoriasService;
import com.course.service.IVacantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {

    @Autowired
    private IVacantesService vacantesService;

    @Autowired
    private ICategoriasService categoriasService;


    @GetMapping("/index")
    public String mostrarIndex(Model model){
        var listado = vacantesService.buscarTodas();

        model.addAttribute("vacantes", listado);

        return "vacantes/listVacantes";
    }

    @GetMapping("/create")
    public String crear(
            Vacante vacante,
            Model model
    ){
        var categorias = categoriasService.buscarTodas();
        model.addAttribute("categorias", categorias);
        return "vacantes/formVacante";
    }

    @PostMapping("/save")
    public String guardar(Vacante vacante, BindingResult bindingResult, RedirectAttributes attributes ){
        if(bindingResult.hasErrors()){
            for(var error: bindingResult.getAllErrors()){
                System.out.println("Ocurrio un error: "+ error.getDefaultMessage());

            }
            return "vacantes/formVacante";
        }
        System.out.println("vacante in: "+ vacante);

        attributes.addFlashAttribute("msg", "Registro guardado");
        vacantesService.guardar(vacante);

        return "redirect:/vacantes/index";
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
