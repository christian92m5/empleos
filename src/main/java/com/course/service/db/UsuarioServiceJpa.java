package com.course.service.db;

import com.course.model.Perfil;
import com.course.model.Usuario;
import com.course.repository.PerfilesRepository;
import com.course.repository.UsuariosRepository;
import com.course.service.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UsuarioServiceJpa implements IUsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PerfilesRepository perfilesRepository;

    @Override
    public void guardar(Usuario usuario) {
        usuario.setFechaRegistro(new Date());

        var usuarioDefault = perfilesRepository.findByPerfil("USUARIO").orElse(new Perfil(1,"USUARIO"));
        usuario.setPerfiles(List.of(usuarioDefault));
        usuario.setEstatus(1);
        usuariosRepository.save(usuario);
    }

    @Override
    public void eliminar(Integer idUsuario) {
        usuariosRepository.deleteById(idUsuario);
    }

    @Override
    public List<Usuario> buscarTodos() {
       return usuariosRepository.findAll();
    }
}
