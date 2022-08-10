/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author Chris
 */
abstract public class Persona {
     //atributos

        private String nombre;
        private String appaterno;
        private String apmaterno;
        private int edad;
        private String sexo;

        //getters and setters
        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getAppaterno() {
            return appaterno;
        }

        public void setAppaterno(String appaterno) {
            this.appaterno = appaterno;
        }

        public String getApmaterno() {
            return apmaterno;
        }

        public void setApmaterno(String apmaterno) {
            this.apmaterno = apmaterno;
        }

        public int getEdad() {
            return edad;
        }

        public void setEdad(int edad) {
            this.edad = edad;
        }

        public String getSexo() {
            return sexo;
        }

        public void setSexo(String sexo) {
            this.sexo = sexo;
        }

}
