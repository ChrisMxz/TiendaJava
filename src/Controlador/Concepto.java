/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author Chris
 */
public class Concepto {
    //atributos
        private int id_concepto;
        private String concepto;

        //constructores
        public Concepto() {
        }

        ;
        
        public Concepto(int id, String conce) {
            this.id_concepto = id;
            this.concepto = conce;
        }

        ;
        
        //metodos
        //getters and setters
        public int getId_concepto() {
            return id_concepto;
        }

        public void setId_concepto(int id_concepto) {
            this.id_concepto = id_concepto;
        }

        public String getConcepto() {
            return concepto;
        }

        public void setConcepto(String concepto) {
            this.concepto = concepto;
        }
    //Para visualizar datos del objeto 
    @Override
    public String toString() {
        return id_concepto +" - "+concepto;
    }
        
        

    
}
