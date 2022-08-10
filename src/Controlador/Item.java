/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author Chris
 */
public class Item {

    //atributos
    private int id_item;
    private int id_pedido;
    private int id_producto;
    private int cantidad;
    private double monto;
    //Constructores

    public Item() {
        this.id_item = 0;
        this.id_pedido = 0;
        this.id_producto = 0;
        this.cantidad = 0;
        this.monto = 0.0;
    }

    public Item(int id1, int id2, int id3, int cant, double mon) {
        this.id_item = id1;
        this.id_pedido = id2;
        this.id_producto = id3;
        this.cantidad = cant;
        this.monto = mon;
    }

    //getters and setters
    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
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

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    @Override
    public String toString() {
        return "Item{" + "id_item=" + id_item + ", cantidad=" + cantidad + ", monto=" + monto + '}';
    }
    
}
