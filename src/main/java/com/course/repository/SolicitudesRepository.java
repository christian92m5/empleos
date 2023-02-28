package com.course.repository;

import com.course.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SolicitudesRepository extends JpaRepository<Solicitud, Integer> {
}
