/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import util.BLDatas;

/**
 *
 * @author user
 */
public class ViewCalendario extends javax.swing.JDialog {

    BLDatas formatarDatas = new BLDatas();
    GregorianCalendar datas = new GregorianCalendar();
    private String dataSelecionada = "";

    /**
     * Creates new form NewJFrame
     */
    public ViewCalendario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();

        setLocationRelativeTo(null);

        carregarCalendario(0, 3);
        jbHoje.setText("Hoje - " + formatarDatas.retornarDataHojeBR());
       
        

    }

    /**
     * Se for mês anteriro parametro mes = 0 se for mês seguinte parametro mes =
     * 1
     *
     * @param dia
     * @param mesAntProx
     */
    private void carregarCalendario(int dia, int mesAntProx) {

        jpCorpoCalendario.removeAll();

        if (mesAntProx == 0) {
            if (datas.MONTH == 1) {
                datas.add(datas.YEAR, -1);
            }
            datas.add(datas.MONTH, -1);
        }
        if (mesAntProx == 1) {
            if (datas.MONTH == 12) {
                datas.add(datas.YEAR, +1);
            }
            datas.add(datas.MONTH, +1);
        }
        if (dia > 0) {

            datas.set(datas.DAY_OF_MONTH, dia);
        }

        //System.out.println(fimDoMes + " - " + datas.getMinimalDaysInFirstWeek());
        //datas.set(datas.getMinimalDaysInFirstWeek(), 0);
        //datas.set(datas.DAY_OF_MONTH, 0);
        //1 = ano
        //2 = mês
        //5 = dia
        //11 = hora
        //12 = minuto
        //datas.set(2020, 01 - 1, 15);
        int diaAtual = datas.get(datas.DAY_OF_MONTH);
        int mesAtual = datas.get(datas.MONTH);
        mesAtual = mesAtual + 1;
        int anoAtual = datas.get(datas.YEAR);
        int quantidadeDiasNoMes = datas.getActualMaximum(datas.DAY_OF_MONTH);
        System.out.println("Quant dias no mes " + quantidadeDiasNoMes);

        datas.set(datas.DAY_OF_MONTH, 1);
        int diaDaSemanaDestemes = datas.get(datas.DAY_OF_WEEK);//DIA DA SEMANA 1 SEGUNDA 7 DOMINGO
        System.out.println("Dia da semana Deste mÊs " + diaDaSemanaDestemes);
        System.out.println(datas.getTime());
        datas.add(datas.MONTH, -1);

        int ultimoDiaMesPassado = datas.getActualMaximum(datas.DAY_OF_MONTH);
        if (diaDaSemanaDestemes == 1) {
            diaDaSemanaDestemes = 8;
        }

        datas.set(anoAtual, mesAtual - 1, diaAtual);

        int contadorPassado = ultimoDiaMesPassado - diaDaSemanaDestemes + 2;
        System.out.println("Contador passado  " + contadorPassado);

        int cont = 0;
        boolean mespass = false;
        boolean mesagora = false;
        int bt3 = 1;
        JButton jb1 = new JButton();
        JButton jb2 = new JButton();
        jb2.setBackground(Color.gray);

        while (cont < 42) {
            if (!mespass) {
                for (int i = 1; i < diaDaSemanaDestemes; i++) {

                    jb2 = new JButton(String.valueOf(contadorPassado));
                    jb2.setSize(45, 37);
                    contadorPassado++;
                    jb2.setBackground(Color.gray);
                    final int nomeBotao = contadorPassado - 1;
                    System.out.println("dia " + nomeBotao);
                    jb2.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            carregarCalendario(nomeBotao, 0);
                            System.out.println("dia " + nomeBotao);
                        }
                    });

                    jpCorpoCalendario.add(jb2);
                    cont++;

                }
                mespass = true;
            }

            if (!mesagora) {

                for (int i = 0; i < quantidadeDiasNoMes; i++) {
                    jb1 = new JButton(String.valueOf(i + 1));
                    if (diaAtual == i + 1) {
                        jb1.setBackground(Color.blue);
                    }

                    final int nomeBotao = i + 1;
                    jb1.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            carregarCalendario(nomeBotao, 2);
                        }
                    });

                    jpCorpoCalendario.add(jb1);
                    cont++;
                    mesagora = true;

                }
            }

            jb2 = new JButton(String.valueOf(bt3++));
            final int nomeBotao = bt3 - 1;
            jb2.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    carregarCalendario(nomeBotao, 1);
                }
            });
            jpCorpoCalendario.add(jb2);
            jb2.setBackground(Color.gray);
            cont++;

        }

        alterarDataNoForm(diaAtual + "/" + mesAtual + "/" + anoAtual);
        jpCorpoCalendario.validate();
    }

    private void alterarDataNoForm(String datam) {

        jtfDataAtual.setText(datam);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpTopoDataInicial = new javax.swing.JPanel();
        jbRetroceder = new javax.swing.JButton();
        jtfDataAtual = new javax.swing.JFormattedTextField();
        jbAvancar = new javax.swing.JButton();
        jpCorpoCalendario = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jbHoje = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

        jpTopoDataInicial.setLayout(new java.awt.GridLayout(1, 3));

        jbRetroceder.setText("<<");
        jbRetroceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRetrocederActionPerformed(evt);
            }
        });
        jpTopoDataInicial.add(jbRetroceder);

        jtfDataAtual.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        jtfDataAtual.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfDataAtual.setText("20/01/2020");
        jtfDataAtual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfDataAtualKeyReleased(evt);
            }
        });
        jpTopoDataInicial.add(jtfDataAtual);

        jbAvancar.setText(">>");
        jbAvancar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAvancarActionPerformed(evt);
            }
        });
        jpTopoDataInicial.add(jbAvancar);

        jpCorpoCalendario.setLayout(new java.awt.GridLayout(6, 7, 1, 1));

        jPanel1.setLayout(new java.awt.GridLayout(1, 7));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("D");
        jLabel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.add(jLabel4);

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("S");
        jLabel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.add(jLabel5);

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("T");
        jLabel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.add(jLabel6);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Q");
        jLabel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.add(jLabel7);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Q");
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.add(jLabel1);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("S");
        jLabel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.add(jLabel2);

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("S");
        jLabel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.add(jLabel3);

        jbHoje.setText("Hoje (00/00/0000)");
        jbHoje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbHojeActionPerformed(evt);
            }
        });

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpTopoDataInicial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addComponent(jpCorpoCalendario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbHoje)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jpTopoDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpCorpoCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbHoje)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbRetrocederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRetrocederActionPerformed
        carregarCalendario(1, 0);
    }//GEN-LAST:event_jbRetrocederActionPerformed

    private void jbAvancarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAvancarActionPerformed
        carregarCalendario(1, 1);
    }//GEN-LAST:event_jbAvancarActionPerformed

    private void jtfDataAtualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfDataAtualKeyReleased

        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                datas.setTime(formatarDatas.converterDataStringParaDate(jtfDataAtual.getText()));
                carregarCalendario(0, 2);
            }

        } catch (Exception ex) {

        }

    }//GEN-LAST:event_jtfDataAtualKeyReleased

    private void jbHojeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbHojeActionPerformed

        try {
            datas.setTime(formatarDatas.converterDataStringParaDateUS(formatarDatas.retornarDataHojeUS()));
            carregarCalendario(0, 2);

        } catch (Exception e) {
        }


    }//GEN-LAST:event_jbHojeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setDataSelecionada(jtfDataAtual.getText());
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ViewCalendario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewCalendario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewCalendario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewCalendario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                ViewCalendario dialog = new ViewCalendario(new javax.swing.JFrame(), true);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbAvancar;
    private javax.swing.JButton jbHoje;
    private javax.swing.JButton jbRetroceder;
    private javax.swing.JPanel jpCorpoCalendario;
    private javax.swing.JPanel jpTopoDataInicial;
    private javax.swing.JFormattedTextField jtfDataAtual;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the dataSelecionada
     */
    public String getDataSelecionada() {
        return dataSelecionada;
    }

    /**
     * @param dataSelecionada the dataSelecionada to set
     */
    public void setDataSelecionada(String dataSelecionada) {
        this.dataSelecionada = dataSelecionada;
    }

     
}
