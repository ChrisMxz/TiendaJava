/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Modelo.CRUDCliente;
import Controlador.Cliente;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Chris
 */
public final class PanelClientes extends javax.swing.JPanel {

    /**
     * Creates new form PanelClientes
     */
    
    private Cliente cliente;
    private boolean edicion=false;
    private CRUDCliente clientes;
    
    public PanelClientes() {
        initComponents();
        cliente =new Cliente();
        clientes=new CRUDCliente();
        estado();
    }
    
    private void Limpiar() {
        //limpiando los datos del formulario
        txtid.setText("");
        txtnombre.setText("");
        txtapp.setText("");
        txtapm.setText("");
        txtedad.setText("");
        txtdir.setText("");
        txttel.setText("");
        cbsexo.setSelectedIndex(-1);
        txtbusca.setText("");
    }
    
    private void estado(){
        cargaTabla();
        bteliminar.setVisible(edicion); //mostrar boton eliminar 
        txtid.setEditable(!edicion);
        btnbuscar.setVisible(!edicion);
        txtbusca.setVisible(!edicion);
        lbid.setVisible(edicion);
        txtid.setVisible(edicion);
        
        if(!edicion){//No se esta editando
            aceptar.setText("Agregar");
        }else{//se esta editando
            aceptar.setText("Guardar");
        }
        
    }
    
