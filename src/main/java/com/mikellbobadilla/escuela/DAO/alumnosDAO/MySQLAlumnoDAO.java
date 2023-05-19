package com.mikellbobadilla.escuela.DAO.alumnosDAO;

import com.mikellbobadilla.escuela.exceptions.DAOException;
import com.mikellbobadilla.escuela.repositories.AlumnoRepository;
import com.mikellbobadilla.escuela.models.Alumno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAlumnoDAO implements AlumnoRepository {
  private final Connection conn;
  final String INSERT = "INSERT INTO alumnos(nombre, apellido, fecha_nac) VALUES(?, ?, ?)";
  final String UPDATE = "UPDATE alumnos SET nombre = ?, apellidos = ?, fecha_nac = ?  WHERE id_alumno = ?";
  final String DELETE = "DELETE FROM alumnos WHERE id_alumnos = ?";
  final String GETALL = "SELECT id_alumnos, nombre, apellidos, fecha_nac FROM alumnos";
  final String GETONE = "SELECT id_alumnos, nombre, apellidos, fecha_nac FROM alumnos WHERE id_alumnos = ?";

  public MySQLAlumnoDAO(Connection conn) {
    this.conn = conn;
  }

  @Override
  public void insertar(Alumno a) throws DAOException {
    PreparedStatement stat = null;
    try{
      stat = conn.prepareStatement(INSERT);
      stat.setString(1, a.getNombre());
      stat.setString(2, a.getApellidos());
      stat.setDate(3, new Date(a.getFechaNacimiento().getTime()));
      if(stat.executeUpdate() == 0){
        throw new DAOException("Puede que no se haya guardado");
      }
    } catch (SQLException e) {
      throw new DAOException("Error en SQL", e);
    } finally {
      closeStatement(stat);
    }
  }

  @Override
  public void modificar(Alumno a){
    PreparedStatement stat = null;
    try{
      stat = conn.prepareStatement(UPDATE);
      stat.setString(1, a.getNombre());
      stat.setString(2, a.getApellidos());
      stat.setDate(3, new Date(a.getFechaNacimiento().getTime()));
      stat.setLong(4, a.getId());
      if(stat.executeUpdate() == 0){
        throw new DAOException("Puede que no se haya modificado el modelo.");
      }
    } catch (SQLException e) {
      throw new DAOException("Error en SQL", e);
    } finally {
      closeStatement(stat);
    }
  }
  @Override
  public void eliminar(Alumno a) {
    PreparedStatement stat = null;
    try{
      stat = conn.prepareStatement(DELETE);
      stat.setLong(1, a.getId());
      if(stat.executeUpdate() == 0){
        throw new DAOException("Puede que no se haya eliminado.");
      }
    } catch (SQLException e) {
      throw new DAOException("Error en SQL", e);
    } finally {
      closeStatement(stat);
    }
  }

  @Override
  public List<Alumno> obtenerTodos() {
    PreparedStatement stat = null;
    ResultSet set = null;
    List<Alumno> alumnos = new ArrayList<>();
    try{
      stat  = conn.prepareStatement(GETALL);
      set = stat.executeQuery();
      while(set.next()){
        alumnos.add(convertir(set));
      }
    } catch (SQLException e) {
      throw new DAOException("Error en SQL.",e);
    } finally {
      closeSet(set);
      closeStatement(stat);
    }
    return alumnos;
  }

  @Override
  public Alumno obtener(Long id) {
    PreparedStatement stat = null;
    ResultSet set = null;
    Alumno alumno = null;
    try{
      stat = conn.prepareStatement(GETONE);
      stat.setLong(1, id);
      set = stat.executeQuery();
      if(set.next()){
        alumno = convertir(set);
      }else {
        throw new DAOException("No se ha encontrado ese registro.");
      }
    } catch (SQLException e) {
      throw new DAOException("Error en SQL.", e);
    } finally {
      closeStatement(stat);
      closeSet(set);
    }
    return alumno;
  }
  /* TODO: Remove this */
  /* Don't look this */
  private void closeStatement(PreparedStatement stat){
    if(stat != null){
      try {
        stat.close();
      } catch (SQLException e) {
        throw new DAOException("Error en SQL", e);
      }
    }
  }
  private void closeSet(ResultSet set){
    if(set != null){
      try {
        set.close();
      } catch (SQLException e) {
        throw new DAOException("Error en SQL", e);
      }
    }
  }

  private Alumno convertir(ResultSet set) throws SQLException{
    String nombre = set.getString("nombre");
    String apellidos = set.getString("apellidos");
    Date fechaNac = set.getDate("fecha_nac");
    Alumno alumno = new Alumno(nombre, apellidos, fechaNac);
    alumno.setId(set.getLong("id_alumno"));
    return alumno;
  }
}
