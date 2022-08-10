/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author Chris
 */
public class Cliente extends Persona{
    
        //Atributos

        private int id_cliente;
        private String direccion;
        private String telefono;
        //constructores

        public Cliente() {
        }

        public Cliente(int id, String nom, String app, String apm, int ed, String sex, String dir, String tel) {
            this.id_cliente = id;
            this.setNombre(nom);
            this.setAppaterno(app);
            this.setApmaterno(apm);
            this.setEdad(ed);
            this.setSexo(sex);
            this.direccion = dir;
            this.telefono = tel;
        }
        //getters and setters

        public int getId_cliente() {
            return id_cliente;
        }

        public void setId_cliente(int id_cliente) {
            this.id_cliente = id_cliente;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

    @Override
    public String toString() {
        return this.getAppaterno()+" "+this.getApmaterno()+" "+this.getNombre(); //para mostrar el cliente
    }    
    
}
