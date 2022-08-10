/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.Concepto;
import Controlador.Producto;
import Modelo.conexion;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//Para usar la bd
/**
 *
 * @author Chris
 */
public class CRUDProductos {

    //Objeto para conexion
    conexion con = new conexion();

    //
    public int insertar(Producto x) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        int id = 0;
        try {
            String sentencia = "INSERT INTO producto (id_concepto, nombre, descripcion, stock, precio) VALUES (?, ?, ?, ?, ?)";

            ps = (PreparedStatement) con.conecta().prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, x.getId_concepto());
            ps.setString(2, x.getNombre());
            ps.setString(3, x.getDescripcion());
            ps.setInt(4, x.getStock());
            ps.setDouble(5, x.getPrecio());
            ps.execute();
            rs = (ResultSet) ps.getGeneratedKeys();//Obteniendo el id generado
            if (rs.next()) {
                //System.out.println("Id: " + rs.getInt(1));
                id = rs.getInt(1);
            }
            con.desconecta();
            JOptionPane.showMessageDialog(null, "Agregado");
        } catch (SQLException e) {
            System.out.println("Error al insertar" + e);
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

        return id;
    }

    public DefaultTableModel lista(String buscar) { //buscamos y devolvemos un modelo de tabla con los datos
        ResultSet rs = null;
        PreparedStatement ps = null;
        String consulta = "SELECT * FROM producto WHERE id_producto LIKE ? OR nombre LIKE ?";
        String[] columnas = {"ID", "Concepto", "Nombre", "Descripcion", "Stock", "Precio"}; //titulo de las columnas
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);//modelo de tabla
        try {
            ps = (PreparedStatement) con.conecta().prepareStatement(consulta);
            ps.setString(1, buscar + "%");
            ps.setString(2, buscar + "%");

            rs = ps.executeQuery();//obteniendo datos

            while (rs.next()) {
                Object[] fila = {rs.getInt("id_producto"), rs.getInt("id_concepto"), rs.getString("nombre"), rs.getString("descripcion"), rs.getInt("stock"), rs.getDouble("precio")};
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

    public void actualizar(Producto x) {
        PreparedStatement ps = null;

        try {
            String sentencia = "UPDATE producto SET id_concepto = ?, nombre = ?, descripcion=?,stock = ?, precio = ? WHERE producto.id_producto = ?";

            ps = (PreparedStatement) con.conecta().prepareStatement(sentencia);

            ps.setInt(1, x.getId_concepto());
            ps.setString(2, x.getNombre());
            ps.setString(3, x.getDescripcion());
            ps.setInt(4, x.getStock());
            ps.setDouble(5, x.getPrecio());
            ps.setInt(6, x.getId_producto());
            ps.executeUpdate();
            con.desconecta();
            //JOptionPane.showMessageDialog(null, "Actualizado");
        } catch (SQLException e) {
            System.out.println("Error al actualizar" + e);
        } finally {
            try {

                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    public void eliminar(int x) {

        PreparedStatement ps = null;
        try {
            String sentencia = "DELETE FROM producto WHERE producto.id_producto = ?";
            ps = (PreparedStatement) con.conecta().prepareStatement(sentencia);
            ps.setInt(1, x);
            ps.execute();
            con.desconecta();

            JOptionPane.showMessageDialog(null, "Eliminado");
        } catch (SQLException e) {
            System.out.println("Error al eliminar " + e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public Producto busca(int x) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Producto p = new Producto();
        CRUDConcepto n = new CRUDConcepto();
        try {
            String consulta = "SELECT * FROM producto WHERE id_producto = ? ";

            ps = (PreparedStatement) con.conecta().prepareStatement(consulta);
            ps.setInt(1, x);
            rs = ps.executeQuery();//obteniendo datos
            if (rs.next()) {
                p.setId_producto(rs.getInt("id_producto"));
                p.setId_concepto(rs.getInt("id_concepto"));
                Concepto c = n.Busca(rs.getInt("id_concepto"));
                p.setConcepto(c.getConcepto());
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setStock(rs.getInt("stock"));
                p.setPrecio(rs.getDouble("precio"));
            }
            con.desconecta(); //Desconectado

        } catch (SQLException e) {
            System.out.println("Error al consultar " + e);
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
        return p;
    }
}
