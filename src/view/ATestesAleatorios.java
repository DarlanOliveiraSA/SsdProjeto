/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
 //teste
// adicionando teste
import java.awt.Color;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import util.BLDatas;

/**
 *
 * @author user
 */
public class ATestesAleatorios extends javax.swing.JFrame {

    BLDatas formatarDatas = new BLDatas();
    GregorianCalendar datas = new GregorianCalendar();

    /**
     * Creates new form NewJFrame
     */
    public ATestesAleatorios() {

        initComponents();

        setLocationRelativeTo(null);

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

        datas.set(anoAtual, mesAtual - 1, diaAtual);
        System.out.println("Data salva atual: " + datas.getTime());
        System.out.println("data de hoje: " + diaAtual + "/" + mesAtual + "/" + anoAtual);

        datas.set(anoAtual, mesAtual - 1, diaAtual);
        int quantidadeDiasNoMes = datas.getActualMaximum(datas.DAY_OF_MONTH);
        System.out.println("ultimo dia deste mês: " + quantidadeDiasNoMes);

        datas.set(datas.DAY_OF_MONTH, 1);

        int semanaDia = datas.get(datas.DAY_OF_WEEK);//DIA DA SEMANA 1 SEGUNDA 7 DOMINGO
        System.out.println("dia da semana que o mes selecionado começa: " + semanaDia);

        int ultimoDiaMesPassado = datas.getMaximum(datas.DAY_OF_MONTH);
        System.out.println("ultimo dia mes passado: " + ultimoDiaMesPassado);

        int contadorPassado = ultimoDiaMesPassado - semanaDia + 2;
        System.out.println("dias exibidos do mespassado: " + contadorPassado);

        int cont = 0;
        boolean mespass = false;
        boolean mesagora = false;
        int bt3 = 1;
        JButton jb1 = new JButton();
        JButton jb2 = new JButton();
        jb2.setBackground(Color.gray);

        while (cont < 42) {
            if (!mespass) {
                for (int i = 0; i < semanaDia - 1; i++) {
                    jb2 = new JButton(String.valueOf(contadorPassado++));
                    jb2.setBackground(Color.gray);
                    System.out.println("prim" + cont);
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

                    System.out.println("seg" + cont);
                    jpCorpoCalendario.add(jb1);
                    cont++;
                    mesagora = true;

                }
            }

            jb2 = new JButton(String.valueOf(bt3++));
            System.out.println("ter" + cont);
            jpCorpoCalendario.add(jb2);
            jb2.setBackground(Color.gray);
            cont++;

        }

        alterarDataNoForm(diaAtual + "/" + mesAtual + "/" + anoAtual);

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        jpTopoDataInicial.add(jtfDataAtual);

        jbAvancar.setText(">>");
        jpTopoDataInicial.add(jbAvancar);

        jpCorpoCalendario.setLayout(new java.awt.GridLayout(6, 7));

        jPanel1.setLayout(new java.awt.GridLayout(1, 7));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Q");
        jLabel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.add(jLabel4);

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Q");
        jLabel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.add(jLabel5);

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("S");
        jLabel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.add(jLabel6);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("S");
        jLabel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.add(jLabel7);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("D");
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
        jLabel3.setText("T");
        jLabel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.add(jLabel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpCorpoCalendario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpTopoDataInicial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jpTopoDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jpCorpoCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbRetrocederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRetrocederActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbRetrocederActionPerformed

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
            java.util.logging.Logger.getLogger(ATestesAleatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ATestesAleatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ATestesAleatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ATestesAleatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ATestesAleatorios().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbAvancar;
    private javax.swing.JButton jbRetroceder;
    private javax.swing.JPanel jpCorpoCalendario;
    private javax.swing.JPanel jpTopoDataInicial;
    private javax.swing.JFormattedTextField jtfDataAtual;
    // End of variables declaration//GEN-END:variables
}
