/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGráfica;



/**
 *
 * @author SynMcall
 */
public class Principal extends javax.swing.JFrame {
    private CadastrarCliente cadastrarCliente;
    private CadastrarFuncionario cadastrarFuncionario;
    private CadastrarCargo cadastrarCargo;
    private CadastrarArma cadastrarArma;
    private CadastrarCalibre cadastrarCalibre;
    private CadastrarFornecedor cadastrarFornecedor;
    private CadastrarVenda venda;
    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        telaDesk = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenu12 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenuItem2.setText("jMenuItem2");

        jMenu2.setText("File");
        jMenuBar3.add(jMenu2);

        jMenu5.setText("Edit");
        jMenuBar3.add(jMenu5);

        jMenuItem5.setText("jMenuItem5");

        jMenuItem4.setText("jMenuItem4");

        jMenuItem8.setText("jMenuItem8");

        jMenu6.setText("jMenu6");

        jMenuItem9.setText("jMenuItem9");

        jMenuItem10.setText("jMenuItem10");

        jMenuItem12.setText("jMenuItem12");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Loja Filhos de Odin");
        setBackground(new java.awt.Color(51, 0, 51));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusTraversalPolicyProvider(true);
        setLocationByPlatform(true);
        setMinimumSize(null);
        setResizable(false);

        telaDesk.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout telaDeskLayout = new javax.swing.GroupLayout(telaDesk);
        telaDesk.setLayout(telaDeskLayout);
        telaDeskLayout.setHorizontalGroup(
            telaDeskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1176, Short.MAX_VALUE)
        );
        telaDeskLayout.setVerticalGroup(
            telaDeskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 748, Short.MAX_VALUE)
        );

        jMenuBar1.setBackground(new java.awt.Color(102, 102, 255));
        jMenuBar1.setBorder(null);
        jMenuBar1.setDoubleBuffered(true);
        jMenuBar1.setFocusCycleRoot(true);
        jMenuBar1.add(jMenu1);

        jMenu8.setBorder(null);
        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/oie_transparent (1).png"))); // NOI18N
        jMenu8.setText("Vendas");
        jMenu8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu8MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu8);

        jMenu7.setBackground(new java.awt.Color(51, 255, 204));
        jMenu7.setBorder(null);
        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/oie_transparent.png"))); // NOI18N
        jMenu7.setText("Clientes");
        jMenu7.setToolTipText("");
        jMenu7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu7MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu7);

        jMenu12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icon_rh.png"))); // NOI18N
        jMenu12.setText("Funcionários");
        jMenu12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu12MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu12);

        jMenu10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/weapon_ak_1326050.png"))); // NOI18N
        jMenu10.setText("Armas");
        jMenu10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu10MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu10);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(telaDesk)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(telaDesk)
        );

        setSize(new java.awt.Dimension(1192, 838));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu7MouseClicked
        ControleClientes controleClientes = new ControleClientes();
        telaDesk.add(controleClientes);
        controleClientes.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu7MouseClicked

    private void jMenu12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu12MouseClicked
        ControleFuncionarios controleFuncionarios = new ControleFuncionarios(); 
        telaDesk.add(controleFuncionarios);
        controleFuncionarios.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu12MouseClicked

    private void jMenu8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu8MouseClicked
        ControleVendas controleVendas = new ControleVendas();
        telaDesk.add(controleVendas);
        controleVendas.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu8MouseClicked

    private void jMenu10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu10MouseClicked
        ControleMercadoria controleArmas = new ControleMercadoria();
        telaDesk.add(controleArmas);
        controleArmas.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu10MouseClicked

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JDesktopPane telaDesk;
    // End of variables declaration//GEN-END:variables
}
