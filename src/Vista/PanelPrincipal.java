/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Chris
 */
public class PanelPrincipal extends javax.swing.JFrame {

    private PanelProductos pproducto = new PanelProductos();
    private PanelClientes pcliente = new PanelClientes();
    private PanelPedidos pcpedido = new PanelPedidos();
    
    
    public PanelPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        MuestraPanel(pproducto);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ContenedorPanel = new javax.swing.JPanel();
        BarraMenu = new javax.swing.JMenuBar();
        MenuProductos = new javax.swing.JMenu();
        MenuClientes = new javax.swing.JMenu();
        MenuPedidos = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tienda");
        setResizable(false);

        ContenedorPanel.setLayout(new java.awt.BorderLayout());

        BarraMenu.setFocusCycleRoot(true);

        MenuProductos.setText("Productos");
        MenuProductos.setMinimumSize(new java.awt.Dimension(80, 23));
        MenuProductos.setPreferredSize(new java.awt.Dimension(80, 23));
        MenuProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuProductosMouseClicked(evt);
            }
        });
        BarraMenu.add(MenuProductos);

        MenuClientes.setText("Clientes");
        MenuClientes.setMinimumSize(new java.awt.Dimension(80, 23));
        MenuClientes.setPreferredSize(new java.awt.Dimension(80, 23));
        MenuClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuClientesMouseClicked(evt);
            }
        });
        BarraMenu.add(MenuClientes);

        MenuPedidos.setText("Pedidos");
        MenuPedidos.setMinimumSize(new java.awt.Dimension(80, 23));
        MenuPedidos.setPreferredSize(new java.awt.Dimension(80, 23));
        MenuPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuPedidosMouseClicked(evt);
            }
        });
        BarraMenu.add(MenuPedidos);

        setJMenuBar(BarraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContenedorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 970, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ContenedorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuProductosMouseClicked

        MuestraPanel(pproducto);

    }//GEN-LAST:event_MenuProductosMouseClicked

    private void MenuClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuClientesMouseClicked
        MuestraPanel(pcliente);
    }//GEN-LAST:event_MenuClientesMouseClicked

    private void MenuPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuPedidosMouseClicked
        MuestraPanel(pcpedido);
    }//GEN-LAST:event_MenuPedidosMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelPrincipal().setVisible(true);
                
            }
        });
        
    }
    
    public final void MuestraPanel(JPanel p){
        p.setSize(970,450);//Tamaño de panel
        p.setLocation(0, 0);//Pocision centrada
        
        ContenedorPanel.removeAll();
        ContenedorPanel.add(p,BorderLayout.CENTER);
        ContenedorPanel.revalidate();
        ContenedorPanel.repaint();
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar BarraMenu;
    private javax.swing.JPanel ContenedorPanel;
    private javax.swing.JMenu MenuClientes;
    private javax.swing.JMenu MenuPedidos;
    private javax.swing.JMenu MenuProductos;
    // End of variables declaration//GEN-END:variables
}
