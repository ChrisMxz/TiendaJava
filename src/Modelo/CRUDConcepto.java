/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.Concepto;
import Modelo.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chris
 */
public class CRUDConcepto {

    //Objeto para conexion
    conexion con = new conexion();
    
    //
    public void Insertar(Concepto x) {
        PreparedStatement ps = null;
        try {
            String sentencia = "INSERT INTO concepto (id_concepto, concepto) VALUES (?, ?)";
            
            ps = (PreparedStatement) con.conecta().prepareStatement(sentencia);
            ps.setInt(1, x.getId_concepto());
            ps.setString(2, x.getConcepto());
            
            ps.execute();
            con.desconecta();
            
            JOptionPane.showMessageDialog(null,"Agregado");
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e);
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }
    //
    public void Actualizar(int id,Concepto x) {
        PreparedStatement ps = null;
        try {
            String sentencia = "UPDATE concepto SET id_concepto = ?, concepto = ? WHERE concepto.id_concepto = ?";
            
            ps = (PreparedStatement) con.conecta().prepareStatement(sentencia);
            ps.setInt(1, x.getId_concepto());
            ps.setString(2, x.getConcepto());
            ps.setInt(3, id);
            
            ps.execute();
            con.desconecta();
            
            JOptionPane.showMessageDialog(null,"Actualizado");
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e);
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }
    public void Eliminar(int x) {
         PreparedStatement ps = null;
        try {
            String sentencia = "DELETE FROM concepto WHERE concepto.id_concepto = ?";
            
            ps = (PreparedStatement) con.conecta().prepareStatement(sentencia);
            ps.setInt(1, x);
            ps.execute();
            con.desconecta();
            
            JOptionPane.showMessageDialog(null,"Eliminado");
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e);
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

     public DefaultTableModel lista(String buscar) { //buscamos y devolvemos un modelo de tabla con los datos
        ResultSet rs = null;
        PreparedStatement ps = null;
        String consulta = "SELECT * FROM concepto WHERE id_concepto LIKE ? OR concepto LIKE ?";
        String[] columnas = {"ID","Concepto"}; //titulo de las columnas
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);//modelo de tabla
        try {
            ps = (PreparedStatement) con.conecta().prepareStatement(consulta);
            ps.setString(1,buscar+"%");
            ps.setString(2,buscar+"%");
            rs = ps.executeQuery();//obteniendo datos
            while (rs.next()) {
                Object[] fila = {rs.getInt("id_concepto"), rs.getString("concepto")};
                modelo.addRow(fila);
            }
            con.desconecta();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al conectar. " + e.getMessage());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return modelo;
    }

    //Obtenemos el concepto
    public Concepto Busca(int x) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Concepto c = new Concepto();
        try {
            String consulta = "SELECT * FROM concepto WHERE id_concepto = ? ";

            ps = (PreparedStatement) con.conecta().prepareStatement(consulta);
            ps.setInt(1, x);
            rs = ps.executeQuery();//obteniendo datos
            if (rs.next()) {
                c.setId_concepto(rs.getInt("id_concepto"));
                c.setConcepto(rs.getString("concepto")); 
            }
            con.desconecta(); //Desconectado  
        } catch (SQLException e) {
            System.out.println("Error al bucar: " + e);
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return c;
    }
}
