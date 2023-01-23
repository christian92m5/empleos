package com.course.service.db;

import com.course.model.Categoria;
import com.course.repository.CategoriasRepository;
import com.course.service.ICategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Primary
public class CategoriasServiceJpa implements ICategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Override
    public void guardar(Categoria categoria) {
        categoriasRepository.save(categoria);
    }

    @Override
    public List<Categoria> buscarTodas() {
        return categoriasRepository.findAll();
    }

    @Override
    public Categoria buscarPorId(Integer idCategoria) {
        return categoriasRepository.findById(idCategoria).orElse(null);
    }
}
