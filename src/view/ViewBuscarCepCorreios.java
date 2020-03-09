/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author user
 */
public class ViewBuscarCepCorreios extends javax.swing.JFrame {

    ClienteViaCepWS clienteViaCep = new ClienteViaCepWS();

    /**
     * Creates new form ViewBuscarCepCorreios
     */
    public ViewBuscarCepCorreios() {
        initComponents();
 
//        JSONObject programador1 = new JSONObject();
//        programador1.put("Nome", "Jurandir malaquias");
//        programador1.put("idade", "33");
//        programador1.put("Signo", "Touro");
//        programador1.put("CPF", "123.456.789-11");
//
//        JSONArray lingPro1 = new JSONArray();
//        lingPro1.add("Java");
//        lingPro1.add("PHP");
//        lingPro1.add("SQL");
//
//        programador1.put("Linguaguem", lingPro1);
//
//        //System.out.println(programador1.toJSONString());
//        JSONObject programador2 = new JSONObject();
//        programador2.put("Nome", "Macauly malaquias");
//        programador2.put("idade", "25");
//        programador2.put("Signo", "Gemeos");
//        programador2.put("CPF", "321.456.789-11");
//
//        JSONArray lingPro2 = new JSONArray();
//        lingPro2.add("Cobol");
//        lingPro2.add("Javascript");
//        lingPro2.add("SQL");
//
//        programador2.put("Linguaguem", lingPro2);
//
//        //System.out.println(programador2.toJSONString());
//        JSONArray programadorLista = new JSONArray();
//        programadorLista.add(programador1);
//        programadorLista.add(programador2);
//
//        //System.out.println(programadorLista.toJSONString());
//        try (FileWriter arqJson = new FileWriter("programadores.json")) {
//
//            arqJson.write(programadorLista.toJSONString());
//            arqJson.flush();
//
//        } catch (Exception e) {
//        }
//**************************************************************************

        //https://viacep.com.br/ws/30530600/json/
        //https://viacep.com.br/ws/MG/CONTAGEM/dezenove/json/
        JSONParser jsonParser = new JSONParser();

        JSONArray programadorLista = new JSONArray();
        programadorLista.add(clienteViaCep.buscarCep("32183560"));

        try (FileWriter arqJson = new FileWriter("programadores.json")) {

            arqJson.write(programadorLista.toJSONString());
            arqJson.flush();

        } catch (Exception e) {
        }

        try (FileReader arqJsonReader = new FileReader("programadores.json")) {

            Object obj = jsonParser.parse(arqJsonReader);
            JSONArray programadoresArray = (JSONArray) obj;

            for (Iterator it = programadoresArray.iterator(); it.hasNext();) {
                Object object = it.next();
                lerObjetosJsonCep((JSONObject) object);

            }

            //programadoresArray.forEach(programador -> parserProgramador((JSONObject)programador));
            //arqJsonReader.read();
        } catch (Exception e) {
        }

    }

    private void lerObjetosJsonCep(JSONObject pObjetoJson) {

        System.out.println(pObjetoJson.get("cep"));
        System.out.println(pObjetoJson.get("logradouro"));
        System.out.println(pObjetoJson.get("bairro"));
        System.out.println(pObjetoJson.get("localidade"));
        System.out.println(pObjetoJson.get("uf"));

        jtfCep.setText(pObjetoJson.get("cep").toString());
        jtfBairro.setText(pObjetoJson.get("bairro").toString());
        jtfRua.setText(pObjetoJson.get("logradouro").toString());
        jtfEstado.setText(pObjetoJson.get("uf").toString());
        jtfCidade.setText(pObjetoJson.get("localidade").toString());
        
       

//        JSONArray lingArray = (JSONArray) pObjetoJson.get("cep");
//
//        for (Object linguagemArray : lingArray) {
//            System.out.print(linguagemArray.toString() + " ");
//        }
        System.out.println("\n-----------------");

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
        jtfRua = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jtfBairro = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtfCidade = new javax.swing.JTextField();
        jtfEstado = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtfCep = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jpOutrosEnd = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 102));

        jtfRua.setPreferredSize(new java.awt.Dimension(100, 20));

        jLabel1.setText("Rua");

        jtfBairro.setPreferredSize(new java.awt.Dimension(100, 20));

        jLabel2.setText("Bairro");

        jLabel3.setText("Cidade");

        jtfCidade.setPreferredSize(new java.awt.Dimension(100, 20));

        jtfEstado.setPreferredSize(new java.awt.Dimension(100, 20));

        jLabel4.setText("Estado");

        jLabel5.setText("CEP");

        jtfCep.setPreferredSize(new java.awt.Dimension(100, 20));

        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Limpar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jpOutrosEnd.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jpOutrosEndLayout = new javax.swing.GroupLayout(jpOutrosEnd);
        jpOutrosEnd.setLayout(jpOutrosEndLayout);
        jpOutrosEndLayout.setHorizontalGroup(
            jpOutrosEndLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpOutrosEndLayout.setVerticalGroup(
            jpOutrosEndLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
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
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfCep, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfRua, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 96, Short.MAX_VALUE))
                    .addComponent(jpOutrosEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfRua, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jpOutrosEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jtfRua.setText("");
        jtfBairro.setText("");
        jtfCidade.setText("");
        jtfEstado.setText("");
        jtfCep.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        //lerObjetosJsonCep(clienteViaCep.buscarCep(jtfCep.getText()));
        lerObjetosJsonCep(clienteViaCep.buscarCepRua(jtfEstado.getText(), jtfRua.getText(), jtfCidade.getText()));
        
        MeuTableModel listarEnderecos = new MeuTableModel(clienteViaCep.getListaDeEnderecos());
        listarEnderecos.popularjpanel(jpOutrosEnd, this);
        
        
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
            java.util.logging.Logger.getLogger(ViewBuscarCepCorreios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewBuscarCepCorreios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewBuscarCepCorreios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewBuscarCepCorreios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewBuscarCepCorreios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jpOutrosEnd;
    private javax.swing.JTextField jtfBairro;
    private javax.swing.JTextField jtfCep;
    private javax.swing.JTextField jtfCidade;
    private javax.swing.JTextField jtfEstado;
    private javax.swing.JTextField jtfRua;
    // End of variables declaration//GEN-END:variables
}
