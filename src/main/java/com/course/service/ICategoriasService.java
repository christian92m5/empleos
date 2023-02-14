package com.course.service;

import java.util.List;

import com.course.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoriasService {

	void guardar(Categoria categoria);
	List<Categoria> buscarTodas();

	Page<Categoria> buscarTodas(Pageable page);

	Categoria buscarPorId(Integer idCategoria);

	void eliminar(Integer idCategoria);
}
