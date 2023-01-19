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
public class EmpleosApplication implements CommandLineRunner {

	@Autowired
	private CategoriasRepository categoriasRepository;

	@Autowired
	private VacantesRepository vacantesRepository;

	@Autowired
	private UsuariosRepository usuariosRepository;

	@Autowired
	private PerfilesRepository perfilesRepository;

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
		//buscarTodasPaginableOrdenada();
		//buscarVacantes();
		//guardarVacante();
		//guardarPerfiles();
		//crearUsuarioConPerfiles();
		//buscarUsuario();
		//buscarVacantesPorEstatus();
		//buscarVacantesPorDesatacadoYEstatus();
		//buscarVacantesSalario();
		buscarVacantePorVariosEstatus();
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

	private void buscarVacantes(){
		var vacantes = vacantesRepository.findAll();
		for (var vac : vacantes){
			System.out.println("Vacante encontrada: "+ vac.getId()+" "+ vac.getNombre()+ " categoria: "+vac.getCategoria().getNombre());
		}
	}

	private void guardarVacante(){
		Vacante vacante = new Vacante();
		vacante.setNombre("Profesor de Matematicas");
		vacante.setDescripcion("Escuela primaria solicita profesor para curso de Matematicas");
		vacante.setFecha(new Date());
		vacante.setSalario(8500.0);
		vacante.setEstatus("Aprobada");
		vacante.setDestacado(0);
		vacante.setImagen("escuela.png");
		vacante.setDetalles("<h1>Los requisitos para profesor de Matematicas</h1>");
		Categoria cat = new Categoria();
		cat.setId(15);
		vacante.setCategoria(cat);
		vacantesRepository.save(vacante);
	}

	private void guardarPerfiles(){
		perfilesRepository.saveAll(getPerfilesAplicacion());
	}

	/**
	 * Metodo que regresa una lista de objetos de tipo Perfil que representa los diferentes PERFILES
	 * O ROLES que tendremos en nuestra aplicación de Empleos
	 * @return
	 */
	private List<Perfil> getPerfilesAplicacion(){
		var lista = new LinkedList<Perfil>();
		var per1 = new Perfil();
		per1.setPerfil("SUPERVISOR");

		var per2 = new Perfil();
		per2.setPerfil("ADMINISTRADOR");

		var per3 = new Perfil();
		per3.setPerfil("USUARIO");

		lista.add(per1);
		lista.add(per2);
		lista.add(per3);

		return lista;
	}

	private void crearUsuarioConPerfiles(){
		var u = new Usuario();
		u.setNombre("Christian Guamán");
		u.setEmail("chri@l.es");
		u.setFechaRegistro(new Date());
		u.setUsername("chris");
		u.setPassword("1234");
		u.setEstatus(1);

		var p = new Perfil();
		p.setId(2);

		var p2 = new Perfil();
		p2.setId(3);

		u.agregar(p);
		u.agregar(p2);

		usuariosRepository.save(u);
	}

	private void buscarUsuario(){
		usuariosRepository.findById(1)
				.ifPresent( u ->{
					System.out.println("Usuario encontrada: "+ u.getId()+" "+ u.getNombre());

					for (var p : u.getPerfiles()){
						System.out.println("Perfil: "+ p.getId()+" "+ p.getPerfil());
					}
				});


	}

	private void buscarVacantesPorEstatus(){

		var res = vacantesRepository.findByEstatus("Aprobada");
		System.out.println("VACANTES ENCONTRADOS: "+ res.size());

		for (var v : res){
			System.out.println("Vacante: "+ v.getId()+" "+ v.getNombre()+ " "+ v.getEstatus());
		}
	}

	private void buscarVacantesPorDesatacadoYEstatus(){

		var res = vacantesRepository.findByDestacadoAndEstatusOrderByIdDesc(1,"Aprobada");
		System.out.println("VACANTES ENCONTRADOS: "+ res.size());

		for (var v : res){
			System.out.println("Vacante: "+ v.getId()+" "+ v.getNombre()+ " "+ v.getEstatus()+ " "+ v.getDestacado());
		}
	}

	private void buscarVacantesSalario(){

		var res = vacantesRepository.findBySalarioBetweenOrderBySalarioDesc(7000.0,14000.0);
		System.out.println("VACANTES ENCONTRADOS: "+ res.size());

		for (var v : res){
			System.out.println("Vacante: "+ v.getId()+" "+ v.getNombre()+ " "+ v.getSalario());
		}
	}

	private void buscarVacantePorVariosEstatus(){

		var res = vacantesRepository.findByEstatusIn(List.of("Eliminada","Creada"));
		System.out.println("VACANTES ENCONTRADOS: "+ res.size());

		for (var v : res){
			System.out.println("Vacante: "+ v.getId()+" "+ v.getNombre()+ " "+ v.getEstatus());
		}
	}
}
