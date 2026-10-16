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

    public void guardar(Espacio espacio) {
        String sql = "INSERT INTO espacio (nombre, capacidad, precio_hora, tipo_espacio_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, 
            espacio.getNombre(), 
            espacio.getCapacidad(), 
            espacio.getPrecioHora(), 
            espacio.getTipoEspacioId()
        );
    }

    public void actualizar(Espacio espacio) {
        String sql = "UPDATE espacio SET nombre = ?, capacidad = ?, precio_hora = ?, tipo_espacio_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, 
            espacio.getNombre(), 
            espacio.getCapacidad(), 
            espacio.getPrecioHora(), 
            espacio.getTipoEspacioId(), 
            espacio.getId()
        );
    }

    public void eliminar(Long id) {
        String sql = "DELETE FROM espacio WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}