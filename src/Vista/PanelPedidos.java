/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Modelo.CRUDCliente;
import Modelo.CRUDItems;
import Modelo.CRUDPedidos;
import Modelo.CRUDProductos;
import Controlador.Producto;
import Controlador.Item;
import Controlador.Cliente;
import Controlador.Pedido;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chris
 */
public class PanelPedidos extends javax.swing.JPanel {

    //var
    private Pedido pedido;
    private Item item;
    private Cliente cliente;
    private Producto produto;
    private boolean bandera = false;  //indica si se esta editando pedido
    private boolean estatus = false;  //indica si el pedido ya esta pagado
    private String msj1 = "Cantidad de Productos: ";//para mostrar txt cantidad
    private String msj2 = "Total: ";
    private String msj3 = "Fecha: ";
    private String msj4 = "Estado: ";

    CRUDProductos productos = new CRUDProductos();
    CRUDPedidos pedidos = new CRUDPedidos();
    CRUDItems items = new CRUDItems();
    CRUDCliente clientes = new CRUDCliente();
    SClientes panel = new SClientes(this);
    PanelItem p = new PanelItem();

    /**
     * Creates new form PanelPedidos
     */
    public PanelPedidos() {
        initComponents();
        cliente=new Cliente();
        cargarpedidos();
        estado();
    }

    //Carga el cliente seleccionado
    public void setCliente(Cliente x) {
        txtcliente.setText(x.toString());
        this.cliente = x;
    }

