package com.example.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.Reserva;

@Repository
public class ReservaDAO {

    private final JdbcTemplate jdbcTemplate;

    public ReservaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Reserva> obtenerTodos() {
        String sql = "SELECT id, usuario_id, espacio_id, fecha_inicio, fecha_fin, total_pagar FROM reserva";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Reserva(
            rs.getLong("id"),
            rs.getLong("usuario_id"),
            rs.getLong("espacio_id"),
            rs.getTimestamp("fecha_inicio") != null ? rs.getTimestamp("fecha_inicio").toLocalDateTime() : null,
            rs.getTimestamp("fecha_fin") != null ? rs.getTimestamp("fecha_fin").toLocalDateTime() : null,
            rs.getObject("total_pagar", Double.class)
        ));
    }
    
    public void guardar(Reserva reserva) {
        String sql = "INSERT INTO reserva (usuario_id, espacio_id, fecha_inicio, fecha_fin, total_pagar) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, 
            reserva.getUsuarioId(), 
            reserva.getEspacioId(), 
            reserva.getFechaInicio(), 
            reserva.getFechaFin(), 
            reserva.getTotalPagar()
        );
    }

    public void actualizar(Reserva reserva) {
        String sql = "UPDATE reserva SET usuario_id = ?, espacio_id = ?, fecha_inicio = ?, fecha_fin = ?, total_pagar = ? WHERE id = ?";
        jdbcTemplate.update(sql, 
            reserva.getUsuarioId(), 
            reserva.getEspacioId(), 
            reserva.getFechaInicio(), 
            reserva.getFechaFin(), 
            reserva.getTotalPagar(), 
            reserva.getId()
        );
    }

    public void eliminar(Long id) {
        String sql = "DELETE FROM reserva WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}