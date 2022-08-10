/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.Item;
import Controlador.Producto;
import Modelo.CRUDCliente;
import Modelo.CRUDProductos;
import Modelo.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chris
 */
public class CRUDItems {

    //Objeto para conexion
    conexion con = new conexion();

    //Obtenemos la lista para eliminar
    public ArrayList listar(int x) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList lista = new ArrayList();
        Item p;
        try {
            String consulta = "SELECT * FROM item WHERE id_pedido=?";
            ps = (PreparedStatement) con.conecta().prepareStatement(consulta);
            ps.setInt(1, x);
            rs = ps.executeQuery();//obteniendo datos

            while (rs.next()) {
                //creando objeto 
                p = new Item(rs.getInt("id_item"), rs.getInt("id_pedido"), rs.getInt("id_producto"), rs.getInt("cantidad"), rs.getDouble("monto"));
                lista.add(p);//Agregando a la lista
            }
            con.desconecta(); //Desconectado

        } catch (SQLException e) {
            System.out.println("Error al consultar" + e);
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
        return lista;

    }

    public DefaultTableModel lista(int x) { //buscamos y devolvemos un modelo de tabla con los datos
        ResultSet rs = null;
        PreparedStatement ps = null;
        CRUDCliente clientes = new CRUDCliente();
        CRUDProductos productos = new CRUDProductos();
        String consulta = "SELECT * FROM item WHERE id_pedido=?";
        String[] columnas = {"ID","ID-Poducto","Descripcion", "Cantidad", "Monto"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);//modelo de tabla
        try {
            ps = (PreparedStatement) con.conecta().prepareStatement(consulta);
            ps.setInt(1, x);
            rs = ps.executeQuery();//obteniendo datos

            while (rs.next()) {
                Producto pro = productos.busca(rs.getInt("id_producto")); //obteniendo el producto dado el id 
                Object[] fila = {rs.getInt("id_item"),rs.getInt("id_producto"), pro.toString(), rs.getInt("cantidad"), rs.getDouble("monto")};
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

    public void insertar(Item x) {
        PreparedStatement ps = null;
        try {
            String sentencia = "INSERT INTO item (id_pedido, id_producto, cantidad, monto) VALUES (?, ?, ?, ?)";
            ps = (PreparedStatement) con.conecta().prepareStatement(sentencia);
            ps.setInt(1, x.getId_pedido());
            ps.setInt(2, x.getId_producto());
            ps.setInt(3, x.getCantidad());
            ps.setDouble(4, x.getMonto());
            ps.execute();
            con.desconecta();
            //JOptionPane.showMessageDialog(null, "Agregado");
        } catch (SQLException e) {
            System.out.println("Error al agregar" + e);
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

    public void actualizar(Item x) {
        PreparedStatement ps = null;
        try {
            String sentencia = "UPDATE item SET cantidad = ?, monto = ? WHERE item.id_item = ?";

            ps = (PreparedStatement) con.conecta().prepareStatement(sentencia);
            ps.setInt(1, x.getCantidad());
            ps.setDouble(2, x.getMonto());
            ps.setInt(3, x.getId_item());
            ps.execute();
            con.desconecta();
            //JOptionPane.showMessageDialog(null, "Actualizado");
        } catch (SQLException e) {
            System.out.println("Error: al actualizar " + e);
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

    public Item busca(int x) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Item p = new Item();
        try {
            String consulta = "SELECT * FROM item WHERE id_item = ? ";

            ps = (PreparedStatement) con.conecta().prepareStatement(consulta);
            ps.setInt(1, x);
            rs = ps.executeQuery();//obteniendo datos
            if (rs.next()) {
                p.setId_item(rs.getInt("id_item"));
                p.setId_pedido(rs.getInt("id_pedido"));
                p.setId_producto(rs.getInt("id_producto"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setMonto(rs.getDouble("monto"));
            }
            con.desconecta(); //Desconectado      
        } catch (SQLException e) {
            System.out.println("Error al buscar" + e);
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

    public void eliminar(int x) {
        PreparedStatement ps = null;
        try {
            String sentencia = "DELETE FROM item WHERE item.id_item = ?";

            ps = (PreparedStatement) con.conecta().prepareStatement(sentencia);
            ps.setInt(1, x);
            ps.execute();
            con.desconecta();
            //JOptionPane.showMessageDialog(null, "Eliminado");
        } catch (SQLException e) {
            System.out.println("Error al eliminar" + e);
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

    public int cuentaproductos(int x) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        int num = 0;
        Item p = new Item();
        try {
            String consulta = "SELECT SUM(cantidad) FROM item WHERE id_pedido=?";

            ps = (PreparedStatement) con.conecta().prepareStatement(consulta);
            ps.setInt(1, x);
            rs = ps.executeQuery();//obteniendo datos
            if (rs.next()) {
                num = rs.getInt(1);
            }
            con.desconecta(); //Desconectado  

        } catch (SQLException e) {
            System.out.println("Error al buscar" + e);
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
        return num;

    }

    public double sumamonto(int x) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        double total = 0.0;
        Item p = new Item();
        try {
            String consulta = "SELECT SUM(monto) FROM item WHERE id_pedido=?";

            ps = (PreparedStatement) con.conecta().prepareStatement(consulta);
            ps.setInt(1, x);
            rs = ps.executeQuery();//obteniendo datos
            if (rs.next()) {
                total = rs.getInt(1);
            }
            con.desconecta(); //Desconectado  

        } catch (SQLException e) {
            System.out.println("Error al buscar" + e);
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
        return total;

    }

}
