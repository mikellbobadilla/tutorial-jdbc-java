package com.mikellbobadilla.escuela.DAO.alumnosDAO;

import com.mikellbobadilla.escuela.repositories.AsignaturaRepository;
import com.mikellbobadilla.escuela.models.Asignatura;

import java.sql.Connection;
import java.util.List;

public class MySQLAsinaturaDAO implements AsignaturaRepository {

    private Connection conn;

    public MySQLAsinaturaDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Asignatura a) {

    }

    @Override
    public void modificar(Asignatura a) {

    }

    @Override
    public void eliminar(Asignatura a) {

    }

    @Override
    public List<Asignatura> obtenerTodos() {
        return null;
    }

    @Override
    public Asignatura obtener(Long id) {
        return null;
    }
}
