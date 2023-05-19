package com.mikellbobadilla.escuela.repositories;

import com.mikellbobadilla.escuela.exceptions.DAOException;

import java.sql.SQLException;
import java.util.List;

public interface repository<MODEL, ID> {

  void insertar(MODEL a) throws DAOException;
  void modificar(MODEL a) throws SQLException;
  void eliminar(MODEL a);
  List<MODEL> obtenerTodos();
  MODEL obtener(ID id);
}
