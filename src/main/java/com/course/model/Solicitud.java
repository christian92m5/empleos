package com.course.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Solicitudes")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date fecha;
    private String archivo;
    private String comentarios;

    @OneToOne
    @JoinColumn(name= "idVacante")
    private Vacante vacante;

    @OneToOne
    @JoinColumn(name= "idUsuario")
    private Usuario usuario;


    public Solicitud(){
        this.fecha = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", archivo='" + archivo + '\'' +
                ", comentarios='" + comentarios + '\'' +
                ", vacante=" + vacante +
                ", usuario=" + usuario +
                '}';
    }
}
