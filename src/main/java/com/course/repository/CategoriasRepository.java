package com.course.repository;

import com.course.model.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriasRepository extends CrudRepository<Categoria, Integer> {
}
