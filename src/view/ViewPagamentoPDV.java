/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerFormaPagamento;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import model.ModelFormaPagamento;
import util.BLMascaras;

/**
 *
 * @author Darlan
 */
public class ViewPagamentoPDV extends javax.swing.JDialog {

    ArrayList<ModelFormaPagamento> listaModelFormaPagamento = new ArrayList<>();
    ControllerFormaPagamento controllerFormaPagamento = new ControllerFormaPagamento();
    BLMascaras mascaras = new BLMascaras();

    private float valorTotal;//Valor Bruto
    private float desconto;//desconto
    private float valorRecebido;//valor recebido
    private float troco;//troco
    private String formaPagamento;//forma de pagamento
    private boolean statusPagamento;//satus do pagamento, se foi pago ou não
    private float totalAPagar;//Valor liquido

    /**
     * Creates new form ViewPagamentoPDV
     *
     * @param parent
     * @param modal
     */
    public ViewPagamentoPDV(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        listarFormaPagamento();
        setLocationRelativeTo(null);
        statusPagamento = false;
        jcbPagamento.requestFocus();
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
        jLabel6 = new javax.swing.JLabel();
        jtfTroco = new javax.swing.JTextField();
        jbOkPagamentoPDV = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jlValorTotal = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtfSubTotal = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jcbPagamento = new componentes.UJComboBox();
        jLabel3 = new javax.swing.JLabel();
        jtfDesconto = new javax.swing.JFormattedTextField();
        jtfValorRecebido = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Troco:");

        jtfTroco.setEditable(false);
        jtfTroco.setBackground(new java.awt.Color(0, 237, 156));
        jtfTroco.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jtfTroco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfTrocoKeyTyped(evt);
            }
        });

        jbOkPagamentoPDV.setText("OK");
        jbOkPagamentoPDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbOkPagamentoPDVActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor total a pagar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jlValorTotal.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jlValorTotal.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlValorTotal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlValorTotal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Sub-Total:");

        jtfSubTotal.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Pagamento:");

        jcbPagamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jcbPagamentoKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Desconto:");

        jtfDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfDescontoKeyReleased(evt);
            }
        });

        jtfValorRecebido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfValorRecebidoKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Valor recebido:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtfValorRecebido))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbPagamento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                            .addComponent(jtfDesconto)
                            .addComponent(jtfSubTotal))))
                .addGap(32, 32, 32))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcbPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtfDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtfValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtfTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbOkPagamentoPDV, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbOkPagamentoPDV)
                    .addComponent(jLabel6)
                    .addComponent(jtfTroco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
     * Este comando é executado após pressionar OK na janela de pagamento.
     */
    private void finalizarVenda() {
        //Para trtar o que será enviado para o banco de dados.
        desconto = 0;
        valorRecebido = 0;
        troco = 0;

        try {

            if (!jtfDesconto.getText().isEmpty()) {
                desconto = Float.parseFloat(mascaras.converterVirgulaParaPonto(this.jtfDesconto.getText()));
            }
            if (!jtfValorRecebido.getText().isEmpty()) {
                valorRecebido = Float.parseFloat(mascaras.converterVirgulaParaPonto(this.jtfValorRecebido.getText()));
            }
            if (!jtfTroco.getText().isEmpty()) {
                troco = Float.parseFloat(mascaras.converterVirgulaParaPonto(this.jtfTroco.getText()));
            }

        } catch (NumberFormatException e) {

            e.printStackTrace();
            System.out.println(desconto + " Desconto \n " + valorRecebido + " Recebido \n" + troco + " Troco ");
            desconto = 0;
            valorRecebido = 0;
            troco = 0;
        }

        valorTotal = getValorTotal();
        totalAPagar = getTotalAPagar();
        formaPagamento = String.valueOf(jcbPagamento.getSelectedIndex());
        statusPagamento = true;
        dispose();
        jtfDesconto.setText("0");//Na janela viewPagamentoPDv os valores são zerados para iniciar uma nova venda 
        jtfValorRecebido.setText("0");
        jtfTroco.setText("0");
    }


    private void jbOkPagamentoPDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbOkPagamentoPDVActionPerformed
        finalizarVenda();
    }//GEN-LAST:event_jbOkPagamentoPDVActionPerformed

    private void jtfDescontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfDescontoKeyReleased
        // TODO add your handling code here:
        calcularPagamento();
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jtfValorRecebido.requestFocus();
        }
    }//GEN-LAST:event_jtfDescontoKeyReleased

    private void jtfValorRecebidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfValorRecebidoKeyReleased
        // TODO add your handling code here:
        calcularPagamento();
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            finalizarVenda();
        }
    }//GEN-LAST:event_jtfValorRecebidoKeyReleased

    private void jtfTrocoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfTrocoKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            finalizarVenda();
        }
    }//GEN-LAST:event_jtfTrocoKeyTyped

    private void jcbPagamentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcbPagamentoKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jtfDesconto.requestFocus();
        }
    }//GEN-LAST:event_jcbPagamentoKeyReleased
    /**
     * Calculao o valor total a pagar e o troco da janela viewPagamentoPDV
     */
    private void calcularPagamento() {
        float subTotal, recebido, pagar;
        try {
            desconto = mascaras.converterVirgulaParaPontoReturnFloat(jtfDesconto.getText());

        } catch (NumberFormatException e) {
            desconto = 0;
            if (!jtfDesconto.getText().equals("")) {
                jtfDesconto.setText("0");
                desconto = 0;
            }
        }
        try {
            subTotal = Float.parseFloat(jtfSubTotal.getText());
        } catch (NumberFormatException e) {
            subTotal = 0;
        }
        try {
            recebido = mascaras.converterVirgulaParaPontoReturnFloat(jtfValorRecebido.getText());
        } catch (NumberFormatException e) {
            recebido = 0;
            if (!jtfValorRecebido.getText().equals("")) {
                jtfValorRecebido.setText("0");
                recebido = 0;
            }
        }
        //calculos
        pagar = subTotal - desconto;
        troco = recebido - pagar;
        //arredondamento para passar ao banco
        troco = mascaras.arredondamentoComPontoDuasCasas(troco);
        pagar = mascaras.arredondamentoComPontoDuasCasas(pagar);
        setTotalAPagar(pagar);
        //setando os valores nos componentes visuais do front
        jlValorTotal.setText("R$ " + pagar);
        jtfTroco.setText(String.valueOf(troco));

    }

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
            java.util.logging.Logger.getLogger(ViewPagamentoPDV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPagamentoPDV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPagamentoPDV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPagamentoPDV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewPagamentoPDV dialog = new ViewPagamentoPDV(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    /**
     * Preenche o combobox com todas as formas de pagamento cadsatradas.
     */
    private void listarFormaPagamento() {
        listaModelFormaPagamento = controllerFormaPagamento.getListaFormaPagamentoController();
        jcbPagamento.removeAllItems();
        for (int i = 0; i < listaModelFormaPagamento.size(); i++) {
            jcbPagamento.addItem(listaModelFormaPagamento.get(i).getDescricaoForPag());
        }

    }

    /**
     * Lança o valor total recebido da ViewPDV na atributo double valorTotal
     * desta guia para ser usado
     */
    public void setTextFildValorTotal() {
        this.jtfSubTotal.setText(this.valorTotal + "");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jbOkPagamentoPDV;
    private componentes.UJComboBox jcbPagamento;
    private javax.swing.JLabel jlValorTotal;
    private javax.swing.JFormattedTextField jtfDesconto;
    private javax.swing.JFormattedTextField jtfSubTotal;
    private javax.swing.JTextField jtfTroco;
    private javax.swing.JFormattedTextField jtfValorRecebido;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the valorTotal
     */
    public float getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * @return the desconto
     */
    public float getDesconto() {
        return desconto;
    }

    /**
     * @param desconto the desconto to set
     */
    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    /**
     * @return the valorRecebido
     */
    public float getValorRecebido() {
        return valorRecebido;
    }

    /**
     * @param valorRecebido the valorRecebido to set
     */
    public void setValorRecebido(float valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    /**
     * @return the troco
     */
    public float getTroco() {
        return troco;
    }

    /**
     * @param troco the troco to set
     */
    public void setTroco(float troco) {
        this.troco = troco;
    }

    /**
     * @return the formaPagamento
     */
    public String getFormaPagamento() {
        return formaPagamento;
    }

    /**
     * @param formaPagamento the formaPagamento to set
     */
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    /**
     * @return the statusPagamento
     */
    public boolean isStatusPagamento() {
        return statusPagamento;
    }

    /**
     * @param statusPagamento the statusPagamento to set
     */
    public void setStatusPagamento(boolean statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    /**
     * @return the totalAPagar
     */
    public float getTotalAPagar() {
        return totalAPagar;
    }

    /**
     * @param totalAPagar the totalAPagar to set
     */
    public void setTotalAPagar(float totalAPagar) {
        this.totalAPagar = totalAPagar;
    }
}
