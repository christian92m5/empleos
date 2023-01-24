package com.course.controller;

import com.course.model.Vacante;
import com.course.service.ICategoriasService;
import com.course.service.IVacantesService;
import com.course.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {

    @Autowired
    @Qualifier("vacantesServiceJpa")
    private IVacantesService vacantesService;

    @Autowired
    @Qualifier("categoriasServiceJpa")
    private ICategoriasService categoriasService;

    @Value("${empleosapp.ruta.imagenes}")
    private String ruta;

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

        return "vacantes/formVacante";
    }

    @PostMapping("/save")
    public String guardar(Vacante vacante, BindingResult bindingResult, RedirectAttributes attributes,
                          @RequestParam("archivoImagen") MultipartFile multiPart
                          ){
        if(bindingResult.hasErrors()){
            for(var error: bindingResult.getAllErrors()){
                System.out.println("Ocurrio un error: "+ error.getDefaultMessage());

            }
            return "vacantes/formVacante";
        }

        if (!multiPart.isEmpty()) {
            //String ruta = "/empleos/img-vacantes/"; // Linux/MAC
            //String ruta = "/Users/christianguaman/Desktop/empleos/img-vacantes/"; // Windows
            String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
            if (nombreImagen != null){ // La imagen si se subio
                // Procesamos la variable nombreImagen
                vacante.setImagen(nombreImagen);
            }
        }


        System.out.println("vacante in: "+ vacante);

        attributes.addFlashAttribute("msg", "Registro guardado");
        vacantesService.guardar(vacante);

        return "redirect:/vacantes/index";
    }

    @GetMapping("/delete/{id}")
    public String eliminarVacante(@PathVariable("id") int idVacante, RedirectAttributes redirectAttributes ){
        System.out.println("Borrando la vacante: "+ idVacante);
        vacantesService.eliminar(idVacante);
        redirectAttributes.addFlashAttribute("msg", "La vacante ha sido eliminado");
        return "redirect:/vacantes/index";
    }

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") int idVacante, Model model){
        var v = vacantesService.buscarPorId(idVacante);
        System.out.println("vacante: "+ v);

        model.addAttribute("vacante", v);
        return "detalle";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int idVacante, Model model){

        var vacante = vacantesService.buscarPorId(idVacante);

        model.addAttribute("vacante", vacante);


        return "vacantes/formVacante";
    }

    @ModelAttribute
    public void setGenericos(Model model){

        var categorias = categoriasService.buscarTodas();
        model.addAttribute("categorias", categorias);

    }


    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
