package com.mikellbobadilla.escuela.DAO.alumnosDAO;

import com.mikellbobadilla.escuela.exceptions.DAOException;
import com.mikellbobadilla.escuela.repositories.ProfesorRepository;
import com.mikellbobadilla.escuela.models.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLProfesorDAO implements ProfesorRepository {

  final String INSERT = "INSERT INTO profesores(nombre, apellido) VALUES(?, ?)";
  final String UPDATE = "UPDATE profesores SET nombre = ?, apellidos = ? WHERE id_profesor = ?";
  final String DELETE = "DELETE FROM profesores WHERE id_profesor = ?";
  final String GETALL = "SELECT id_profesor, nombre, apellidos FROM profesores";
  final String GETONE = "SELECT id_profesor, nombre, apellidos FROM profesores WHERE id_profesor = ?";


  private final Connection conn;

  public MySQLProfesorDAO(Connection conn) {
    this.conn = conn;
  }

  @Override
  public void insertar(Profesor a) {
    PreparedStatement stat = null;
    try{
      stat = conn.prepareStatement(INSERT);
      stat.setString(1, a.getNombre());
      stat.setString(2, a.getApellidos());
      if(stat.executeUpdate() ==  0){
        throw new DAOException("Puede que no se haya guardado");
      }
    } catch (SQLException e) {
      throw new DAOException("Error en SQL", e);
    }finally{
      if(stat != null){
        try {
          stat.close();
        } catch (SQLException e) {
          throw new DAOException("Error en SQL", e);
        }
      }
    }
  }

  @Override
  public void modificar(Profesor a) {
    PreparedStatement stat = null;
    try{
      stat = conn.prepareStatement(UPDATE);
      stat.setString(1, a.getNombre());
      stat.setString(2, a.getApellidos());
      stat.setLong(3, a.getId());
      if(stat.executeUpdate() == 0){
        throw new DAOException("Puede que no se haya modificado");
      }
    } catch (SQLException e) {
      throw new DAOException("Error en SQL", e);
    }finally {
      if(stat != null){
        try {
          stat.close();
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  @Override
  public void eliminar(Profesor a) {
    PreparedStatement stat = null;
    try{
      stat = conn.prepareStatement(DELETE);
      stat.setLong(1, a.getId());
      if(stat.executeUpdate() == 0){
        throw new DAOException("Puede que no se haya eliminado");
      }
    } catch (SQLException e) {
      throw new DAOException("Error en SQL", e);
    }finally {
      if(stat != null){
        try {
          stat.close();
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  @Override
  public List<Profesor> obtenerTodos() {
    PreparedStatement stat = null;
    ResultSet set = null;
    List<Profesor> profesores = new ArrayList<>();
    try{
      stat = conn.prepareStatement(GETALL);
      set = stat.executeQuery();
      while (set.next()){
        profesores.add(convertir(set));
      }
    } catch (SQLException e) {
      throw new DAOException("Error en SQL.", e);
    }finally {
      if(stat != null){
        try {
          stat.close();
        } catch (SQLException e) {
          throw new DAOException("Error en SQL", e);
        }
      }
      if(set != null){
        try {
          set.close();
        } catch (SQLException e) {
          throw new DAOException("Error en SQL", e);
        }
      }
    }

    return profesores;
  }

  @Override
  public Profesor obtener(Long id) {
    PreparedStatement stat = null;
    ResultSet rs = null;
    Profesor profesor = null;
    try{
      stat =  conn.prepareStatement(GETONE);
      rs = stat.executeQuery();
      if(rs.next()){
        profesor = convertir(rs);
      }else {
        throw new DAOException("No se encontró a este profesor");
      }
    } catch (SQLException e) {
      throw new DAOException("Error en SQL.", e);
    } finally {
      if(stat != null){
        try {
          stat.close();
        } catch (SQLException e) {
          throw new DAOException("Error en SQL.", e);
        }
        if(rs != null){
          try {
            rs.close();
          } catch (SQLException e) {
            throw new DAOException("Error en SQL.", e);
          }
        }
      }
    }

    return profesor;
  }


  private Profesor convertir(ResultSet set) throws SQLException {
    String nombre = set.getString("nombre");
    String apellidos = set.getString("apellidos");
    Profesor p = new Profesor(nombre, apellidos);
    p.setId(set.getLong("id_profesor"));
    return p;
  }
}
