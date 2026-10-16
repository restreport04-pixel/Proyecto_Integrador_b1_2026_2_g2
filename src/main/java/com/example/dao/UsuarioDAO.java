package com.example.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.model.Usuario;

@Repository
public class UsuarioDAO {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Usuario> mapper = (rs, rowNum) -> new Usuario(
        rs.getLong("id"),
        rs.getString("nombre"),
        rs.getString("email"),
        rs.getString("telefono")
    );

    public List<Usuario> obtenerTodos() {
        return jdbcTemplate.query("SELECT id, nombre, email, telefono FROM usuario", mapper);
    }

    public List<Usuario> findAll() {
        return obtenerTodos();
    }

    public void save(Usuario u) {
        jdbcTemplate.update("INSERT INTO usuario (nombre, email, telefono) VALUES (?, ?, ?)", 
            u.getNombre(), u.getEmail(), u.getTelefono());
    }

    public void update(Usuario u) {
        jdbcTemplate.update("UPDATE usuario SET nombre = ?, email = ?, telefono = ? WHERE id = ?", 
            u.getNombre(), u.getEmail(), u.getTelefono(), u.getId());
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM usuario WHERE id = ?", id);
    }
}