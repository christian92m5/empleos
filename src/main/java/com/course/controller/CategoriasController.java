package com.course.controller;

import com.course.model.Categoria;
import com.course.service.ICategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/categorias")
public class CategoriasController {

    @Autowired
    private ICategoriasService categoriasService;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        var categorias = categoriasService.buscarTodas();
        model.addAttribute("categorias",categorias);
        return "categorias/listCategorias";
    }

    @GetMapping("/create")
    public String crear(Categoria categoria) {
        return "categorias/formCategoria";
    }

    @PostMapping("/save")
    public String guardar(Categoria categoria, BindingResult result, RedirectAttributes attributes ) {

        if(result.hasErrors()){
            for(var error: result.getAllErrors()){
                System.out.println("Ocurrio un error: "+ error.getDefaultMessage());

            }
            return"categorias/formCategoria";
        }
        System.out.println("categorias in: "+ categoria);

        attributes.addFlashAttribute("msg", "Registro guardado");
        categoriasService.guardar(categoria);
        return "redirect:/categorias/index";
    }
}
