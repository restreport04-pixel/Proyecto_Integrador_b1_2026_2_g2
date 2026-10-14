package com.example.dao;

import com.example.model.Reserva;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservaDAO {
    private final JdbcTemplate jdbcTemplate;

    public ReservaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Reserva> mapper = (rs, rowNum) -> new Reserva(
            rs.getLong("id"),
            rs.getLong("usuario_id"),
            rs.getLong("espacio_id"),
            rs.getDate("fecha").toLocalDate()
    );

    public List<Reserva> findAll() {
        return jdbcTemplate.query("SELECT id, usuario_id, espacio_id, fecha FROM reserva", mapper);
    }

    public void save(Reserva r) {
        jdbcTemplate.update("INSERT INTO reserva (usuario_id, espacio_id, fecha) VALUES (?, ?, ?)",
                r.getUsuarioId(), r.getEspacioId(), r.getFecha());
    }

    public void update(Reserva r) {
        jdbcTemplate.update("UPDATE reserva SET usuario_id = ?, espacio_id = ?, fecha = ? WHERE id = ?",
                r.getUsuarioId(), r.getEspacioId(), r.getFecha(), r.getId());
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM reserva WHERE id = ?", id);
    }
}