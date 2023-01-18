package com.course;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.course.model.Categoria;
import com.course.repository.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class EmpleosApplication implements CommandLineRunner {

	@Autowired
	private CategoriasRepository categoriasRepository;
	public static void main(String[] args) {
		SpringApplication.run(EmpleosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//guardar();
		//buscarPorId();
		//modificar();
		//eliminar();
		//conteo();
		//eliminarTodo();
		//encontrarPorIds();
		//buscarTodas();
		//existeId();
		//guardarTodas();
		//buscarTodas();
		//findTodosJpa();
		//borrarTodoEnBloque();
		//buscarTodasOrdenadas();
		//buscarTodasPaginable();
		buscarTodasPaginableOrdenada();
	}
	private void guardar(){
		var cat1 = new Categoria();
		cat1.setNombre("Finanzas");
		cat1.setDescripcion("Trabajo relacionado con finanzas y contabilidad");
		categoriasRepository.save(cat1);
		System.out.println("Guardar vacantes "+ cat1);
	}

	private void buscarPorId(){
		var res = categoriasRepository.findById(2);
		System.out.println("Encontrado "+ res.orElse(null));

	}

	private void modificar(){
		var res = categoriasRepository.findById(3);
		res.ifPresent( c -> {
			c.setNombre("Ing. de Software");
			c.setDescripcion("Desarrollo de Sistemas");
			categoriasRepository.save(c);
			System.out.println("Encontrado "+ c);
		});
	}

	private void eliminar(){
		categoriasRepository.deleteById(2);
	}

	private void conteo(){
		var count = categoriasRepository.count();
		System.out.println("Total de categorias "+ count);

	}

	private void eliminarTodo(){
		categoriasRepository.deleteAll();
	}

	private void encontrarPorIds(){
		var categorias = categoriasRepository.findAllById(List.of(1,4,10));
		for (var cat : categorias){
			System.out.println("Categoria encontrada: "+ cat);
		}
	}

	private void buscarTodas(){
		var categorias = categoriasRepository.findAll();
		for (var cat : categorias){
			System.out.println("Categoria encontrada: "+ cat);
		}
	}

	private void existeId(){
		var existe = categoriasRepository.existsById(50);
		System.out.println("Categoria 50 encontrada: "+ existe);

	}

	private void guardarTodas(){
		var nuevasCategorias = getListaCategorias();
		categoriasRepository.saveAll(nuevasCategorias);



	}

	/**
	 * Metodo que regresa una lista de 3 Categorias
	 * @return
	 */
	private List<Categoria> getListaCategorias(){
		var lista = new LinkedList<Categoria>();
		// Categoria 1
		Categoria cat1 = new Categoria();
		cat1.setNombre("Programador de Blockchain");
		cat1.setDescripcion("Trabajos relacionados con Bitcoin y Criptomonedas");

		// Categoria 2
		Categoria cat2 = new Categoria();
		cat2.setNombre("Soldador/Pintura");
		cat2.setDescripcion("Trabajos relacionados con soldadura, pintura y enderezado");

		// Categoria 3
		Categoria cat3 = new Categoria();
		cat3.setNombre("Ingeniero Industrial");
		cat3.setDescripcion("Trabajos relacionados con Ingenieria industrial.");

		lista.add(cat1);
		lista.add(cat2);
		lista.add(cat3);
		return lista;
	}

	private void findTodosJpa(){
		var categorias = categoriasRepository.findAll();
		for (var cat : categorias){
			System.out.println("Categoria encontrada: "+ cat.getId()+" "+ cat.getNombre());
		}
	}

	private void borrarTodoEnBloque(){
		categoriasRepository.deleteAllInBatch();
	}

	private void buscarTodasOrdenadas(){
		var categorias = categoriasRepository.findAll(Sort.by("nombre").descending());
		for (var cat : categorias){
			System.out.println("Categoria encontrada: "+ cat);
		}
	}

	private void buscarTodasPaginable(){
		var categorias = categoriasRepository.findAll(PageRequest.of(0,5));
		System.out.println("Total registros: "+categorias.getTotalElements());
		System.out.println("Total paginas: "+categorias.getTotalPages());

		for (var cat : categorias.getContent()){
			System.out.println("Categoria encontrada: "+ cat.getId()+" "+ cat.getNombre());
		}
	}

	private void buscarTodasPaginableOrdenada(){
		var categorias = categoriasRepository.findAll(PageRequest.of(1,5, Sort.by("nombre").descending()));
		System.out.println("Total registros: "+categorias.getTotalElements());
		System.out.println("Total paginas: "+categorias.getTotalPages());

		for (var cat : categorias.getContent()){
			System.out.println("Categoria encontrada: "+ cat.getId()+" "+ cat.getNombre());
		}
	}
}
