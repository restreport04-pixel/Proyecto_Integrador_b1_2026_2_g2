package com.example.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.model.Espacio;

public class EspacioDAO {

    private final JdbcTemplate jdbcTemplate;

    public EspacioDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Espacio> obtenerTodos() {
        String sql = "SELECT id, nombre, capacidad, precio_hora, tipo_espacio_id FROM espacio";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Espacio(
            rs.getLong("id"),
            rs.getString("nombre"),
            rs.getInt("capacidad"),
            rs.getDouble("precio_hora"),
            rs.getObject("tipo_espacio_id", Long.class)
        ));
    }
}