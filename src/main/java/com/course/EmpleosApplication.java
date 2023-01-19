package com.course;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.course.model.Categoria;
import com.course.model.Perfil;
import com.course.model.Usuario;
import com.course.model.Vacante;
import com.course.repository.CategoriasRepository;
import com.course.repository.PerfilesRepository;
import com.course.repository.UsuariosRepository;
import com.course.repository.VacantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class EmpleosApplication{



	public static void main(String[] args) {
		SpringApplication.run(EmpleosApplication.class, args);
	}



}
