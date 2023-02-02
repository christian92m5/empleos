package com.course.service;

import java.util.List;

import com.course.model.Categoria;

public interface ICategoriasService {

	void guardar(Categoria categoria);
	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);

	void eliminar(Integer idCategoria);
}
