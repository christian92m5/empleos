package com.course.service;

import com.course.model.Categoria;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class CategoriaServiceImpl implements ICategoriasService{

    private LinkedList<Categoria> categorias;

    public CategoriaServiceImpl(){
        this.categorias = new LinkedList<>();
    }

    @Override
    public void guardar(Categoria categoria) {
        categoria.setId(categorias.size()+1);
        categorias.add(categoria);
    }

    @Override
    public List<Categoria> buscarTodas() {
        return categorias;
    }

    @Override
    public Categoria buscarPorId(Integer idCategoria) {

        return categorias.stream()
                .filter(c -> Objects.equals(c.getId(), idCategoria))
                .findFirst()
                .orElse(null);
    }
}
