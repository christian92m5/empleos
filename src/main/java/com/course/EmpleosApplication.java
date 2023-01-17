package com.course;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.course.repository.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmpleosApplication implements CommandLineRunner {

	@Autowired
	private CategoriasRepository categoriasRepository;
	public static void main(String[] args) {
		SpringApplication.run(EmpleosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(categoriasRepository);

		guardar();
	}
	private void guardar(){
		System.out.println("Guardar vacantes");
	}
}