    private void cargaTabla(){
       DefaultTableModel modelo=clientes.lista(txtbusca.getText());
       //tamaño de las columnas
        int[] anchos ={50,100,100,100,40,40,145,100};
        for (int i = 0; i < tb.getColumnCount(); i++) {
            tb.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);   
        }
       tb.setModel(modelo);
       tb.setDefaultEditor(Object.class, null); //La tabla no es editable
       
    }
    
    private boolean FormularioOk() {
        boolean bandera = true;

        if (txtnombre.getText().isEmpty()) {
            bandera = false;
        }
        if (txtapp.getText().isEmpty()) {
            bandera = false;
        }
        if (txtapm.getText().isEmpty()) {
            bandera = false;
        }
        if (txtedad.getText().isEmpty()) {
            bandera = false;
        }
        if (txtdir.getText().isEmpty()) {
            bandera = false;
        }
        if (txttel.getText().isEmpty()) {
            bandera = false;
        }
        if (cbsexo.getSelectedIndex()==-1) {
            bandera = false;
        }
        return bandera;
    }
    
    private void cargarFormulario(){
        //variables a utilizar
        int edad;
        String nombre, app, apm, sex, dir, tel;

        //obteneiendo datos del formulario
        nombre = txtnombre.getText();
        app = txtapp.getText();
        apm = txtapm.getText();
        edad = Integer.parseInt(txtedad.getText());
        dir = txtdir.getText();
        tel = txttel.getText();

        sex = cbsexo.getSelectedItem().toString();
        if (sex.equals("Masculino")) {
            sex = "M";
        } else {
            sex = "F";
        }

        //Creando objeto
        cliente.setNombre(nombre);
        cliente.setAppaterno(app);
        cliente.setApmaterno(apm);
        cliente.setEdad(edad);
        cliente.setDireccion(dir);
        cliente.setTelefono(tel);
        cliente.setSexo(sex);

   
    }
    
    private void cargarCliente() {
        try {
            int id_clien, edad;
            String nombre, app, apm, dir, tel, sex;
            int fila = tb.getSelectedRow(); //Obtenemos la fila 

            //Obteniendo los datos del cliente
            id_clien = Integer.parseInt(tb.getValueAt(fila, 0).toString());
            nombre = tb.getValueAt(fila, 1).toString();
            app = tb.getValueAt(fila, 2).toString();
            apm = tb.getValueAt(fila, 3).toString();
            edad = Integer.parseInt(tb.getValueAt(fila, 4).toString());
            sex = tb.getValueAt(fila, 5).toString();
            dir = tb.getValueAt(fila, 6).toString();
            tel = tb.getValueAt(fila, 7).toString();

            //cargamos el objeto para usarlo despues
            cliente = new Cliente(id_clien, nombre, app, apm, edad, sex, dir, tel);

            //Cargando los datos en el formulario
            txtid.setText(String.valueOf(id_clien));
            txtnombre.setText(nombre);
            txtapp.setText(app);
            txtapm.setText(apm);
            txtedad.setText(String.valueOf(edad));
            txtdir.setText(dir);
            txttel.setText(tel);
            if (sex == "M") {
                cbsexo.setSelectedIndex(0);//selecciona masculino
            } else {
                cbsexo.setSelectedIndex(1);//selecciona masculino
            }
            //se seleciona un objeto y habilitamos su edicion
            edicion = true;
            estado();

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

        jPanel1 = new javax.swing.JPanel();
        lbid = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbsexo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        aceptar = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tb = new javax.swing.JTable();
        btnbuscar = new javax.swing.JButton();
        bteliminar = new javax.swing.JButton();
        txtid = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtapp = new javax.swing.JTextField();
        txtapm = new javax.swing.JTextField();
        txttel = new javax.swing.JTextField();
        txtedad = new javax.swing.JTextField();
        btncancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdir = new javax.swing.JTextArea();
        txtbusca = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(970, 450));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(970, 450));

        lbid.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lbid.setText("ID Cliente");

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel3.setText("Apellido Paterno");

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel4.setText("Apellido Materno");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel5.setText("Edad");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel6.setText("Sexo");

        cbsexo.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbsexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino" }));
        cbsexo.setSelectedIndex(-1);

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel7.setText("Direccion");

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel8.setText("Telefono");

        aceptar.setBackground(new java.awt.Color(0, 153, 153));
        aceptar.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        aceptar.setForeground(new java.awt.Color(255, 255, 255));
        aceptar.setText("Agregar");
        aceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aceptarMouseClicked(evt);
            }
        });

        tb.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tb);

        btnbuscar.setBackground(new java.awt.Color(51, 153, 255));
        btnbuscar.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnbuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnbuscar.setText("Buscar");
        btnbuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnbuscarMouseClicked(evt);
            }
        });

        bteliminar.setBackground(new java.awt.Color(255, 102, 102));
        bteliminar.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        bteliminar.setForeground(new java.awt.Color(255, 255, 255));
        bteliminar.setText("Eliminar");
        bteliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bteliminarMouseClicked(evt);
            }
        });

        txtid.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtidKeyTyped(evt);
            }
        });

        txtnombre.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });

        txtapp.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtapp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtappKeyTyped(evt);
            }
        });

        txtapm.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtapm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapmKeyTyped(evt);
            }
        });

        txttel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txttel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelKeyTyped(evt);
            }
        });

        txtedad.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtedad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtedadKeyTyped(evt);
            }
        });

        btncancelar.setBackground(new java.awt.Color(153, 153, 153));
        btncancelar.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btncancelar.setForeground(new java.awt.Color(255, 255, 255));
        btncancelar.setText("Cancelar");
        btncancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btncancelarMouseClicked(evt);
            }
        });

        txtdir.setColumns(20);
        txtdir.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtdir.setLineWrap(true);
        txtdir.setRows(3);
        txtdir.setTabSize(1);
        txtdir.setWrapStyleWord(true);
        txtdir.setMaximumSize(new java.awt.Dimension(50, 50));
        txtdir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdirKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtdir);

        txtbusca.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtbusca.setToolTipText("ID, Nombre, Apellido Paterno");
        txtbusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8)
                        .addComponent(jLabel7)
                        .addComponent(jLabel2)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(txtapp, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(txtapm, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(lbid)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txtedad, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(cbsexo, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(bteliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(69, 69, 69)
                            .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(65, 65, 65)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtbusca, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnbuscar)))
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbid)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtapp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtapm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbsexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aceptar))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btncancelar)
                            .addComponent(bteliminar))
                        .addContainerGap(49, Short.MAX_VALUE))))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 455));
    }// </editor-fold>//GEN-END:initComponents

    private void aceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aceptarMouseClicked
        // Se presiona boton aceptar
        if (FormularioOk()) { //verificamos que no falte ningun dato
            cargarFormulario(); //cargamos los datos escritos en el formulario
            if (!edicion) {//agregando 
                int id;
                id = clientes.insertar(cliente); //llamamos al metodo para ingresar nuevo 
                cliente.setId_cliente(id);
            } else {//actualizando
                clientes.actualizar(cliente);//envio el objeto producto a crud para actualizarlo
                edicion = false;  
            }
            Limpiar();
            estado();
        }else{JOptionPane.showMessageDialog(this, "Faltan campos por llenar");}
    }//GEN-LAST:event_aceptarMouseClicked

    private void btncancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncancelarMouseClicked
        // Limpia el formulario
        edicion=false;
        Limpiar();   
        estado();
        
    }//GEN-LAST:event_btncancelarMouseClicked

    private void tbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMouseClicked
        // Obtener el datocque se selecciona en la tabla
        if (evt.getClickCount() == 2) { //doble click
            cargarCliente();
        }
        

    }//GEN-LAST:event_tbMouseClicked

    private void bteliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bteliminarMouseClicked
        //Se presiona boton para eliminar
            if (JOptionPane.showConfirmDialog(this, "Se eliminará ¿Desea continuar?",
                    "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                clientes.eliminar(cliente.getId_cliente());//envio el objeto producto a crud para insertarlo
                edicion = false;
                estado();
                Limpiar();
            } 
    }//GEN-LAST:event_bteliminarMouseClicked

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

    private void txttelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelKeyTyped
        // Verificando que solo sean numeros
        int key = evt.getKeyChar();
        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }
        //Estableciendo longitud max
        if (txttel.getText().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txttelKeyTyped

    private void txtedadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtedadKeyTyped
        // Verificando que solo sean numeros
        int key = evt.getKeyChar();
        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }
        //Estableciendo longitud max
        if (txtedad.getText().length() == 3) {
            evt.consume();
        }
    }//GEN-LAST:event_txtedadKeyTyped

    private void txtnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyTyped
         //Estableciendo longitud max
        if (txtnombre.getText().length() == 20) {
            evt.consume();
        }
    }//GEN-LAST:event_txtnombreKeyTyped

    private void txtappKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtappKeyTyped
         //Estableciendo longitud max
        if (txtapp.getText().length() == 15) {
            evt.consume();
        }
    }//GEN-LAST:event_txtappKeyTyped

    private void txtapmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapmKeyTyped
         //Estableciendo longitud max
        if (txtapm.getText().length() == 15) {
            evt.consume();
        }
    }//GEN-LAST:event_txtapmKeyTyped

    private void txtdirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdirKeyTyped
        //Estableciendo longitud max
        if (txtdir.getText().length() == 100) {
            evt.consume();
        }
    }//GEN-LAST:event_txtdirKeyTyped

    private void btnbuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbuscarMouseClicked
        // Se busca  
       cargaTabla();
    }//GEN-LAST:event_btnbuscarMouseClicked

    private void txtbuscaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscaKeyTyped
        if (txtdir.getText().length() == 30) {
            evt.consume();
        }
    }//GEN-LAST:event_txtbuscaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JButton bteliminar;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JComboBox<String> cbsexo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lbid;
    private javax.swing.JTable tb;
    private javax.swing.JTextField txtapm;
    private javax.swing.JTextField txtapp;
    private javax.swing.JTextField txtbusca;
    private javax.swing.JTextArea txtdir;
    private javax.swing.JTextField txtedad;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txttel;
    // End of variables declaration//GEN-END:variables
}
