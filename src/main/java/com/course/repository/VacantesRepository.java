package com.course.repository;

import com.course.model.Vacante;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {

    List<Vacante> findByEstatus(String estatus);

    List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);

    List<Vacante> findBySalarioBetweenOrderBySalarioDesc(Double s1, Double s2);

    List<Vacante> findByEstatusIn(List<String> estatus);

}
