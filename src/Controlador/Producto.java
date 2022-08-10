/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author Chris
 */
public class Producto extends Concepto {

    //Atributos
    private int id_producto;
    private String nombre;
    private String descripcion;
    private int stock;
    private double precio;

    //constructor sin parametros
    public Producto() {
    }

    ;
        //constructor con parametros
        public Producto(int id, String nom, String des, int sto, double pre) {
        this.id_producto = id;
        this.nombre = nom;
        this.descripcion = des;
        this.stock = sto;
        this.precio = pre;
    }

    ;
        
        //Metodos
        
        //Getters and Setters
        
        public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return  nombre +" "+ descripcion ;
    }
    
    
    
    
}
