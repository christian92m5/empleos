package com.course.service.db;

import com.course.model.Vacante;
import com.course.repository.VacantesRepository;
import com.course.service.IVacantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacantesServiceJpa implements IVacantesService {

    @Autowired
    private VacantesRepository vacantesRepository;
    @Override
    public List<Vacante> buscarTodas() {
        return vacantesRepository.findAll();
    }

    @Override
    public Vacante buscarPorId(Integer id) {
        return vacantesRepository.findById(id).orElse(null);
    }

    @Override
    public void guardar(Vacante vacante) {
        vacantesRepository.save(vacante);

    }
}
