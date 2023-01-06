package com.course.service;

import com.course.model.Vacante;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Service
public class VacantesServiceImpl implements IVacantesService{
    private LinkedList<Vacante> listado;

    public VacantesServiceImpl(){

        var sdf = new SimpleDateFormat("dd-MM-yyyy");
        listado = new LinkedList<>();


        try {
            var vacante1 = new Vacante();
            vacante1.setId(1);
            vacante1.setNombre("Ingeniero Civil");
            vacante1.setDescripcion("Se solicita Ingeniero Civil");
            vacante1.setFecha(sdf.parse("01-01-2023"));
            vacante1.setSalario(14000.0);
            vacante1.setDestacado(1);
            vacante1.setImagen("empresa1.png");

            var vacante2 = new Vacante();
            vacante2.setId(2);
            vacante2.setNombre("Contador Público");
            vacante2.setDescripcion("Se solicita Contador Público");
            vacante2.setFecha(sdf.parse("05-12-2022"));
            vacante2.setSalario(12000.0);
            vacante2.setDestacado(0);
            vacante2.setImagen("empresa2.png");


            var vacante3 = new Vacante();
            vacante3.setId(3);
            vacante3.setNombre("Ingeniero Electrico");
            vacante3.setDescripcion("Se solicita Ingeniero Electrico");
            vacante3.setFecha(sdf.parse("22-12-2022"));
            vacante3.setSalario(10500.0);
            vacante3.setDestacado(0);

            var vacante4 = new Vacante();
            vacante4.setId(4);
            vacante4.setNombre("Diseñador Grafico");
            vacante4.setDescripcion("Se solicita Diseñador Grafico");
            vacante4.setFecha(sdf.parse("29-11-2022"));
            vacante4.setSalario(7500.0);
            vacante4.setDestacado(1);
            vacante4.setImagen("empresa3.png");


            listado.add(vacante1);
            listado.add(vacante2);
            listado.add(vacante3);
            listado.add(vacante4);


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vacante> buscarTodas() {
        return listado;
    }

    @Override
    public Vacante buscarPorId(Integer id) {
        for (Vacante v : listado){
            if(v.getId() == id){
                return v;
            }
        }
        return null;
    }
}
