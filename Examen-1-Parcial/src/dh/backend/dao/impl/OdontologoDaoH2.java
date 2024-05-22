package dh.backend.dao.impl;

import dh.backend.dao.IDao;
import dh.backend.db.H2Connection;
import dh.backend.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private static Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);
    private static String SQL_INSERT = "INSERT INTO ODONTOLOGOS VALUES (DEFAULT,?,?,?)";
    private static String SQL_SELECT_ALL = "SELECT * FROM ODONTOLOGOS";


    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontologoRetornado = null;
        OdontologoDaoH2 odontologoDaoH2 = new OdontologoDaoH2();

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            //Odontologo odontologoAGuardar = odontologoDaoH2.registrar(odontologo);
            PreparedStatement pst = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1,odontologo.getNumeroMatricula());
            pst.setString(2,odontologo.getNombre());
            pst.setString(3,odontologo.getApellido());
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            while(rs.next()){
                Integer idEncontrado =  rs.getInt(1);
                odontologoRetornado = new Odontologo(idEncontrado,odontologo.getNumeroMatricula(),odontologo.getNombre(),odontologo.getApellido());
            }
            LOGGER.info("Odontologo a retornar :" + odontologoRetornado.toString() );
            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e) {

            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Ocurrio Rollback" + e.getMessage());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    LOGGER.info("SQL Exception : " + ex.getMessage());
                }
            }
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.info("Ocurrio un erro al cerrar la conexion : " + e.getMessage());
                e.printStackTrace();
            }

        }

        return odontologoRetornado;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        List<Odontologo> listaOdontologos = new ArrayList<>();
        Connection connection = null;
        try {
            connection = H2Connection.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL);

            while(rs.next()){
                Integer idDevuelto = rs.getInt(1);
                Integer numeroMatricula = rs.getInt(2);
                String nombre = rs.getString(3);
                String apellido = rs.getString(4);
                Odontologo o = new Odontologo(idDevuelto,numeroMatricula,nombre,apellido);
                listaOdontologos.add(o);
                LOGGER.info("Odontologo creado : [" + listaOdontologos.size() + "] = " + o.toString());
            }

        } catch (Exception e) {
            LOGGER.info("Ocurrio un error en la SQL_SELECT_ALL "+e.getMessage());
            e.printStackTrace();

        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.info("Ocurrio un erro al cerrar la conexion : " + e.getMessage());
                e.printStackTrace();
            }

        }
        return listaOdontologos;
    }
}
