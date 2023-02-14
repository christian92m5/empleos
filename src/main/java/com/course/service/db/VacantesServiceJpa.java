package com.course.service.db;

import com.course.model.Vacante;
import com.course.repository.VacantesRepository;
import com.course.service.IVacantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public List<Vacante> buscarDestacadas() {
        return vacantesRepository.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
    }

    @Override
    public void eliminar(Integer idVacante) {

        vacantesRepository.deleteById(idVacante);

    }

    @Override
    public List<Vacante> buscarByExample(Example<Vacante> example) {
        return vacantesRepository.findAll(example);
    }

    @Override
    public Page<Vacante> buscarTodas(Pageable page) {
        return vacantesRepository.findAll(page);
    }
}
