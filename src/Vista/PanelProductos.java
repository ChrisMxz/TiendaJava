/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;


import Modelo.CRUDConcepto;
import Modelo.CRUDProductos;
import Controlador. *;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chris
 */
public class PanelProductos extends javax.swing.JPanel{
    
    //Establece el concepto dentro del txt y guarda el objeto utilizado
    public void setConcepto(Concepto x) {
        this.concepto=x;    //guargando el concepto
        txtconcepto.setText(this.concepto.toString()); //mostrando en el campo 
        
    }
    private Concepto concepto;
    private Producto producto;
    private boolean edicion=false;
    private CRUDProductos productos;
    private CRUDConcepto conceptos;
    private PanelConcepto panel;
    
        public PanelProductos() {    
        initComponents();
        
        producto=new Producto();
        productos = new CRUDProductos();
        conceptos=new CRUDConcepto();
        panel= new PanelConcepto(this,true);
        estado();
    }
        
    private void estado(){
        cargaTabla();
        btnelimina.setVisible(edicion); //mostrar boton eliminar 
        txtid.setEditable(!edicion);
        btnbusca.setVisible(!edicion);
        txtbusca.setVisible(!edicion);
        txtid.setVisible(edicion);
        lbid.setVisible(edicion);
        
        if(!edicion){//No se esta editando
            btnaceptar.setText("Agregar");
        }else{//se esta editando
            btnaceptar.setText("Guardar");
        }   
    }

    //Metodo para verificar campos vacios
    private boolean FormularioOk() {
        boolean bandera = true;

        if (txtnombreproducto.getText().isEmpty()) {
            bandera = false;
        }
        if (txtconcepto.getText().isEmpty()) {
            bandera = false;
        }
        if (txtdescripproducto.getText().isEmpty()) {
            bandera = false;
        }
        if (txtstock.getText().isEmpty()) {
            bandera = false;
        }
        if (txtprecioproducto.getText().isEmpty()) {
            bandera = false;
        }
        return bandera;
    }
    
    private void cargarFormulario(){
        //variables a utilizar
        int stock;
        double precio;
        String nombre, descripcion;

        //obteneiendo datos del formulario
        
        nombre = txtnombreproducto.getText();
        descripcion = txtdescripproducto.getText();
        stock = Integer.parseInt(txtstock.getText());
        precio = Double.parseDouble(txtprecioproducto.getText());

        //Creando objeto de tipo producto que esta interna a clase main
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setStock(stock);
        producto.setPrecio(precio);
        producto.setId_concepto(concepto.getId_concepto());
    }
    
    private void Limpiar(){
        // Limpiando los campos de texto
        txtid.setText("");
        txtnombreproducto.setText("");
        txtdescripproducto.setText("");
        txtprecioproducto.setText("");
        txtstock.setText("");
        txtconcepto.setText(""); 
    }
    
