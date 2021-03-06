/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerProdutos;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.ModelProduto;
import util.Formatador;

/**
 *
 * @author Darlan
 */
public class ViewProduto extends javax.swing.JFrame {

    /**
     * Construtor onde colocarei os métodos que serão chamados dentro da classe
     */
    ArrayList<ModelProduto> listaModelProduto = new ArrayList<>();
    ControllerProdutos controllerProdutos = new ControllerProdutos();
    ModelProduto modelProdutos = new ModelProduto();
    String salvarAlterar;
    Formatador formatador = new Formatador();

    /**
     * Creates new form ViewProduto
     */
    public ViewProduto() {
        initComponents();
        carregarProdutos();
        setLocationRelativeTo(null);
        habilitarDesabiliarCampos(false);
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
        jLabel1 = new javax.swing.JLabel();
        jtfCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtableProdutos = new javax.swing.JTable();
        jbPesquisa = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jtfPesquisa = new javax.swing.JTextField();
        jbCancelar = new javax.swing.JButton();
        jbNovo = new javax.swing.JButton();
        jbAlterar = new javax.swing.JButton();
        jbSalvar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jtfEstoque = new javax.swing.JFormattedTextField();
        jtfValor = new javax.swing.JFormattedTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Produtos");
        setIconImages(null);
        setResizable(false);

        jPanel1.setName(""); // NOI18N

        jLabel1.setText("Código:");

        jtfCodigo.setEditable(false);
        jtfCodigo.setEnabled(false);

        jLabel2.setText("Nome do produto:");

        jLabel3.setText("Estoque:");

        jLabel4.setText("Valor:");

        jtableProdutos.setAutoCreateRowSorter(true);
        jtableProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Quantidade", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtableProdutos);
        if (jtableProdutos.getColumnModel().getColumnCount() > 0) {
            jtableProdutos.getColumnModel().getColumn(1).setPreferredWidth(300);
        }

        jbPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/iconfinder_search_285651.png"))); // NOI18N
        jbPesquisa.setText("Pesquisar");
        jbPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisaActionPerformed(evt);
            }
        });

        jLabel5.setText("Pesquisar:");

        jtfPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfPesquisaActionPerformed(evt);
            }
        });
        jtfPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfPesquisaKeyPressed(evt);
            }
        });

        jbCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/iconfinder_sign-error_299045.png"))); // NOI18N
        jbCancelar.setText("Cancelar");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jbNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/iconfinder_sign-add_299068.png"))); // NOI18N
        jbNovo.setText("Novo");
        jbNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNovoActionPerformed(evt);
            }
        });

        jbAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/iconfinder_pencil_285638.png"))); // NOI18N
        jbAlterar.setText("Alterar");
        jbAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAlterarActionPerformed(evt);
            }
        });

        jbSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/iconfinder_floppy_285657.png"))); // NOI18N
        jbSalvar.setText("Salvar");
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jbExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/iconfinder_trashcan_299071.png"))); // NOI18N
        jbExcluir.setText("Excluir");
        jbExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirActionPerformed(evt);
            }
        });

        jtfEstoque.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jtfValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("GERENCIAR PRODUTOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtfPesquisa)
                        .addGap(18, 18, 18)
                        .addComponent(jbPesquisa))
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jbCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbExcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbAlterar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbNovo)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbSalvar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfValor)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(0, 82, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jtfEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(0, 377, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jbPesquisa)
                    .addComponent(jtfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jtfEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbNovo)
                    .addComponent(jbCancelar)
                    .addComponent(jbAlterar)
                    .addComponent(jbExcluir)
                    .addComponent(jbSalvar))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisaActionPerformed
        // Pesquisar na tabela através da função de filtro:
        DefaultTableModel modelo = (DefaultTableModel) this.jtableProdutos.getModel();
        final TableRowSorter<DefaultTableModel> classificador = new TableRowSorter<>(modelo);
        this.jtableProdutos.setRowSorter(classificador);
        String texto = jtfPesquisa.getText();
        classificador.setRowFilter(RowFilter.regexFilter("(?i)" + texto, 1));

    }//GEN-LAST:event_jbPesquisaActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        // Limpar todos os campos do formulário cancelando o evento de inserir novo produto
        habilitarDesabiliarCampos(false);
        this.carregarProdutos();
        limparCampos();

    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jtfPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfPesquisaActionPerformed

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        // Salva um novo produto no banco de dados SQL
        if (salvarAlterar.equals("salvar")) {
            this.salvarProduto();
        } else if (salvarAlterar.equals("alterar")) {
            this.alterarProduto();
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirActionPerformed
        // Exclui um produto no banco de dados
        int linha = jtableProdutos.getSelectedRow();
        int codigoProduto = (int) jtableProdutos.getValueAt(linha, 0);

        if (controllerProdutos.excluirProdutoController(codigoProduto)) {
            JOptionPane.showMessageDialog(this, "Produto excluído com sucesso", "Bom trabalho", JOptionPane.INFORMATION_MESSAGE);
            this.carregarProdutos();
            this.habilitarDesabiliarCampos(false);
            this.limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir o produto, consulto a SSD para mais detalhes", "Falha", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jbExcluirActionPerformed

    private void jbNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNovoActionPerformed
        // Habilita os campos para que o usuário consiga inserir um novo produto
        habilitarDesabiliarCampos(true);
        salvarAlterar = "salvar";
    }//GEN-LAST:event_jbNovoActionPerformed

    private void jbAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAlterarActionPerformed
        // Altera o registro da interface e salva no banco de dados
        salvarAlterar = "alterar";
        habilitarDesabiliarCampos(true);
        int linha = jtableProdutos.getSelectedRow();
        try {
            int codigoProduto = (int) this.jtableProdutos.getValueAt(linha, 0);
            //recupera os dados do banco através do codigo do produto
            modelProdutos = controllerProdutos.retornarProdutoController(codigoProduto);
            //joga o produto recuperado na interface gráfica
            this.jtfCodigo.setText(String.valueOf(modelProdutos.getIdProduto()));
            this.jtfNome.setText(modelProdutos.getProNome());
            this.jtfEstoque.setText(String.valueOf(modelProdutos.getProEstoque()));
            this.jtfValor.setText(String.valueOf(modelProdutos.getProValor()));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao alterar o produto, codigo inválido ou produto não foi selecionado", "Ops", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jbAlterarActionPerformed

    private void jtfPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfPesquisaKeyPressed
        /**
         * Inseri por minha conta esta função para pesquisar ao digitar, sem
         * precisar clicar em pesquisar.
         */
        //Compara se está vazio, se estiver vazio ele carrega a tabela completa
        if(" ".equals(jtfPesquisa.getText())){
               resetarTabela();
        }else{
        DefaultTableModel modelo = (DefaultTableModel) this.jtableProdutos.getModel();
        final TableRowSorter<DefaultTableModel> classificador = new TableRowSorter<>(modelo);
        this.jtableProdutos.setRowSorter(classificador);
        String texto = jtfPesquisa.getText();
        classificador.setRowFilter(RowFilter.regexFilter("(?i)" + texto, 1));
        }
        
    }//GEN-LAST:event_jtfPesquisaKeyPressed

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
            java.util.logging.Logger.getLogger(ViewProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewProduto().setVisible(true);
            }
        });
    }

    private void salvarProduto() {
        // Salva um novo produto no banco de dados SQL
        modelProdutos.setProNome(this.jtfNome.getText().toUpperCase());
        modelProdutos.setProEstoque(Integer.parseInt(this.jtfEstoque.getText()));
        modelProdutos.setProValor(formatador.converterVirgulaParaPontoReturn(this.jtfValor.getText()));
        if (controllerProdutos.salvarProdutoController(modelProdutos) > 0) {
            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!", "Bom trabalho", JOptionPane.INFORMATION_MESSAGE);
            this.carregarProdutos();
            this.limparCampos();
            this.habilitarDesabiliarCampos(false);
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar o produto,consulto a SSD para mais detalhes", "Falha", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterarProduto() {
        // Altera um produto no banco de dados SQL
        modelProdutos.setProNome(this.jtfNome.getText());
        modelProdutos.setProEstoque(Integer.parseInt(this.jtfEstoque.getText()));
        modelProdutos.setProValor(formatador.converterVirgulaParaPontoReturn(this.jtfValor.getText()));
        if (controllerProdutos.alterarProdutoController(modelProdutos)) {
            JOptionPane.showMessageDialog(this, "Produto Alterado com sucesso!", "Bom trabalho", JOptionPane.INFORMATION_MESSAGE);
            this.carregarProdutos();
            this.limparCampos();
            this.habilitarDesabiliarCampos(false);
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao Alterar o produto,consulto a SSD para mais detalhes", "Falha", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo usado para habilitar ou desabilitar campos, setar true para
     * habilitar...
     *
     * @param condicao
     */
    private void habilitarDesabiliarCampos(boolean condicao) {
        jtfNome.setEnabled(condicao);
        jtfEstoque.setEnabled(condicao);
        jtfValor.setEnabled(condicao);
        jbSalvar.setEnabled(condicao);
    }

    /**
     * Reseta a tabela dos produtos
     */
    private void resetarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.jtableProdutos.getModel();
        final TableRowSorter<DefaultTableModel> classificador = new TableRowSorter<>(modelo);
        this.jtableProdutos.setRowSorter(classificador);
        String texto = "";
        classificador.setRowFilter(RowFilter.regexFilter("(?i)" + texto, 1));
    }

    /**
     * Limpar os campos do formulário produtos
     *
     * @param condicao
     */
    private void limparCampos() {
        jtfCodigo.setText("");
        jtfNome.setText("");
        jtfEstoque.setText("");
        jtfValor.setText("");
        jtfPesquisa.setText("");
        resetarTabela();

    }

    /**
     * Preenche a tabela de produtos com os produtos cadastrados no banco
     */
    private void carregarProdutos() {
        listaModelProduto = controllerProdutos.retornarListaProdutoController();
        DefaultTableModel modelo = (DefaultTableModel) jtableProdutos.getModel();
        modelo.setNumRows(0);
        //Inserir produtos na minha tabela de produtos
        int cont = listaModelProduto.size();
        for (int i = 0; i < cont; i++) {
            modelo.addRow(new Object[]{
                listaModelProduto.get(i).getIdProduto(),
                listaModelProduto.get(i).getProNome(),
                listaModelProduto.get(i).getProEstoque(),
                listaModelProduto.get(i).getProValor()

            });
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbAlterar;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbNovo;
    private javax.swing.JButton jbPesquisa;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JTable jtableProdutos;
    private javax.swing.JTextField jtfCodigo;
    private javax.swing.JFormattedTextField jtfEstoque;
    private javax.swing.JTextField jtfNome;
    private javax.swing.JTextField jtfPesquisa;
    private javax.swing.JFormattedTextField jtfValor;
    // End of variables declaration//GEN-END:variables
}
