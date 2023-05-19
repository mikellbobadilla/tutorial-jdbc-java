package com.mikellbobadilla.escuela.DAO.alumnosDAO;

import com.mikellbobadilla.escuela.repositories.MatriculaRepository;
import com.mikellbobadilla.escuela.models.Matricula;

import java.sql.Connection;
import java.util.List;

public class MySQLMatriculaDAO implements MatriculaRepository {

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
