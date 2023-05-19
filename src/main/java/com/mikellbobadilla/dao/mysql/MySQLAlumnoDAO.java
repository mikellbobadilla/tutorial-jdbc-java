package com.mikellbobadilla.dao.mysql;

import com.mikellbobadilla.dao.AlumnoDAO;
import com.mikellbobadilla.proyect.Alumno;

import java.sql.*;
import java.util.List;

public class MySQLAlumnoDAO implements AlumnoDAO {
  private Connection conn;
  final String INSERT = "INSERT INTO alumnos(id_alumno, nombre, apellido, fecha_nac) VALUES(?, ?, ?)";
  final String UPDATE = "UPDATE alumnos SET nombre = ?, apellidos = ?, fecha_nac = ?  WHERE id_alumno = ?";
  final String DELETE = "DELETE FROM alumnos WHERE id_alumnos = ?";
  final String GETALL = "SELECT id_alumnos, nombre, apellido, fecha_nac FROM alumnos";
  final String GETONE = "SELECT id_alumnos, nombre, apellidos, fecha_nac FROM alumnos WHERE id_alumnos = ?";

  public MySQLAlumnoDAO(Connection conn) {
    this.conn = conn;
  }

  @Override
  public void insertar(Alumno a) throws DAOException {
    PreparedStatement stat = null;
    try{
      stat = conn.prepareStatement(INSERT);
      stat.setLong(1, a.getId());
      stat.setString(2, a.getNombre());
      stat.setDate(3, new Date(a.getFechaNacimiento().getTime()));
      if(stat.executeUpdate() == 0){
        throw new DAOException("Puede que no se haya guardado");
      }
    } catch (SQLException e) {
      throw new DAOException("Error en SQL", e);
    } finally {
      if(stat != null){
        try {
          stat.close();
        }catch (SQLException exc){
          throw new DAOException("Error en SQL", exc);
        }
      }
    }
  }

  @Override
  public void modificar(Alumno a) {

  }

  @Override
  public void eliminar(Alumno a) {

  }

  private Alumno convertir(ResultSet set) throws SQLException{
    String nombre = set.getString("nombre");
    String apelldos = set.getString("apellidos");
    Date fechaNac = set.getDate("fecha_nac");
    Alumno alumno = new Alumno(nombre, apelldos, fechaNac);
    alumno.setId(set.getLong("id_alumno"));
    return alumno;
  }

  @Override
  public List<Alumno> obtenerTodos() {
    return null;
  }

  @Override
  public Alumno obtener(Long id) {
    PreparedStatement stat = null;
    ResultSet set = null;
    try{
      stat = conn.prepareStatement(GETONE);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {

    }
    return null;
  }
}
