package com.example.andresfg.parcial2bd.Entidades;

public class MateriaPrima {
    private Integer id;
    private String descripcion;
    private Integer disponibilidad;
    private Integer valorUnitario;
    private Integer idUsuario;


    public MateriaPrima(Integer id, String descripcion , Integer disponibilidad , Integer valorUnitario, Integer idUsuario) {
        this.id=id;
        this.descripcion = descripcion;
        this.disponibilidad = disponibilidad;
        this.valorUnitario = valorUnitario;
        this.idUsuario = idUsuario;
    }

    public MateriaPrima(){};

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Integer disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Integer getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Integer valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "MateriaPrima{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", disponibilidad=" + disponibilidad +
                ", valorUnitario=" + valorUnitario +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
