/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.Cliente;
import Modelo.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chris
 */
public class CRUDCliente {
    

    //Objeto para conexion
    conexion con = new conexion();

    //
    public int insertar(Cliente x) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        int id = 0;
        try {
            String sentencia = "INSERT INTO cliente (nombre, appaterno, apmaterno, edad, sexo, direccion, telefono) VALUES (?, ?, ?, ?, ?, ?, ?)";

            ps = (PreparedStatement) con.conecta().prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, x.getNombre());
            ps.setString(2, x.getAppaterno());
            ps.setString(3, x.getApmaterno());
            ps.setInt(4, x.getEdad());
            ps.setString(5, x.getSexo());
            ps.setString(6, x.getDireccion());
            ps.setString(7, x.getTelefono());
            ps.executeUpdate();
            rs = (ResultSet) ps.getGeneratedKeys();//Obteniendo el id generado
            if (rs.next()) {
                //System.out.println("Id: " + rs.getInt(1));
                id = rs.getInt(1);
            }
            con.desconecta();

            JOptionPane.showMessageDialog(null, "Agregado");
        } catch (SQLException e) {
            System.out.println("Error insertar: " + e);
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

    //Obtenemos la lista de los clientes
    public DefaultTableModel lista(String buscar) { //buscamos y devolvemos un modelo de tabla con los datos
        ResultSet rs = null;
        PreparedStatement ps = null;
        String consulta = "SELECT * FROM cliente WHERE id_cliente LIKE ? OR nombre LIKE ? OR appaterno LIKE ?";
        String[] columnas = {"id", "Nombre", "Ap Paterno", "Ap Materno", "Edad", "Sexo", "Direccion", "Telefono"}; //titulo de las columnas
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);//modelo de tabla
        try {
            ps = (PreparedStatement) con.conecta().prepareStatement(consulta);
            ps.setString(1,buscar+"%");
            ps.setString(2,buscar+"%");
            ps.setString(3,buscar+"%");
            
            rs = ps.executeQuery();//obteniendo datos

            while (rs.next()) {
                Object[] fila = {rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("appaterno"), rs.getString("apmaterno"), rs.getInt("edad"), rs.getString("sexo"), rs.getString("direccion"), rs.getString("telefono")};
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

    public void actualizar(Cliente x) {
        PreparedStatement ps = null;
        try {
            String sentencia = "UPDATE cliente SET nombre = ?, appaterno=?,apmaterno = ?, edad = ? , sexo = ?, direccion = ?, telefono = ? WHERE cliente.id_cliente = ?";

            ps = (PreparedStatement) con.conecta().prepareStatement(sentencia);
            ps.setString(1, x.getNombre());
            ps.setString(2, x.getAppaterno());
            ps.setString(3, x.getApmaterno());
            ps.setInt(4, x.getEdad());
            ps.setString(5, x.getSexo());
            ps.setString(6, x.getDireccion());
            ps.setString(7, x.getTelefono());
            ps.setInt(8, x.getId_cliente());
            ps.execute();
            con.desconecta();

            JOptionPane.showMessageDialog(null, "Actualizado");
        } catch (SQLException e) {
            System.out.println("Error: al actualizar cliente" + e);
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
            String sentencia = "DELETE FROM cliente WHERE cliente.id_cliente = ?";

            ps = (PreparedStatement) con.conecta().prepareStatement(sentencia);
            ps.setInt(1, x);
            ps.execute();
            con.desconecta();

            JOptionPane.showMessageDialog(null, "Eliminado");
        } catch (SQLException e) {
            System.out.println("Error: Al eliminar cliente" + e);
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
    
    //Obtenemos el cliente por id
    public Cliente busca(int x) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Cliente c = null;
        try {
            String consulta = "SELECT * FROM cliente WHERE id_cliente = ? ";

            ps = (PreparedStatement) con.conecta().prepareStatement(consulta);
            ps.setInt(1, x);
            rs = ps.executeQuery();//obteniendo datos
            if (rs.next()) {
                //creando objeto 
                c = new Cliente(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("appaterno"),rs.getString("apmaterno"), rs.getInt("edad"),rs.getString("sexo"),rs.getString("direccion"),rs.getString("telefono"));
            }
            con.desconecta(); //Desconectado  

        } catch (SQLException e) {
            System.out.println("Error: al consultar Cliente " + e);
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
        return c;

    }
}
