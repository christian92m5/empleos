package com.course.controller;

import com.course.model.Usuario;
import com.course.model.Vacante;
import com.course.service.ICategoriasService;
import com.course.service.IUsuariosService;
import com.course.service.IVacantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Date;
import java.util.LinkedList;

@Controller
public class HomeController {

    @Autowired
    @Qualifier("vacantesServiceJpa")
    private IVacantesService vacantesService;

    @Autowired
    @Qualifier("categoriasServiceJpa")
    private ICategoriasService categoriasService;

    @Autowired
    private IUsuariosService usuariosService;


    @GetMapping("/index")
    public String mostrarIndex(Authentication auth){
       var userName = auth.getName();
        System.out.println("userName: "+userName);
        auth.getAuthorities()
               .forEach( rol -> System.out.println("Rol: "+rol.getAuthority()));
        return "redirect:/";
    }

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

    @GetMapping("/signup")
    public String registrarse(Usuario usuario, Model model) {
        return "formRegistro";
    }

    @PostMapping("/signup")
    public String guardarRegistro(Usuario usuario, BindingResult bindingResult, RedirectAttributes attributes) {
        // Ejercicio.

        if(bindingResult.hasErrors()){
            for(var error: bindingResult.getAllErrors()){
                System.out.println("Ocurrio un error: "+ error.getDefaultMessage());

            }
            return "formRegistro";
        }

        usuariosService.guardar(usuario);

        attributes.addFlashAttribute("msg", "Usuario creado");

        return "redirect:/usuarios/index";
    }

    @GetMapping("/search")
    public String buscar(@ModelAttribute("search") Vacante vacante, Model model){
        System.out.println("vacante "+vacante);

        var matcher = ExampleMatcher.matching()
                .withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());

        var example = Example.of(vacante, matcher);
        var vacantes = vacantesService.buscarByExample(example);
        model.addAttribute("vacantes", vacantes);
        return "home";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ModelAttribute
    public void setGenericos(Model model){
        var vacanteSearch = new Vacante();
        vacanteSearch.reset();
        model.addAttribute("vacantes", vacantesService.buscarDestacadas());
        model.addAttribute("categorias", categoriasService.buscarTodas());
        model.addAttribute("search", vacanteSearch);


    }


    


}
