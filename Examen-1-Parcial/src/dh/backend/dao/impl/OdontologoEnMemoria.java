package dh.backend.dao.impl;

import dh.backend.dao.IDao;
import dh.backend.model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
public class OdontologoEnMemoria implements IDao<Odontologo>{
    private Logger LOGGER = Logger.getLogger(OdontologoEnMemoria.class);
    List<Odontologo> listaOdontolos = new ArrayList<>();

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Integer id = listaOdontolos.size() +1 ;
        Odontologo o = new Odontologo(id,odontologo.getNumeroMatricula(),odontologo.getNombre(), odontologo.getApellido());
        listaOdontolos.add(o);
        LOGGER.info("Odontologo fue guardado en Memoria = " + o.toString());
        return o;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        return listaOdontolos;
    }
}
