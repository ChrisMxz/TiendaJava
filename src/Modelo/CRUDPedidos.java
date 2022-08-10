/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.Item;
import Controlador.Pedido;
import Controlador.Producto;
import Modelo.CRUDItems;
import Modelo.CRUDCliente;
import Modelo.CRUDProductos;
import Modelo.conexion;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chris
 */
public class CRUDPedidos {

    //Objeto para conexion
    conexion con = new conexion();

    public DefaultTableModel lista(String buscar) { //buscamos y devolvemos un modelo de tabla con los datos
        ResultSet rs = null;
        PreparedStatement ps = null;
        CRUDCliente clientes = new CRUDCliente();
        String consulta = "SELECT * FROM pedido WHERE id_pedido LIKE ? OR id_cliente LIKE ?";
        String[] columnas = {"ID", "Cliente", "No.Productos", "Total", "Fecha", "Estado"}; //titulo de las columnas
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);//modelo de tabla
        try {
            ps = (PreparedStatement) con.conecta().prepareStatement(consulta);
            ps.setString(1, buscar + "%");
            ps.setString(2, buscar + "%");

            rs = ps.executeQuery();//obteniendo datos

            while (rs.next()) {
                Object[] fila = {rs.getInt("id_pedido"), rs.getInt("id_cliente"), rs.getInt("cantidadproductos"), rs.getDouble("total"), rs.getTimestamp("fecha"), rs.getString("estado")};
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

    public int insertar(Pedido x) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        int id = 0;

        try {
            String sentencia = "INSERT INTO pedido (id_cliente, cantidadproductos, total, fecha, estado) VALUES (?, NULL, NULL, current_timestamp,?)";
            ps = (PreparedStatement) con.conecta().prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, x.getId_cliente());
            ps.setString(2, x.getEstado());
            ps.execute();
            rs = (ResultSet) ps.getGeneratedKeys();//Obteniendo el id generado
            if (rs.next()) {
                System.out.println("Id: " + rs.getInt(1));
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

    public void actualiza(Pedido x) {
        PreparedStatement ps = null;
        try {
            String sentencia = "UPDATE pedido SET id_cliente=?, cantidadproductos=?, total=?, estado=? WHERE pedido.id_pedido=?";
            ps = (PreparedStatement) con.conecta().prepareStatement(sentencia);
            ps.setInt(1, x.getId_cliente());
            ps.setInt(2, x.getCantidadproductos());
            ps.setDouble(3, x.getTotal());
            ps.setString(4, x.getEstado());
            ps.setInt(5, x.getId_pedido());
            ps.execute();
            con.desconecta();
            //JOptionPane.showMessageDialog(null, "Actualizado");
        } catch (SQLException e) {
            System.out.println("Error al insertar" + e);
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

    public Pedido busca(int x) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Pedido p = new Pedido();
        try {
            String consulta = "SELECT * FROM pedido WHERE id_pedido = ? ";

            ps = (PreparedStatement) con.conecta().prepareStatement(consulta);
            ps.setInt(1, x);
            rs = ps.executeQuery();//obteniendo datos
            if (rs.next()) {
                //creando objeto 
                Timestamp fecha = rs.getTimestamp("fecha");

                p.setId_pedido(rs.getInt("id_pedido"));
                p.setId_cliente(rs.getInt("id_cliente"));
                p.setCantidadproductos(rs.getInt("cantidadproductos"));
                p.setTotal(rs.getDouble("total"));
                p.setEstado(rs.getString("estado"));
                p.setFecha(fecha);
            }
            con.desconecta(); //Desconectado  

        } catch (SQLException e) {
            System.out.println("Error buscar" + e);
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
        Producto producto = new Producto();//objeto producto
        CRUDItems items = new CRUDItems();//clase CRUDitems
        CRUDProductos productos = new CRUDProductos();//clase CRUDproductos
        ArrayList lista;

        Pedido pedido = busca(x);//buscamos el pedido
        if (pedido.getEstado().equals("Pendiente")) {//si esta pendiente se puede eliminar

            try {

                lista = items.listar(x); //listando los items con el id del pedido

                //recorremos la lista
                try {
                    Iterator i = lista.iterator();
                    while (i.hasNext()) {
                        Item item = (Item) i.next();
                        producto = productos.busca(item.getId_producto()); //obtengo el producto
                        //stock=stock+numproductos
                        producto.setStock(producto.getStock() + item.getCantidad()); //recuperamos  el stock
                        //actualizando en bd  
                        productos.actualizar(producto);//actualizamos el producto
                        items.eliminar(item.getId_item());//eliminamos el item
                    }
                } catch (Exception e) {
                    System.out.println("Error" + e);
                }

                //ahora tenemos eliminamos el pedido
                String sentencia = "DELETE FROM pedido WHERE pedido.id_pedido = ?";

                ps = (PreparedStatement) con.conecta().prepareStatement(sentencia);
                ps.setInt(1, x);
                ps.execute();
                con.desconecta();
                JOptionPane.showMessageDialog(null, "Eliminado");
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

        } else {
            JOptionPane.showMessageDialog(null, "Este pedido ya esta hecho, por lo que no se puede eliminar");
        }

    }

}
