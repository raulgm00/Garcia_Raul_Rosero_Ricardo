package dh.backend.test;

import dh.backend.dao.impl.OdontologoDaoH2;
import dh.backend.model.Odontologo;
import dh.backend.service.OdontologoService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OdontologoServiceTest {

    public static Logger LOGGER = Logger.getLogger(OdontologoServiceTest.class);
    OdontologoService odontologoService = new OdontologoService( new OdontologoDaoH2() );



    /*Ejecutar esto antes que nada*/
    @BeforeAll
    static void crearTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/Examen-1-Parcial;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.info(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Test
    @DisplayName("Testear el listado de todos los odontólogos")
    public void testListadoPacientes(){

        Odontologo od = new Odontologo(3333,"Profesora","Fulanita");
        odontologoService.registrarOdontolog(od);
        List<Odontologo> listaOdontologos = odontologoService.buscarTodosLosOdontologos();
        assertEquals(3,listaOdontologos.size());

    }



}