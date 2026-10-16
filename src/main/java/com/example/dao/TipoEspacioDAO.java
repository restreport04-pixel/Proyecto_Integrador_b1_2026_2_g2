package com.example.dao;

import com.example.model.TipoEspacio;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoEspacioDAO {

    private final JdbcTemplate jdbcTemplate;

    public TipoEspacioDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TipoEspacio> obtenerTodos() {
        String sql = "SELECT id, nombre, descripcion FROM tipo_espacio";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new TipoEspacio(
            rs.getLong("id"),
            rs.getString("nombre"),
            rs.getString("descripcion")
        ));
    }
}