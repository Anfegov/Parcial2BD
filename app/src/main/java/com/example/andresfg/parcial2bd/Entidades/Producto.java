package com.example.andresfg.parcial2bd.Entidades;

public class Producto {
    private Integer idProducto;
    private String nombreProducto;
    private Integer disponiblidad;
    private Integer valorUnitario;
    private Integer idUsuario;

    public Producto(Integer idProducto , String nombreProducto , Integer disponiblidad , Integer valorUnitario , Integer idUsuario) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.disponiblidad = disponiblidad;
        this.valorUnitario = valorUnitario;
        this.idUsuario = idUsuario;
    }

    public Producto(){}

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getDisponiblidad() {
        return disponiblidad;
    }

    public void setDisponiblidad(Integer disponiblidad) {
        this.disponiblidad = disponiblidad;
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
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", disponiblidad=" + disponiblidad +
                ", valorUnitario=" + valorUnitario +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