    private void cargaTabla() {
        DefaultTableModel modelo = productos.lista(txtbusca.getText());
        //tamaño de las columnas
        //tamaño de las columnas
        int[] anchos ={10,30,100,200,10,30};
        for (int i = 0; i < tb.getColumnCount(); i++) {
            tb.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);   
        }
        tb.setModel(modelo);
        tb.setDefaultEditor(Object.class, null); //La tabla no es editable
    }
    
    private void cargaProducto(){
        try{
            int id_con, id_pro, sto;
            double precio;
            String  nombre, des;
            int fila=tb.getSelectedRow(); //Obtenemos la fila 
            
            //Obteneiendo el producto por el id
            id_pro=Integer.parseInt(tb.getValueAt(fila,0).toString());
            id_con=Integer.parseInt(tb.getValueAt(fila,1).toString());
            nombre=tb.getValueAt(fila,2).toString();
            des=tb.getValueAt(fila,3).toString();
            sto=Integer.parseInt(tb.getValueAt(fila,4).toString());
            precio=Double.parseDouble(tb.getValueAt(fila,5).toString());
            
            //Obteniendo el concepto por el id 
            concepto=conceptos.Busca(id_con);
            
            //cargando los datos en el objeto para su uso 
            producto.setId_producto(id_pro);
            producto.setId_concepto(id_con);
            producto.setNombre(nombre);
            producto.setDescripcion(des);
            producto.setStock(sto);
            producto.setPrecio(precio);            
            
            //Cargando los datos en el formulario
            txtid.setText(String.valueOf(id_pro));
            txtconcepto.setText(concepto.toString());
            txtnombreproducto.setText(nombre);
            txtdescripproducto.setText(des);
            txtstock.setText(String.valueOf(sto));
            txtprecioproducto.setText(String.valueOf(precio));
            
            //se seleciona un objeto y habilitamos su edicion
            edicion=true;
            estado();
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Error al seleccionar de tabla: "+e);
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

        scrollproductos = new javax.swing.JScrollPane();
        tb = new javax.swing.JTable();
        btnbusca = new javax.swing.JButton();
        btnaceptar = new javax.swing.JButton();
        txtprecioproducto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtstock = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtnombreproducto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        lbid = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnelimina = new javax.swing.JButton();
        btncancela = new javax.swing.JButton();
        txtconcepto = new javax.swing.JTextField();
        txtbusca = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdescripproducto = new javax.swing.JTextArea();

        setPreferredSize(new java.awt.Dimension(970, 450));

        tb.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "ID Concepto", "Nombre", "Descripcion", "Stock", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMouseClicked(evt);
            }
        });
        scrollproductos.setViewportView(tb);

        btnbusca.setBackground(new java.awt.Color(51, 153, 255));
        btnbusca.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnbusca.setForeground(new java.awt.Color(255, 255, 255));
        btnbusca.setText("Buscar");
        btnbusca.setToolTipText("Busca un producto por:\n- ID\n- Nombre\n- Concepto\n");
        btnbusca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnbuscaMouseClicked(evt);
            }
        });

        btnaceptar.setBackground(new java.awt.Color(0, 153, 153));
        btnaceptar.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnaceptar.setForeground(new java.awt.Color(255, 255, 255));
        btnaceptar.setText("Agregar");
        btnaceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnaceptarMouseClicked(evt);
            }
        });

        txtprecioproducto.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtprecioproducto.setToolTipText("precio del producto\n");
        txtprecioproducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioproductoKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel5.setText("Precio");

        txtstock.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtstock.setToolTipText("Cantidad de Producto");
        txtstock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtstockKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel7.setText("Stock");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel6.setText("Descripcion");

        txtnombreproducto.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtnombreproducto.setToolTipText("Nombre del producto");
        txtnombreproducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreproductoKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel2.setText("Nombre");

        txtid.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtid.setToolTipText("id (solo numeros)");
        txtid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtidKeyTyped(evt);
            }
        });

        lbid.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lbid.setText("ID Producto");

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel8.setText("Concepto");

        btnelimina.setBackground(new java.awt.Color(255, 102, 102));
        btnelimina.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnelimina.setForeground(new java.awt.Color(255, 255, 255));
        btnelimina.setText("Eliminar");
        btnelimina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btneliminaMouseClicked(evt);
            }
        });

        btncancela.setBackground(new java.awt.Color(204, 204, 204));
        btncancela.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btncancela.setForeground(new java.awt.Color(102, 102, 102));
        btncancela.setText("Cancelar");
        btncancela.setToolTipText("Limpia los campos del formulario\n");
        btncancela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btncancelaMouseClicked(evt);
            }
        });

        txtconcepto.setEditable(false);
        txtconcepto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtconceptoMouseClicked(evt);
            }
        });

        txtbusca.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtbusca.setToolTipText("ID , Nombre");
        txtbusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscaKeyTyped(evt);
            }
        });

        txtdescripproducto.setColumns(20);
        txtdescripproducto.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtdescripproducto.setLineWrap(true);
        txtdescripproducto.setRows(3);
        txtdescripproducto.setTabSize(0);
        txtdescripproducto.setWrapStyleWord(true);
        txtdescripproducto.setMaximumSize(new java.awt.Dimension(50, 50));
        txtdescripproducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdescripproductoKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtdescripproducto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnombreproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtconcepto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtprecioproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnelimina))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btncancela)
                                        .addComponent(btnaceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtbusca, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnbusca)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(scrollproductos, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbid)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbid)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbusca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtnombreproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtconcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtprecioproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnaceptar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btncancela)
                            .addComponent(btnelimina))
                        .addGap(166, 166, 166))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollproductos, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnaceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnaceptarMouseClicked
        // Se presiona boton aceptar
        if (FormularioOk()) { //verificamos que no falte ningun dato
            if (!edicion) {//agregando 
                int id;
                cargarFormulario(); //cargamos los datos escritos en el formulario
                id = productos.insertar(producto); //llamamos al metodo para ingresar nuevo producto
                producto.setId_producto(id);
                Limpiar();
            } else {//actualizando
                cargarFormulario(); //cargando los nuevos datos
                productos.actualizar(producto);//envio el objeto producto a crud para actualizarlo
                JOptionPane.showMessageDialog(null, "Actualizado");
                cargaTabla();// Actualizo la tabla de productos
                edicion = false;
                Limpiar();
            }
            estado();
        } else {
            JOptionPane.showMessageDialog(null, "Faltan campos por llenar");
        }

    }//GEN-LAST:event_btnaceptarMouseClicked

    private void txtidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidKeyTyped
        // Verificando que solo sean numeros
        int key = evt.getKeyChar();
        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }
        //Estableciendo longitud max
        if (txtid.getText().length() == 5) {
            evt.consume();
        }
    }//GEN-LAST:event_txtidKeyTyped

    private void txtnombreproductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreproductoKeyTyped
        //Estableciendo longitud max
        if (txtnombreproducto.getText().length() == 50) {
            evt.consume();
        }
    }//GEN-LAST:event_txtnombreproductoKeyTyped

    private void txtstockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstockKeyTyped
