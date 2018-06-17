package com.example.andresfg.parcial2bd.Entidades;

import java.util.Date;

public class Venta {
    private int idVenta;
    private int idCliente;
    private int Total;
    private String Datetime;
    private int idUsuario;

    public Venta(int idCliente , int total , String datetime , int idUsuario) {
        this.idCliente = idCliente;
        Total = total;
        Datetime = datetime;
        this.idUsuario = idUsuario;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public String getDatetime() {
        return Datetime;
    }

    public void setDatetime(String datetime) {
        Datetime = datetime;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", idCliente=" + idCliente +
                ", Total=" + Total +
                ", Datetime=" + Datetime +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
