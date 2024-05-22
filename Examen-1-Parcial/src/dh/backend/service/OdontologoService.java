package dh.backend.service;

import dh.backend.dao.IDao;
import dh.backend.model.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo registrarOdontolog(Odontologo o){
        return odontologoIDao.registrar(o);
    }

    public List<Odontologo> buscarTodosLosOdontologos(){
        return odontologoIDao.buscarTodos();
    }
}
