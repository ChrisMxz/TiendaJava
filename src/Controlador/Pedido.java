/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.sql.Timestamp;

/**
 *
 * @author Chris
 */
public class Pedido {
    //atributos

    private int id_pedido;
    private int id_cliente;
    private int cantidadproductos;
    private double total;
    private Timestamp fecha;
    private String estado;

    //constructores
    public Pedido() {
    }

    public Pedido(int id1, int id2, int cant, double to, Timestamp fec, String est) {
        this.id_pedido = id1;
        this.id_cliente = id2;
        this.cantidadproductos = cant;
        this.total = to;
        this.fecha = fec;
        this.estado = est;
    }
    //getters and setters

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getCantidadproductos() {
        return cantidadproductos;
    }

    public void setCantidadproductos(int cantidadproductos) {
        this.cantidadproductos = cantidadproductos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id_pedido=" + id_pedido + ", id_cliente=" + id_cliente + ", cantidadproductos=" + cantidadproductos + ", total=" + total + ", fecha=" + fecha + ", estado=" + estado + '}';
    }
    
    

}