// Verificando que solo sean numeros
        int key = evt.getKeyChar();
        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }
//Estableciendo longitud max
        if (txtstock.getText().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtstockKeyTyped

    private void txtprecioproductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioproductoKeyTyped
        // Verificando que solo sean numeros y el punto decimal
        int key = evt.getKeyChar();
        boolean numeros = key >= 48 && key <= 57 || key == 46;

        if (!numeros) {
            evt.consume();
        }
        //Estableciendo longitud max
        if (txtprecioproducto.getText().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtprecioproductoKeyTyped

    private void btncancelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncancelaMouseClicked
        // Limpia el formulario
        edicion = false;
        estado();
        Limpiar();

    }//GEN-LAST:event_btncancelaMouseClicked

    private void tbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMouseClicked
        // Obtener el datocque se selecciona en la tabla
         if (evt.getClickCount() == 2) { //doble click
            cargaProducto();
        }
        
    }//GEN-LAST:event_tbMouseClicked

    private void btneliminaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneliminaMouseClicked
        //Se presiona boton para eliminar
        //Verificacmos que los campos esten llenos
        if (FormularioOk()) {
            if (JOptionPane.showConfirmDialog(this, "Se eliminará ¿Desea continuar?",
                    "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                productos.eliminar(producto.getId_producto());//envio el objeto producto a crud para insertarlo
                edicion = false;
                estado();
                Limpiar();
            }

        } else {
            JOptionPane.showMessageDialog(this, "Faltan campos por llenar");
        }
    }//GEN-LAST:event_btneliminaMouseClicked

    private void txtconceptoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtconceptoMouseClicked
        // Seleccionar concepto
           if(!panel.isVisible()||!panel.isEnabled()||!panel.isShowing()){
           panel.setVisible(true);
           }     
    }//GEN-LAST:event_txtconceptoMouseClicked

    private void btnbuscaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbuscaMouseClicked
        // Se busca
        cargaTabla();
    }//GEN-LAST:event_btnbuscaMouseClicked

    private void txtbuscaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscaKeyTyped

    private void txtdescripproductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescripproductoKeyTyped
        //Estableciendo longitud max
        if (txtdescripproducto.getText().length() == 100) {
            evt.consume();
        }
    }//GEN-LAST:event_txtdescripproductoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaceptar;
    private javax.swing.JButton btnbusca;
    private javax.swing.JButton btncancela;
    private javax.swing.JButton btnelimina;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbid;
    private javax.swing.JScrollPane scrollproductos;
    private javax.swing.JTable tb;
    private javax.swing.JTextField txtbusca;
    private javax.swing.JTextField txtconcepto;
    private javax.swing.JTextArea txtdescripproducto;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnombreproducto;
    private javax.swing.JTextField txtprecioproducto;
    private javax.swing.JTextField txtstock;
    // End of variables declaration//GEN-END:variables
}
