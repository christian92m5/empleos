package com.course.service;

import com.course.model.Solicitud;
import com.course.repository.SolicitudesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudesServiceImpl implements ISolicitudesService{

    @Autowired
    private SolicitudesRepository solicitudesRepository;

    @Override
    public void guardar(Solicitud solicitud) {
        solicitudesRepository.save(solicitud);
    }

    @Override
    public void eliminar(Integer idSolicitud) {
        solicitudesRepository.deleteById(idSolicitud);
    }

    @Override
    public List<Solicitud> buscarTodas() {
        return solicitudesRepository.findAll();
    }

    @Override
    public Solicitud buscarPorId(Integer idSolicitud) {
        return solicitudesRepository.findById(idSolicitud).orElse(null);
    }

    @Override
    public Page<Solicitud> buscarTodas(Pageable page) {
        return solicitudesRepository.findAll(page);
    }
}
