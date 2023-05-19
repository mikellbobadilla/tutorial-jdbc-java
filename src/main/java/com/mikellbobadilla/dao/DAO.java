package com.mikellbobadilla.dao;

import com.mikellbobadilla.dao.mysql.DAOException;

import java.util.List;

public interface DAO<T, K> {

  void insertar(T a) throws DAOException;
  void modificar(T a);
  void eliminar(T a);
  List<T> obtenerTodos();
  T obtener(K id);
}
