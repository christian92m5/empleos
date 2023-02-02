package com.course.service;

import com.course.model.Vacante;

import java.util.List;

public interface IVacantesService {

    List<Vacante> buscarTodas();

    Vacante buscarPorId(Integer id);

    void guardar(Vacante vacante);

    List<Vacante> buscarDestacadas();

    void eliminar(Integer idVacante);

}
