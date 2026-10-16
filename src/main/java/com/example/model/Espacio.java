package com.example.model;

public class Espacio {
    private Long id;
    private String nombre;
    private Integer capacidad;
    private Double precioHora;
    private Long tipoEspacioId;

    public Espacio() {}

    public Espacio(Long id, String nombre, Integer capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public Espacio(Long id, String nombre, Integer capacidad, Double precioHora, Long tipoEspacioId) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precioHora = precioHora;
        this.tipoEspacioId = tipoEspacioId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }

    public Double getPrecioHora() { return precioHora; }
    public void setPrecioHora(Double precioHora) { this.precioHora = precioHora; }

    public Long getTipoEspacioId() { return tipoEspacioId; }
    public void setTipoEspacioId(Long tipoEspacioId) { this.tipoEspacioId = tipoEspacioId; }
}