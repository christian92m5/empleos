package com.course.repository;

import com.course.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilesRepository extends JpaRepository<Perfil, Integer> {

    Optional<Perfil> findByPerfil(String perfil);
}