    public void cargaproductos() {
        DefaultTableModel modelo = productos.lista(txtbuscaproducto.getText());
        //tamaño de las columnas
        //tamaño de las columnas
        int[] anchos ={10,30,100,200,10,30};
        for (int i = 0; i < tb.getColumnCount(); i++) {
            tbproductos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);   
        }
        tbproductos.setModel(modelo);
        tbproductos.setDefaultEditor(Object.class, null); //La tabla no es editable
    }

    public void cargaitems(int id) {
        DefaultTableModel modelo = items.lista(id);
        tb.setModel(modelo);
        tb.setDefaultEditor(Object.class, null); //La tabla no es editable
    }

    public void cargarpedidos() {
        DefaultTableModel modelo = pedidos.lista(txtbuscapedido.getText());
        tb.setModel(modelo);
        tb.setDefaultEditor(Object.class, null); //La tabla no es editable
    }

    public void estado() {
        //habilitando o deshabilitando los campos necesarios
        //bandera= false: No se esta editando un pedido
        //bandera= true: Se esta editando un pedido
        
        //Limpiando tablas
        tb.setEnabled(true);//deshabilitamos la tabla para evitar modificar
        tb.removeAll();
        tbproductos.removeAll();
        DefaultTableModel tabla = new DefaultTableModel();
        tb.setModel(tabla);
        tbproductos.setModel(tabla);


        txtbuscapedido.setEditable(!bandera); //inhabilita el campo pedido
        tbproductos.setVisible(bandera);//Mostramos la tabla de productos para agregar
        txtbuscaproducto.setVisible(bandera);//mostramos el campo para buscar un producto por id
        btnbuscapro.setVisible(bandera);//Mostramos el boton para buscar el producto
        btnelimina.setVisible(bandera);//mostramos el boton eliminar
        btnHecho.setVisible(bandera);
        lb1.setVisible(bandera);
        tbproductos.setVisible(bandera);
        btnbusca.setVisible(!bandera);//mostramos el boton de buscar

        if (bandera) { //Se esta Editando un pedido
           btnnuevo.setText("Volver");
            pedido=pedidos.busca(Integer.parseInt(txtbuscapedido.getText()));//volvemos a cargar el pedido
            lbcantidad.setText(msj1+pedido.getCantidadproductos());//Mostramos la cantidad de productos;
            lbtotal.setText(msj2+pedido.getTotal());//Mostramos el total
            lbfecha.setText(msj3+pedido.getFecha());//Mostramos la fecha
            lbestado.setText(msj4+pedido.getEstado());// Mostramos el estado
            
            if(pedido.getEstado().equals("Pagado")){//verificamos si ya esta pagado
                estatus=true;//el pedido ya esta pagado
                btnHecho.setVisible(!estatus);//ocultamos el pagar
                btnelimina.setVisible(!estatus);//ocultamos el eliminar
                //ocultamos los elementos de la tabla producto
                lb1.setVisible(!estatus);
                tbproductos.setVisible(!estatus);
                txtbuscaproducto.setVisible(!estatus);
                btnbuscapro.setVisible(!estatus);
            }
            
            cargaproductos();//Mostramos la lista de productos

            cargaitems(pedido.getId_pedido());//Cargamos lista de items del pedido

        } else {
            //NO se esta editando un pedido
            btnnuevo.setText("Nuevo Pedido");

            lbcantidad.setText(""); //no se muestra cantidad de productos
            lbfecha.setText("");//No se muestra la fecha
            lbtotal.setText("");//No se muestra el total
            txtbuscapedido.setText("");
            txtbuscapedido.setText("");

            cargarpedidos();//cargamos la lista de pedidos
        }
    }

    //al hacer click en el elemento de la tabla obtenemos el pedido seleccionado
    public void obtienepedido() {
        // Obtener el datocque se selecciona en la tabla
        int id_clien, id_pe,noproductos;
        double total;
        String estado;
        Timestamp fecha;
        try {
            int fila = tb.getSelectedRow(); //Obtenemos la fila 

            //Obteniendo el pedido
            id_pe = Integer.parseInt(tb.getValueAt(fila, 0).toString());
            id_clien=Integer.parseInt(tb.getValueAt(fila, 1).toString());
            noproductos=Integer.parseInt(tb.getValueAt(fila, 2).toString());
            total=Double.parseDouble(tb.getValueAt(fila, 3).toString());
            fecha=Timestamp.valueOf(tb.getValueAt(fila, 4).toString());
            estado=tb.getValueAt(fila, 5).toString();
            pedido = new Pedido(id_pe,id_clien,noproductos,total,fecha,estado);

            //Obteniendo el cliente por el id
            cliente = clientes.busca(id_clien);

            //Cargando los datos en el formulario
            txtbuscapedido.setText(String.valueOf(id_pe));
            txtcliente.setText(cliente.toString());

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error al seleccionar de tabla: " + e);
        }
    }

    public int obtieneproducto() {
        // Obtener el datocque se selecciona en la tabla
        int id_pro=0;
        try {
            int fila = tbproductos.getSelectedRow(); //Obtenemos la fila 
            //Obteneiendo el id del producto
            id_pro = Integer.parseInt(tbproductos.getValueAt(fila, 0).toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error al seleccionar de tabla: " + e);
        }
        return id_pro;
    }

    public void obtieneitem() {
        // Obtener el datocque se selecciona en la tabla
        
        try {
            int id,idcon,idped,cantidad;
            double monto;
            int fila = tb.getSelectedRow(); //Obtenemos la fila 

            //Obteniendo el producto por el id
            id = Integer.parseInt(tb.getValueAt(fila, 0).toString());
            idcon=Integer.parseInt(tb.getValueAt(fila, 1).toString());
            cantidad=Integer.parseInt(tb.getValueAt(fila, 3).toString());
            monto=Double.parseDouble(tb.getValueAt(fila, 4).toString());
            idped=Integer.parseInt(txtbuscapedido.getText());
            
            item=new Item(id,idped,idcon,cantidad,monto);//objeto a utilizar

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error al seleccionar de tabla: " + e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb = new javax.swing.JTable();
        btnnuevo = new javax.swing.JButton();
        txtbuscapedido = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtcliente = new javax.swing.JTextField();
        lb1 = new javax.swing.JLabel();
        txtbuscaproducto = new javax.swing.JTextField();
        btnbuscapro = new javax.swing.JButton();
        btnbusca = new javax.swing.JButton();
        btnelimina = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbproductos = new javax.swing.JTable();
        lbfecha = new javax.swing.JLabel();
        lbcantidad = new javax.swing.JLabel();
        lbtotal = new javax.swing.JLabel();
        btnHecho = new javax.swing.JButton();
        lbestado = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(970, 450));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel1.setText("Pedido");

        tb.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb);

        btnnuevo.setBackground(new java.awt.Color(0, 153, 153));
        btnnuevo.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnnuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnnuevo.setText("Nuevo Pedido");
        btnnuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnnuevoMouseClicked(evt);
            }
        });

        txtbuscapedido.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel2.setText("Cliente");

        txtcliente.setEditable(false);
        txtcliente.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtcliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtclienteMouseClicked(evt);
            }
        });

        lb1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        lb1.setText("Producto");

        txtbuscaproducto.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        btnbuscapro.setBackground(new java.awt.Color(153, 153, 153));
        btnbuscapro.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnbuscapro.setForeground(new java.awt.Color(255, 255, 255));
        btnbuscapro.setText("Buscar");
        btnbuscapro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnbuscaproMouseClicked(evt);
            }
        });

        btnbusca.setBackground(new java.awt.Color(51, 153, 255));
        btnbusca.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnbusca.setForeground(new java.awt.Color(255, 255, 255));
        btnbusca.setText("Buscar");
        btnbusca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnbuscaMouseClicked(evt);
            }
        });

        btnelimina.setBackground(new java.awt.Color(255, 102, 102));
        btnelimina.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnelimina.setForeground(new java.awt.Color(255, 255, 255));
        btnelimina.setText("Eliminar Pedido");
        btnelimina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btneliminaMouseClicked(evt);
            }
        });

        tbproductos.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tbproductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbproductosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbproductos);

        lbfecha.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        lbfecha.setText("Fecha: ");

        lbcantidad.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        lbcantidad.setText("Cantidad de productos: 10");

        lbtotal.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        lbtotal.setText("Total: 100.00");

        btnHecho.setBackground(new java.awt.Color(51, 153, 255));
        btnHecho.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnHecho.setForeground(new java.awt.Color(255, 255, 255));
        btnHecho.setText("Hecho");
        btnHecho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHechoMouseClicked(evt);
            }
        });

        lbestado.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        lbestado.setText("Estatus:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtbuscapedido, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnbusca))
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtcliente)
                                .addGap(205, 205, 205)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbestado, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtbuscaproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnnuevo))
                                .addGap(31, 31, 31)
                                .addComponent(btnbuscapro))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnHecho)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnelimina))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(lb1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbfecha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbestado)
                                .addGap(34, 34, 34)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbuscaproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnbuscapro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbuscapedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnbusca)
                            .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnnuevo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnHecho)
                        .addComponent(btnelimina))
                    .addComponent(lbcantidad, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbtotal)
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnnuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnuevoMouseClicked

        if (bandera) { //bandera indica que se esta editando un pedido
            //Terminamos la edicion del pedido
            bandera = false;
            estatus=false;
            
            estado();
        } else {//bandera indica que no estamos editando ningun pedido
            // Se agrega un pedido

            //Se presiona el boton Agregar nuevo pedido
            if (txtcliente.getText().isEmpty()) {//Verificando que se selecciono un cliente
                JOptionPane.showMessageDialog(null, "Selecciona un cliente");
                // Se va a seleccionar un cliente
                SClientes pcliente = new SClientes(this);
                pcliente.setVisible(true);
            } else {
                pedido = new Pedido();//nueva instancia de producto
                pedido.setId_cliente(cliente.getId_cliente());//se agrega el id cliente
                pedido.setEstado("Pendiente"); // se agrega el estado
                int id = pedidos.insertar(pedido);//insertando el nuevo pedido y obtenemos el id que genero sql
                //pedido.setId_pedido(id);//guardamos

                txtbuscapedido.setText(String.valueOf(id));//mostrando id en el campo

                //Inicia la edicion del pedido
                bandera = true; //habilito la edicion
                estado();
            }
        }
    }//GEN-LAST:event_btnnuevoMouseClicked

    private void btneliminaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneliminaMouseClicked
        // Presiona eliminar pedido

        if (JOptionPane.showConfirmDialog(this, "¡Se eliminará el pedido!\n¿Desea continuar?",
                "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (txtbuscapedido.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay un pedido seleccionado");
            } else {
                pedidos.eliminar(Integer.parseInt(txtbuscapedido.getText()));
            }

            bandera = false; //cambiamos la bandera (false: no se esta editando)
            estado();
        }


    }//GEN-LAST:event_btneliminaMouseClicked

    private void txtclienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtclienteMouseClicked
        // Se va a seleccionar un cliente
        if (!estatus) {//si no esta pagado el pedido
            if(!panel.isVisible()||!panel.isEnabled()||!panel.isShowing()){
           panel.setVisible(true);
           }

        }

    }//GEN-LAST:event_txtclienteMouseClicked

    private void tbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMouseClicked
        // se selecciona un item de la tabla

        if (evt.getClickCount() == 2) {
            //se da doble click ()
            if (bandera) {//items 
                if (!estatus) {//el pedido esta pendiente (se puede editar)
                    //aqui cargamos(para actualizar o eliminar)
                    obtieneitem();
                    //llamamos a la ventana item
                    if (!panel.isVisible() || !panel.isEnabled() || !panel.isShowing()) {
                        p.cargaitem(item);//le enviamos el item a editar
                        p.cargaPanel(this);//le enviamos el panel para volver a esta ventana
                        p.cargabandera(true);//le enviamos el estado
                        p.setVisible(true);
                        p.estado();
                    }

                }

            } else {//Esta la lista de pedidos
                //cargamos el pedido
                obtienepedido();
                //comenzamos la edicion del pedido
                bandera = true;
            }
            estado();
        }


    }//GEN-LAST:event_tbMouseClicked

    private void btnHechoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHechoMouseClicked
        // Presiona Pagar
        if (JOptionPane.showConfirmDialog(this, "Una vez pagado no se puede modificar\n¿Desea continuar?",
                "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            pedido.setEstado("Pagado");//cambiamos el estado
            pedidos.actualiza(pedido);//actualizamos en la bd
            estado();
        }  
    }//GEN-LAST:event_btnHechoMouseClicked

    private void tbproductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbproductosMouseClicked
        if (evt.getClickCount() == 2) { //doble click
            //llamamos a la ventana item para crear uno nuevo
            if (!panel.isVisible() || !panel.isEnabled() || !panel.isShowing()) {
                p.setVisible(true);
                p.cargaPedido(pedido.getId_pedido());
                p.cargaproducto(obtieneproducto());//le enviamos el id del producto
                //llamamos a la ventana item
                p.cargaPanel(this);//le enviamos el panel para volver a esta ventana
                p.cargabandera(false);//le enviamos el estado
                p.estado();
            }
        }
    }//GEN-LAST:event_tbproductosMouseClicked

    private void btnbuscaproMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbuscaproMouseClicked
        // Busca
        cargaproductos();
    }//GEN-LAST:event_btnbuscaproMouseClicked

    private void btnbuscaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbuscaMouseClicked
        cargarpedidos();
    }//GEN-LAST:event_btnbuscaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHecho;
    private javax.swing.JButton btnbusca;
    private javax.swing.JButton btnbuscapro;
    private javax.swing.JButton btnelimina;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lbcantidad;
    private javax.swing.JLabel lbestado;
    private javax.swing.JLabel lbfecha;
    private javax.swing.JLabel lbtotal;
    private javax.swing.JTable tb;
    private javax.swing.JTable tbproductos;
    private javax.swing.JTextField txtbuscapedido;
    private javax.swing.JTextField txtbuscaproducto;
    private javax.swing.JTextField txtcliente;
    // End of variables declaration//GEN-END:variables
}
