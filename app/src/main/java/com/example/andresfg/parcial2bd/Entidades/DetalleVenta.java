package com.example.andresfg.parcial2bd.Entidades;

public class DetalleVenta {
    int id_venta;
    int id_producto;
    int cantidad;
    int precio;


    public DetalleVenta(int id_venta , int id_producto , int cantidad , int precio) {
        this.id_venta = id_venta;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" +
                "id_venta=" + id_venta +
                ", id_producto=" + id_producto +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }
}
