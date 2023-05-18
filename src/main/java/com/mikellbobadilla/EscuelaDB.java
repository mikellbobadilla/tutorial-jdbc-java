package com.mikellbobadilla;

import java.sql.*;

public class EscuelaDB {

  private Connection connection = null;

  public EscuelaDB() throws SQLException{
    try{
      connect();
      consult("López");
      transaccion();
    }finally {
      close();
    }
  }

  public void connect() throws SQLException {
    String jdbc = "jdbc:mysql://localhost:3306/escuela";
    connection = DriverManager.getConnection(jdbc, "practice", "helloWorld");
    connection.setAutoCommit(false);
  }

  public void transaccion() throws SQLException {
    final String PROFESOR = "INSERT INTO profesores(id_profesor, nombre, apellidos) VALUES(? , ? , ?)";
    final String ASIGNATURA = "INSERT INTO asignaturas(id_asignatura, nombre, profesor) VALUES(?, ?, ?)";
    PreparedStatement profesor = null, asignatura = null;
    try{
      profesor = connection.prepareStatement(PROFESOR);
      profesor.setInt(1, 60);
      profesor.setString(2, "Mónica");
      profesor.setString(3, "De La Vega");
      profesor.executeUpdate();

      asignatura = connection.prepareStatement(ASIGNATURA);
      asignatura.setInt(1, 110);
      asignatura.setString(2, "Fundamentos de Lógica");
      asignatura.setInt(3, 60);
      asignatura.executeUpdate();

      connection.commit();
    }catch (SQLException exc){
      connection.rollback();
      exc.printStackTrace();
    }finally {
      if(profesor != null){
        profesor.close();
      }
      if(asignatura != null){
        asignatura.close();
      }
    }
  }


  /**
   * Método usando la clase Statement normal.
   */
  public void consult() throws SQLException{
    Statement statement = connection.createStatement();
    String query = "SELECT id_alumno, nombre, apellidos FROm alumnos";
    ResultSet set = statement.executeQuery(query);
    while (set.next()){
      var student = new StudentDAO(
        set.getInt("id_alumno"),
        set.getString("nombre") + " " + set.getString("apellidos")
      );
      System.out.println(student);
    }
    set.close();
    statement.close();
  }

  /**
   * Usando PreparedStatement para evitar injeción SQL.
   */
  public void consult(String apellidos)throws SQLException{
    Statement statement= connection.createStatement();
    String query = "SELECT id_alumno, nombre, apellidos FROM alumnos WHERE apellidos = ?";
    PreparedStatement statement1 = connection.prepareStatement(query);
    statement1.setString(1, apellidos);
    ResultSet set = statement1.executeQuery();
    while(set.next()){
      var student = new StudentDAO(
        set.getInt("id_alumno"),
        set.getString("nombre") + " " + set.getString("apellidos")
      );
      System.out.println(student);
    }
    set.close();
    statement.close();
  }

  public void close() throws SQLException{
    if(connection != null){
      connection.close();
    }
  }
}
