package com.course.controller;

import com.course.model.Categoria;
import com.course.service.ICategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/categorias")
public class CategoriasController {

    @Autowired
    @Qualifier("categoriasServiceJpa")
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

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idCategoria, RedirectAttributes redirectAttributes ){
        System.out.println("Borrando la categoria: "+ idCategoria);
        categoriasService.eliminar(idCategoria);
        redirectAttributes.addFlashAttribute("msg", "La categoria ha sido eliminado");
        return "redirect:/categorias/index";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int idCategoria, Model model ){
        System.out.println("Editar la categoria: "+ idCategoria);
        var categoria = categoriasService.buscarPorId(idCategoria);
        model.addAttribute("categoria", categoria);
        return"categorias/formCategoria";
    }
}
