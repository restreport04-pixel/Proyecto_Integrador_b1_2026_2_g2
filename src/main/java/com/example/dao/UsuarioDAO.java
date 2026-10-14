package com.example.dao;

import com.example.model.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioDAO {
    private final JdbcTemplate jdbcTemplate;

    public UsuarioDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Usuario> mapper = (rs, rowNum) -> new Usuario(
            rs.getLong("id"),
            rs.getString("nombre"),
            rs.getString("email")
    );

    public List<Usuario> findAll() {
        return jdbcTemplate.query("SELECT id, nombre, email FROM usuario", mapper);
    }

    public void save(Usuario u) {
        jdbcTemplate.update("INSERT INTO usuario (nombre, email) VALUES (?, ?)", u.getNombre(), u.getEmail());
    }

    public void update(Usuario u) {
        jdbcTemplate.update("UPDATE usuario SET nombre = ?, email = ? WHERE id = ?", u.getNombre(), u.getEmail(), u.getId());
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM usuario WHERE id = ?", id);
    }
}