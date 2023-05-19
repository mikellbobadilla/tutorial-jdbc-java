package com.mikellbobadilla.dao.mysql;

import com.mikellbobadilla.dao.MatriculaDAO;
import com.mikellbobadilla.proyect.Matricula;

import java.sql.Connection;
import java.util.List;

public class MySQLMatriculaDAO implements MatriculaDAO {

    private Connection conn;

    public MySQLMatriculaDAO(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insertar(Matricula a) {

    }

    @Override
    public void modificar(Matricula a) {

    }

    @Override
    public void eliminar(Matricula a) {

    }

    @Override
    public List<Matricula> obtenerTodos() {
        return null;
    }

    @Override
    public Matricula obtener(Long id) {
        return null;
    }
}
