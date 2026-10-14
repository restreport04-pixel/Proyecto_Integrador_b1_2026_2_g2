package com.example.dao;

import com.example.model.Espacio;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EspacioDAO {
    private final JdbcTemplate jdbcTemplate;

    public EspacioDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Espacio> mapper = (rs, rowNum) -> new Espacio(
            rs.getLong("id"),
            rs.getString("nombre"),
            rs.getInt("capacidad")
    );

    public List<Espacio> findAll() {
        return jdbcTemplate.query("SELECT id, nombre, capacidad FROM espacio", mapper);
    }

    public void save(Espacio e) {
        jdbcTemplate.update("INSERT INTO espacio (nombre, capacidad) VALUES (?, ?)", e.getNombre(), e.getCapacidad());
    }

    public void update(Espacio e) {
        jdbcTemplate.update("UPDATE espacio SET nombre = ?, capacidad = ? WHERE id = ?", e.getNombre(), e.getCapacidad(), e.getId());
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM espacio WHERE id = ?", id);
    }
}