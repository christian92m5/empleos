package com.course.controller;

import com.course.service.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private IUsuariosService usuariosService;


    @GetMapping("/index")
	public String mostrarIndex(Model model) {

    	// Ejercicio
    	var usuarios = usuariosService.buscarTodos();
		model.addAttribute("usuarios", usuarios);

		return "usuarios/listUsuarios";
	}
    
    @GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {		    	
		
    	// Ejercicio.
		usuariosService.eliminar(idUsuario);
		attributes.addFlashAttribute("msg", "El usuario ha sido eliminado");

		return "redirect:/usuarios/index";
	}
}
