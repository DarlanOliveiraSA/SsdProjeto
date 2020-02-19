/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerCliente;
import controller.ControllerProdutos;
import controller.ControllerProdutosVendasProdutos;
import controller.ControllerVendas;
import controller.ControllerVendasProdutos;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.ModelCliente;
import model.ModelProduto;
import model.ModelProdutosDaVenda;
import model.ModelProdutosVendasProdutos;
import model.ModelVendas;
import model.ModelVendasCliente;
import util.BLDatas;
import util.Formatador;

/**
 *
 * @author user
 */
public class ViewVendasFinal extends javax.swing.JFrame {

    ControllerCliente controllerCliente = new ControllerCliente();
    ModelCliente modelCliente = new ModelCliente();
    ArrayList<ModelCliente> listaModelClientes = new ArrayList<>();
    ControllerProdutos controllerProdutos = new ControllerProdutos();
    ModelProduto modelProduto = new ModelProduto(); //Cria um objeto do tipo produto
    ArrayList<ModelProduto> listaModelProdutos = new ArrayList<>();
    ArrayList<ModelVendasCliente> listaModelVendasClientes = new ArrayList<>();
    ControllerVendas controllerVendas = new ControllerVendas();
    ModelVendas modelVenda = new ModelVendas();
    BLDatas blDatas = new BLDatas();
    ControllerVendasProdutos controllerVendasProdutos = new ControllerVendasProdutos();
    ModelProdutosDaVenda modelVendasProdutos = new ModelProdutosDaVenda();
    ArrayList<ModelProdutosDaVenda> listaModelVendasProdutos = new ArrayList<>();
    ControllerProdutosVendasProdutos controllerProdutosVendasProdutos = new ControllerProdutosVendasProdutos();
    ArrayList<ModelProdutosVendasProdutos> listaModelProdutosVendasprodutos = new ArrayList<>();
    Formatador formatador = new Formatador();
    String alterarSalvar = "salvar";
    ViewClienteFinal viewCliente = new ViewClienteFinal();
    private ViewVisualizarVendas viewVisualizarVendas;
    private ModelVendas modelVendaAlteracao = new ModelVendas();
    JFrame janelaProdutosCadastrados = new JFrame("Selecione um produto");
    JPanel painel = new JPanel();
    JComboBox produtos = new JComboBox();
    boolean statusEdicao = false;
    String nomeDaEmpresa = "Nome da empresa padrão";

    private static Point point = new Point();

    private void janelaSelecaoProduto() {
        janelaProdutosCadastrados.dispose();
        janelaProdutosCadastrados = new JFrame("Selecione um produto");
        painel = new JPanel();
        produtos = new JComboBox();
        janelaProdutosCadastrados.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janelaProdutosCadastrados.setType(Type.POPUP);
        listaModelProdutos = new ArrayList();
        listaModelProdutos = controllerProdutos.retornarListaProdutoController();
        for (int i = 0; i < listaModelProdutos.size(); i++) {
            produtos.addItem(listaModelProdutos.get(i).getProNome());
        }
        JLabel label = new JLabel();
        produtos.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            @Override
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }

            @Override
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                selecionarProdutoNaLista(evt);
            }

            @Override
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        label.setText("Selecione um produto da lista para adicionar ao pedido");
        painel.add(label);
        painel.add(produtos);
        janelaProdutosCadastrados.add(painel);
        janelaProdutosCadastrados.setSize(400, 100);
        janelaProdutosCadastrados.setLocationRelativeTo(this);
        janelaProdutosCadastrados.setVisible(true);
        janelaProdutosCadastrados.setAlwaysOnTop(true);

    }

    private void onload() {
        initComponents();

        listarProdutos();
        setLocationRelativeTo(null);
        //preencherCodigoClientePeloCombobox();
        //preencherCodigoProdutoPeloCombobox();
        viewCliente.dispose();
        setAlwaysOnTop(true);

    }

    /**
     * Creates new form viewTelaDeVendas
     */
    public ViewVendasFinal() {
        onload();

    }

    public ViewVendasFinal(ModelVendas pModelVendasASerAlterada) {
        onload();

        modelVendaAlteracao = pModelVendasASerAlterada;
        carregarVendaParaAlteracao();

    }

    public void iniciarNovaVenda() {
        int idDaVenda = controllerVendas.recuperarUltimaVendaController();
        idDaVenda++;
        habilitarDesabilitarCambos(true);
        habilitarDesabilitarCampCliente(false);
        limparFormulario();
        jtfCodigoDaVenda.setText(idDaVenda + "");
        alterarSalvar = "salvar";

    }

    /**
     * Serve para habilitar ou desabilitar todos os campos do cliente
     *
     * @param status
     */
    public void habilitarDesabilitarCampCliente(boolean status) {
        System.out.println("Habilitar campos Cliente deu"+status);
        jtfNomeDoCliente.setEnabled(status);
        jtfBairro.setEnabled(status);
        jtfCidade.setEnabled(status);
        jtfCodigoCliente.setEnabled(status);
        jtfNumCasa.setEnabled(status);
        jtfEstado.setEnabled(status);
        jtfEndereco.setEnabled(status);
        jtfPontoDeReferencia.setEnabled(status);

    }

    private void carregarVendaParaAlteracao() {

        try {
            modelCliente = controllerCliente.getClienteController(modelVendaAlteracao.getCliente());
            habilitarDesabilitarCambos(true);
            atualizarCampos(modelCliente);//Preenche o campo cliente, com o cliente da venda.

            alterarSalvar = "alterar";
            int codigoVenda = modelVendaAlteracao.getIdVenda(); //pega o código da venda na linha selecionada

            listaModelProdutosVendasprodutos = controllerProdutosVendasProdutos.getListaProdutosVendasProdutosController(codigoVenda);//recupera a venda no banco pelo código
            DefaultTableModel modelo = (DefaultTableModel) jtProdutosVenda.getModel();//Usa a tabela como modelo
            modelo.setNumRows(0);//zera a tabela
            int qtdProdTabela = listaModelProdutosVendasprodutos.size();//recupera a quantidade de produtos a ser preenchida na tabela na tabela

            preencherCodigoClientePeloCombobox();//preenche o código do cliente pelo combobox (usei isto para facilitar o preenchimento)
            double desconto = modelVendaAlteracao.getVenDesconto();//retorna o valor do desconto salvo no banco de dados
            double valorRecebido = modelVendaAlteracao.getVenValorRecebido();//retorna o valor recebido salvo no banco de dados

            jtfDesconto.setText(String.valueOf(desconto));//aplica o desconto no jtf
            jtfRecebido.setText(String.valueOf(valorRecebido));//aplica o valor recebido no jtf

            habilitarDesabilitarCampCliente(false);
            jtfCodigoProduto.requestFocus();
            jtfCodigoDaVenda.setText(String.valueOf(codigoVenda));//Seta no campo número da venda o id da venda já cadastrada
            for (int i = 0; i < qtdProdTabela; i++) {
                //Preenche a tabela com os produtos da venda.
                modelo.addRow(new Object[]{
                    listaModelProdutosVendasprodutos.get(i).getModelProdutos().getIdProduto(),//ID atual do produto no banco
                    listaModelProdutosVendasprodutos.get(i).getModelProdutos().getProNome(),//Nome Atual do produto no banco
                    listaModelProdutosVendasprodutos.get(i).getModelProdutosDaVenda().getVenProQuantidadeVendida(),//quantidade vendida na data da compra
                    listaModelProdutosVendasprodutos.get(i).getModelProdutosDaVenda().getVenProValor(),//valor vendido na data da compra
                    listaModelProdutosVendasprodutos.get(i).getModelProdutosDaVenda().getVenProQuantidadeVendida() * listaModelProdutosVendasprodutos.get(i).getModelProdutosDaVenda().getVenProValor()//quantidade * valor = total
                });
            }

            somarValorTotalProdutos(); //soma todos os produtos e preenche o JTF total
            //jtpViewVendas.setSelectedIndex(0); // MUDA PARA A GUIA PRINCIPAL DE VENDAS

        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Selecione a venda que deseja alterar", "ATENÇÃO - Erro 2708", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void adicionarProduto(int pCodigo) {
        int proCodigo = 0;
        try {

            proCodigo = Integer.parseInt(jtfCodigoProduto.getText());

        } catch (NumberFormatException e) {
            janelaSelecaoProduto();
        }
        if (pCodigo == proCodigo) {
            System.out.println("Os dois parametros são iguais");
            proCodigo = pCodigo;
        }

        modelProduto = new ModelProduto();

        modelProduto = controllerProdutos.retornarProdutoController(Integer.parseInt(proCodigo + ""));

        int quantidade = Integer.parseInt(jspQuantidade.getValue().toString());
        boolean salvou = false;

        if (jspQuantidade.getValue().equals("")) {
            JOptionPane.showMessageDialog(this, "Você precisa preencher a quantidade de produtos", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {

            DefaultTableModel modelo = (DefaultTableModel) jtProdutosVenda.getModel();

            try {

                /**
                 * Se a tabela estiver vazia ele adiciona o produto sem
                 * restrições
                 */
                if (modelo.getRowCount() <= 0) {
                    modelo.addRow(new Object[]{
                        modelProduto.getIdProduto(),
                        modelProduto.getProNome(),
                        quantidade,
                        modelProduto.getProValor(),
                        quantidade * modelProduto.getProValor()

                    });
                    salvou = true;
                    System.out.println("Não tinha linha, adicionando linha com produto 1");

                }
                /**
                 * Se a tabela NÃO estiver vazia, ele verifica se o produto
                 * existe
                 */
                if (!salvou) {
                    for (int i = 0; i < modelo.getRowCount(); i++) {

                        int produtoBuscado = Integer.parseInt(modelo.getValueAt(i, 0) + "");

                        /**
                         * Verifica se o produto existe na tabela, se ele
                         * existir incrementa, caso contrário ele continua no
                         * ELSE
                         */
                        if (produtoBuscado == proCodigo) {

                            int quantAtual = Integer.parseInt(modelo.getValueAt(i, 2) + "");
                            int quantNova = quantAtual + quantidade;
                            double preco = Double.parseDouble(modelo.getValueAt(i, 3) + "");
                            modelo.setValueAt(preco, i, 3);
                            modelo.setValueAt(quantNova, i, 2);
                            modelo.setValueAt(quantNova * preco, i, 4);

                            salvou = true;
                            System.out.println("Incrementou a quantidade de produtos ");

                        }

                    }
                    if (!salvou) {
                        modelo.addRow(new Object[]{
                            modelProduto.getIdProduto(),
                            modelProduto.getProNome(),
                            quantidade,
                            modelProduto.getProValor(),
                            quantidade * modelProduto.getProValor()

                        });
                        salvou = true;
                        System.out.println("Lista tem produtos mas o produto adicionado é novo, adicionando linha com produto 2");

                    }

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Produto não cadastrado, Selecione um produto na próxima janela", "Atenção", JOptionPane.WARNING_MESSAGE);
                janelaSelecaoProduto();

            }

            somarValorTotalProdutos();
        }
    }

    private void somarValorTotalProdutos() {

        double soma = 0, valorPro, total;
        int quantPro;
        int cont = jtProdutosVenda.getRowCount();

        //repete o comando for de soma enquanto ainda existir linhas na tabela
        for (int i = 0; i < cont; i++) {
            quantPro = (int) jtProdutosVenda.getValueAt(i, 2);
            valorPro = (double) jtProdutosVenda.getValueAt(i, 3);
            total = quantPro * valorPro;

            soma = soma + total;

        }

        jtfValorTotal.setText(String.valueOf(soma));
        aplicarDescontos();
        calcularTroco();

    }

    /**
     * Este comando aplica um desconto ao valor final da venda
     */
    private void aplicarDescontos() {

        try {
            double desconto = formatador.converterVirgulaParaPontoReturn((jtfDesconto.getText()));
            jtfValorTotal.setText(String.valueOf(
                    Double.parseDouble(jtfValorTotal.getText()) - desconto));
        } catch (NumberFormatException e) {

        }

    }

    private void calcularTroco() {

        double recebido, troco, total;
        total = Double.parseDouble(jtfValorTotal.getText());
        try {
            recebido = formatador.converterVirgulaParaPontoReturn((jtfRecebido.getText()));

        } catch (NumberFormatException e) {

            recebido = 0;
        }
        troco = recebido - total;
        if (troco >= 0) {
            jtfTroco.setText(String.valueOf("R$" + troco));

        } else {
            jtfTroco.setText(String.valueOf("R$ " + "0"));

        }
    }

    public void atualizarCampos(ModelCliente pModelCliente) {
        modelCliente = pModelCliente;
        jtfCidade.setText(modelCliente.getCliCidade());
        jtfDDDTelefone.setText(modelCliente.getCliDDDTel());
        jtfNomeDoCliente.setText(modelCliente.getCliNome());
        jtfEndereco.setText(modelCliente.getCliEndereco());
        jtfNumCasa.setText(modelCliente.getCliNumCasa());
        jtfBairro.setText(modelCliente.getCliBairro());
        jtfCidade.setText(modelCliente.getCliCidade());
        jtfEstado.setText(modelCliente.getCliUf());
        jtfTelefone.setText(modelCliente.getCliTelefone());
        jtfCodigoCliente.setText(modelCliente.getIdCliente() + "");
        jtfPontoDeReferencia.setText(modelCliente.getCliPontoReferencia());
    }

    public JTextField atualizarJtfTelefone(boolean pVisible) {
        jtfTelefone.setVisible(pVisible);
        return this.jtfTelefone;
    }

    public void autoCompletarPorTel(String Tel) {
        modelCliente = new ModelCliente();
        modelCliente = controllerCliente.getClienteTelefoneController(Tel);
        jtfCidade.setText(modelCliente.getCliCidade());
        jtfDDDTelefone.setText(modelCliente.getCliDDDTel());
        jtfNomeDoCliente.setText(modelCliente.getCliNome());
        jtfEndereco.setText(modelCliente.getCliEndereco());
        jtfNumCasa.setText(modelCliente.getCliNumCasa());
        jtfBairro.setText(modelCliente.getCliBairro());
        jtfCidade.setText(modelCliente.getCliCidade());
        jtfEstado.setText(modelCliente.getCliUf());
        jtfTelefone.setText(modelCliente.getCliTelefone());
        jtfCodigoCliente.setText(modelCliente.getIdCliente() + "");
        jtfPontoDeReferencia.setText(modelCliente.getCliPontoReferencia());
    }

    private boolean checarSeClienteExistePorTel(String pTel) {

//Checa se o número do cliente é 8 ou9 
        if (pTel.length() == 8 || pTel.length() == 9) {

            if (pTel.length() == 9) {

                String pTelCel = pTel.substring(1, 9);
                System.out.println(pTelCel);
                pTel = pTelCel;

            }

            System.out.println("O número do cliente tem 8 ou 9 caracteres corretamente");
            ModelCliente modelClienteX = controllerCliente.getClienteTelefoneController(pTel);
            if (modelClienteX.getCliTelefone() != null) {

                System.out.println("O Cliente existe, preenchendo dados");
                atualizarCampos(modelClienteX);
                return true;

            } else {
                telefoneInexistente();
                System.out.println("O Cliente não existe no banco, telefone deu null");
                return false;
            }

        }
        return false;

    }

    private void editarLinhaDaTabela() {
        int linhaSelecionada = jtProdutosVenda.getSelectedRow();//linha selecionada na tabela
        int quant = (int) jtProdutosVenda.getValueAt(linhaSelecionada, 2);
        double valor = (double) jtProdutosVenda.getValueAt(linhaSelecionada, 3);
        DefaultTableModel modelo = (DefaultTableModel) jtProdutosVenda.getModel();
        double total = quant * valor;
        modelo.setValueAt(total, linhaSelecionada, 4);

    }

    public void habilitarDesabilitarCambos(boolean status) {

        habilitarDesabilitarCampCliente(status);

        btSalvarPedido.setVisible(status);
        jtfCodigoProduto.setEnabled(status);
        jspQuantidade.setEnabled(status);
        jtfRecebido.setEnabled(status);
        jtfDesconto.setEnabled(status);
        jtfTroco.setEnabled(status);

        statusEdicao = status; //Controla quando os botões vão funcionar ou não.

    }

    public void limparFormulario() {
        jtfNomeDoCliente.setText("");
        jtfCodigoCliente.setText("");
        jtfCodigoProduto.setText("");
        jtfRecebido.setText("");
        jtfDesconto.setText("");
        jtfTroco.setText("");
        jtfEndereco.setText("");
        jtfBairro.setText("");
        jtfCidade.setText("");
        jtfNumCasa.setText("");
        jtfEstado.setText("");
        jtfDDDTelefone.setText("");
        jtfPontoDeReferencia.setText("");
        jtfValorTotal.setText("");
        jtfCodigoDaVenda.setText("");
        DefaultTableModel modelo = (DefaultTableModel) jtProdutosVenda.getModel();
        //Limpa tabela antes de carregar
        modelo.setNumRows(0);
    }

//    private void listarClientesInicial() {
//        listaModelClientes = controllerCliente.getListaClienteController();
//        //listaModelClientes.sort(this);
//        jcbNomeCliente.removeAllItems();
//        for (int i = 0; i < listaModelClientes.size(); i++) {
//            jcbNomeCliente.addItem(listaModelClientes.get(i).getCliNome());
//        }
//    }
    private void listarProdutos() {
        listaModelProdutos = controllerProdutos.retornarListaProdutoController();

    }

    private void preencherCodigoClientePeloCombobox() {
        modelCliente = new ModelCliente();
        if (jtfNomeDoCliente.getText().length() > 6) {

            try {
                modelCliente = controllerCliente.getClienteController(jtfNomeDoCliente.getText());

            } catch (NullPointerException e) {
            }
            atualizarCampos(this.modelCliente);

        }
    }

    private void preencherCodigoProdutoPeloCombobox() {
        modelProduto = controllerProdutos.retornarProdutoController(listaModelProdutos.get(0).getIdProduto());
        jtfCodigoProduto.setText(String.valueOf(modelProduto.getIdProduto()));

    }

    private void telefoneInexistente() {
        jtfBairro.setText("");
        jtfCidade.setText("");
        jtfCodigoCliente.setText("");
        jtfEstado.setText("");
        jtfNumCasa.setText("");
        jtfEndereco.setText("");
        jtfNomeDoCliente.setText("");
    }

    /**
     * Serve para remover um produto do estoque caso ele seja vendido 1 =
     * adicionar, 0 = remover.
     *
     * @param pModelProduto
     */
    private void atualizarEstoqueProduto(ModelProduto pModelProduto, int add0Remover1) {
        ModelProduto modelProdutoEstoque = new ModelProduto();

        if (pModelProduto.getProQuantidadeVendida() > 0) {

            if (add0Remover1 == 0) {
                modelProdutoEstoque = controllerProdutos.retornarProdutoController(pModelProduto.getIdProduto());
                int estoqueAtual = modelProdutoEstoque.getProEstoque();
                int adicionar = estoqueAtual + pModelProduto.getProQuantidadeVendida();
                modelProdutoEstoque.setProEstoque(adicionar);
                controllerProdutos.alterarEstoqueProdutoController(modelProdutoEstoque);

            }
            if (add0Remover1 == 1) {
                modelProdutoEstoque = controllerProdutos.retornarProdutoController(pModelProduto.getIdProduto());
                int estoqueAtual = modelProdutoEstoque.getProEstoque();
                int adicionar = estoqueAtual - pModelProduto.getProQuantidadeVendida();
                modelProdutoEstoque.setProEstoque(adicionar);
                controllerProdutos.alterarEstoqueProdutoController(modelProdutoEstoque);
            }
        }

    }

    public DefaultTableModel retornarModeloDaTabela() {
        return (DefaultTableModel) jtProdutosVenda.getModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpPlanoDeFundo = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtfCodigoDaVenda = new javax.swing.JTextField();
        btNovaVenda = new componentes.UJPanelImagem();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jtfDDDTelefone = new componentes.UJTextField();
        jLabel20 = new javax.swing.JLabel();
        jtfTelefone = new javax.swing.JTextField();
        uJPanelImagem7 = new componentes.UJPanelImagem();
        jPanel28 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jtfNumCasa = new componentes.UJTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtfNomeDoCliente = new componentes.UJTextField();
        jPanel15 = new javax.swing.JPanel();
        jtfEndereco = new componentes.UJTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jtfBairro = new componentes.UJTextField();
        jLabel17 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jtfCidade = new componentes.UJTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jtfEstado = new componentes.UJTextField();
        jPanel21 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfCodigoCliente = new javax.swing.JTextField();
        btNovoCliente = new componentes.UJPanelImagem();
        jPanel25 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jtfPontoDeReferencia = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jspQuantidade = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jtfCodigoProduto = new javax.swing.JTextField();
        btAddProduto = new componentes.UJPanelImagem();
        btPesquisarProduto = new componentes.UJPanelImagem();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProdutosVenda = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jtfValorTotal = new javax.swing.JTextField();
        jbRemoverProdutos = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jtfRecebido = new javax.swing.JTextField();
        jtfDesconto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtfTroco = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btSalvarPedido = new componentes.UJPanelImagem();
        btCancelarPedido = new componentes.UJPanelImagem();
        jbtVendasRealizadas = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        barraDeMover = new javax.swing.JPanel();
        uJPanelImagem11 = new componentes.UJPanelImagem();
        uJPanelImagem1 = new componentes.UJPanelImagem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Pedidos");
        setUndecorated(true);

        jpPlanoDeFundo.setBackground(new java.awt.Color(119, 187, 153));
        jpPlanoDeFundo.setAutoscrolls(true);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Numero do Pedido");

        jtfCodigoDaVenda.setEditable(false);
        jtfCodigoDaVenda.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfCodigoDaVenda.setText("---");
        jtfCodigoDaVenda.setBorder(null);
        jtfCodigoDaVenda.setEnabled(false);

        btNovaVenda.setImagem(new java.io.File("C:\\SSDprojeto\\src\\imagens\\Fundos\\btNovaVenda.png"));
        btNovaVenda.setPreferredSize(new java.awt.Dimension(120, 25));
        btNovaVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btNovaVendaMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout btNovaVendaLayout = new javax.swing.GroupLayout(btNovaVenda);
        btNovaVenda.setLayout(btNovaVendaLayout);
        btNovaVendaLayout.setHorizontalGroup(
            btNovaVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        btNovaVendaLayout.setVerticalGroup(
            btNovaVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btNovaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfCodigoDaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jtfCodigoDaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btNovaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel11.setPreferredSize(new java.awt.Dimension(427, 160));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setText("DDD");

        jtfDDDTelefone.setText("31");
        jtfDDDTelefone.setToolTipText("");
        jtfDDDTelefone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfDDDTelefoneKeyReleased(evt);
            }
        });

        jLabel20.setText("Telefone");

        jtfTelefone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfTelefoneKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel21)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel20))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jtfDDDTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jtfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20))
                .addGap(3, 3, 3)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfDDDTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        uJPanelImagem7.setImagem(new java.io.File("C:\\SSDprojeto\\src\\imagens\\Fundos\\btLupa.png"));
        uJPanelImagem7.setPreferredSize(new java.awt.Dimension(25, 25));

        javax.swing.GroupLayout uJPanelImagem7Layout = new javax.swing.GroupLayout(uJPanelImagem7);
        uJPanelImagem7.setLayout(uJPanelImagem7Layout);
        uJPanelImagem7Layout.setHorizontalGroup(
            uJPanelImagem7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        uJPanelImagem7Layout.setVerticalGroup(
            uJPanelImagem7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uJPanelImagem7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(uJPanelImagem7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setAutoscrolls(true);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setText("Nrº e Complemento");

        jtfNumCasa.setEditable(false);
        jtfNumCasa.setEnabled(false);
        jtfNumCasa.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 99, Short.MAX_VALUE))
            .addComponent(jtfNumCasa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfNumCasa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Nome Cliente");

        jtfNomeDoCliente.setEditable(false);
        jtfNomeDoCliente.setEnabled(false);
        jtfNomeDoCliente.setPreferredSize(new java.awt.Dimension(6, 19));
        jtfNomeDoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfNomeDoClienteKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addContainerGap(109, Short.MAX_VALUE))
            .addComponent(jtfNomeDoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtfNomeDoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jtfEndereco.setEditable(false);
        jtfEndereco.setEnabled(false);
        jtfEndereco.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        jLabel10.setText("Rua / Av");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addContainerGap(109, Short.MAX_VALUE))
                    .addComponent(jtfEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(1, 1, 1))
        );

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jtfBairro.setEditable(false);
        jtfBairro.setEnabled(false);
        jtfBairro.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        jLabel17.setText("Bairro");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addGap(0, 96, Short.MAX_VALUE))
            .addComponent(jtfBairro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setText("Cidade");

        jtfCidade.setEditable(false);
        jtfCidade.setEnabled(false);
        jtfCidade.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addContainerGap(106, Short.MAX_VALUE))
            .addComponent(jtfCidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setText("UF");

        jtfEstado.setEditable(false);
        jtfEstado.setEnabled(false);
        jtfEstado.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jtfEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                .addGap(1, 1, 1))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Código Cli");

        jtfCodigoCliente.setEditable(false);
        jtfCodigoCliente.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jtfCodigoCliente.setEnabled(false);
        jtfCodigoCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfCodigoClienteFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
            .addComponent(jtfCodigoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btNovoCliente.setImagem(new java.io.File("C:\\SSDprojeto\\src\\imagens\\Fundos\\btNovoCLiente.png"));
        btNovoCliente.setPreferredSize(new java.awt.Dimension(120, 25));
        btNovoCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btNovoClienteMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout btNovoClienteLayout = new javax.swing.GroupLayout(btNovoCliente);
        btNovoCliente.setLayout(btNovoClienteLayout);
        btNovoClienteLayout.setHorizontalGroup(
            btNovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        btNovoClienteLayout.setVerticalGroup(
            btNovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Ponto de referência:");

        jtfPontoDeReferencia.setEditable(false);
        jtfPontoDeReferencia.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jtfPontoDeReferencia.setEnabled(false);
        jtfPontoDeReferencia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfPontoDeReferenciaFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5)
            .addComponent(jtfPontoDeReferencia)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfPontoDeReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButton2.setText("Alterar Cliente");
        jButton2.setPreferredSize(new java.awt.Dimension(120, 25));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(btNovoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btNovoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));

        jspQuantidade.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jspQuantidade.setEnabled(false);
        jspQuantidade.setValue(1);
        jspQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jspQuantidadeKeyPressed(evt);
            }
        });

        jLabel13.setText("Quantidade:");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jspQuantidade)
            .addComponent(jLabel13)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("Cod. Prod.");

        jtfCodigoProduto.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jtfCodigoProduto.setEnabled(false);
        jtfCodigoProduto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfCodigoProdutoFocusLost(evt);
            }
        });
        jtfCodigoProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtfCodigoProdutoMouseClicked(evt);
            }
        });
        jtfCodigoProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfCodigoProdutoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addContainerGap(48, Short.MAX_VALUE))
            .addComponent(jtfCodigoProduto)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btAddProduto.setImagem(new java.io.File("C:\\SSDprojeto\\src\\imagens\\Fundos\\btAddProduto.png"));
        btAddProduto.setPreferredSize(new java.awt.Dimension(120, 25));
        btAddProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btAddProdutoMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout btAddProdutoLayout = new javax.swing.GroupLayout(btAddProduto);
        btAddProduto.setLayout(btAddProdutoLayout);
        btAddProdutoLayout.setHorizontalGroup(
            btAddProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        btAddProdutoLayout.setVerticalGroup(
            btAddProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        btPesquisarProduto.setImagem(new java.io.File("C:\\SSDprojeto\\src\\imagens\\Fundos\\btPesquisarProduto.png"));
        btPesquisarProduto.setPreferredSize(new java.awt.Dimension(120, 25));
        btPesquisarProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btPesquisarProdutoMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout btPesquisarProdutoLayout = new javax.swing.GroupLayout(btPesquisarProduto);
        btPesquisarProduto.setLayout(btPesquisarProdutoLayout);
        btPesquisarProdutoLayout.setHorizontalGroup(
            btPesquisarProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        btPesquisarProdutoLayout.setVerticalGroup(
            btPesquisarProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btAddProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btPesquisarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btPesquisarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btAddProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jtProdutosVenda.setAutoCreateRowSorter(true);
        jtProdutosVenda.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jtProdutosVenda.setFont(new java.awt.Font("Arial", 0, 8)); // NOI18N
        jtProdutosVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod. Prod.", "Nome Produto", "Quant.", "Valor Un.", "Valor Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtProdutosVenda.setGridColor(new java.awt.Color(255, 255, 255));
        jtProdutosVenda.setMinimumSize(new java.awt.Dimension(0, 0));
        jtProdutosVenda.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jtProdutosVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtProdutosVendaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtProdutosVenda);

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText(" Valor total");
        jLabel6.setOpaque(true);

        jtfValorTotal.setEditable(false);
        jtfValorTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfValorTotal.setForeground(new java.awt.Color(13, 143, 57));
        jtfValorTotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtfValorTotal.setEnabled(false);

        jbRemoverProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/iconfinder_trashcan_299071.png"))); // NOI18N
        jbRemoverProdutos.setText("Remover Produto");
        jbRemoverProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoverProdutosActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Valor recebido:");

        jtfRecebido.setEnabled(false);
        jtfRecebido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfRecebidoKeyReleased(evt);
            }
        });

        jtfDesconto.setEnabled(false);
        jtfDesconto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfDescontoFocusLost(evt);
            }
        });
        jtfDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfDescontoKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Desconto:");

        jtfTroco.setEditable(false);
        jtfTroco.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfTroco.setForeground(new java.awt.Color(13, 143, 57));
        jtfTroco.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Troco:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jtfDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(jtfTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfTroco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jbRemoverProdutos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbRemoverProdutos)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        btSalvarPedido.setToolTipText("");
        btSalvarPedido.setImagem(new java.io.File("C:\\SSDprojeto\\src\\imagens\\Fundos\\btSalvarPedido.png"));
        btSalvarPedido.setPreferredSize(new java.awt.Dimension(120, 25));
        btSalvarPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btSalvarPedidoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btSalvarPedidoMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout btSalvarPedidoLayout = new javax.swing.GroupLayout(btSalvarPedido);
        btSalvarPedido.setLayout(btSalvarPedidoLayout);
        btSalvarPedidoLayout.setHorizontalGroup(
            btSalvarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        btSalvarPedidoLayout.setVerticalGroup(
            btSalvarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        btCancelarPedido.setImagem(new java.io.File("C:\\SSDprojeto\\src\\imagens\\Fundos\\btCancelarPedido.png"));
        btCancelarPedido.setPreferredSize(new java.awt.Dimension(120, 25));
        btCancelarPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btCancelarPedidoMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout btCancelarPedidoLayout = new javax.swing.GroupLayout(btCancelarPedido);
        btCancelarPedido.setLayout(btCancelarPedidoLayout);
        btCancelarPedidoLayout.setHorizontalGroup(
            btCancelarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        btCancelarPedidoLayout.setVerticalGroup(
            btCancelarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        jbtVendasRealizadas.setText("Pedidos Realizados");
        jbtVendasRealizadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtVendasRealizadasActionPerformed(evt);
            }
        });

        jButton1.setText("Salvar e imprimir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("F5 - Salvar Pedido         F6 - Salvar e Imprimir Pedido  ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(jbtVendasRealizadas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btCancelarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btSalvarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbtVendasRealizadas)
                        .addComponent(jButton1))
                    .addComponent(btCancelarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSalvarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        barraDeMover.setBackground(new java.awt.Color(119, 187, 153));
        barraDeMover.setPreferredSize(new java.awt.Dimension(515, 23));
        barraDeMover.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                barraDeMoverMouseDragged(evt);
            }
        });
        barraDeMover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                barraDeMoverMousePressed(evt);
            }
        });

        uJPanelImagem11.setImagem(new java.io.File("C:\\SSDprojeto\\src\\imagens\\Fundos\\btMinimizar.png"));
        uJPanelImagem11.setMinimumSize(new java.awt.Dimension(24, 24));
        uJPanelImagem11.setPreferredSize(new java.awt.Dimension(24, 24));
        uJPanelImagem11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                uJPanelImagem11MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout uJPanelImagem11Layout = new javax.swing.GroupLayout(uJPanelImagem11);
        uJPanelImagem11.setLayout(uJPanelImagem11Layout);
        uJPanelImagem11Layout.setHorizontalGroup(
            uJPanelImagem11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        uJPanelImagem11Layout.setVerticalGroup(
            uJPanelImagem11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        uJPanelImagem1.setImagem(new java.io.File("C:\\SSDprojeto\\src\\imagens\\Fundos\\btFecharJanela.png"));
        uJPanelImagem1.setMinimumSize(new java.awt.Dimension(24, 24));
        uJPanelImagem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                uJPanelImagem1MouseReleased(evt);
            }
        });
        uJPanelImagem1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                uJPanelImagem1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout uJPanelImagem1Layout = new javax.swing.GroupLayout(uJPanelImagem1);
        uJPanelImagem1.setLayout(uJPanelImagem1Layout);
        uJPanelImagem1Layout.setHorizontalGroup(
            uJPanelImagem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        uJPanelImagem1Layout.setVerticalGroup(
            uJPanelImagem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout barraDeMoverLayout = new javax.swing.GroupLayout(barraDeMover);
        barraDeMover.setLayout(barraDeMoverLayout);
        barraDeMoverLayout.setHorizontalGroup(
            barraDeMoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraDeMoverLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(uJPanelImagem11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(uJPanelImagem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        barraDeMoverLayout.setVerticalGroup(
            barraDeMoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraDeMoverLayout.createSequentialGroup()
                .addGroup(barraDeMoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uJPanelImagem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uJPanelImagem11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jpPlanoDeFundoLayout = new javax.swing.GroupLayout(jpPlanoDeFundo);
        jpPlanoDeFundo.setLayout(jpPlanoDeFundoLayout);
        jpPlanoDeFundoLayout.setHorizontalGroup(
            jpPlanoDeFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPlanoDeFundoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPlanoDeFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(barraDeMover, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpPlanoDeFundoLayout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpPlanoDeFundoLayout.setVerticalGroup(
            jpPlanoDeFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPlanoDeFundoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(barraDeMover, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPlanoDeFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPlanoDeFundoLayout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPlanoDeFundo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jpPlanoDeFundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfRecebidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfRecebidoKeyReleased
        // TODO add your handling code here:
        somarValorTotalProdutos();
    }//GEN-LAST:event_jtfRecebidoKeyReleased

    private void jtfDescontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfDescontoFocusLost
        //Ao perder o foco o JtfDesconto vai executar o seguinte comando
        somarValorTotalProdutos();
    }//GEN-LAST:event_jtfDescontoFocusLost

    private void jtfDescontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfDescontoKeyReleased
        // TODO add your handling code here:
        somarValorTotalProdutos();
    }//GEN-LAST:event_jtfDescontoKeyReleased

    private void jtfTelefoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfTelefoneKeyReleased
        // TODO add your handling code here:   // TODO add your handling code here:

        try {

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                //O Próximo If verifica se o Cliente está cadstrado no banco de dados
                if (checarSeClienteExistePorTel(jtfTelefone.getText())) {
                    habilitarDesabilitarCambos(true);

                    jtfCodigoProduto.requestFocus();
                    jtfCodigoProduto.selectAll();
                } else {

                    if (jtfTelefone.getText().length() == 9 || jtfTelefone.getText().length() == 8) {
                        int intNovo = JOptionPane.showConfirmDialog(this, "O cliente não localizado com este número. \n Deseja Cadastrar um novo Cliente?", "Atenção", JOptionPane.YES_NO_OPTION);
                        if (intNovo == 0) {
                            try {
                                viewCliente.novoCliente("salvar", jtfTelefone.getText(), jtfDDDTelefone.getText());
                                viewCliente.setVisible(true);
                                viewCliente.setViewVendasFrame(this);

                            } catch (Exception e) {
                                viewCliente = new ViewClienteFinal();
                                viewCliente.novoCliente("salvar", jtfTelefone.getText(), jtfDDDTelefone.getText());
                                viewCliente.setVisible(true);
                                viewCliente.setViewVendasFrame(this);
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "O telefone deve conter 8 ou 9 caracteres, pois só aceitamos celulares e fixos do Brasil. ", "Erro - Código 1714", JOptionPane.ERROR_MESSAGE);

                    }

                }

            }

        } catch (NullPointerException e) {
            e.printStackTrace();

        }


    }//GEN-LAST:event_jtfTelefoneKeyReleased

    private void jtfCodigoClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCodigoClienteFocusLost
        // TODO add your handling code here:
        modelCliente = controllerCliente.getClienteController(Integer.parseInt(jtfCodigoCliente.getText()));
        jtfNomeDoCliente.setText(modelCliente.getCliNome());
    }//GEN-LAST:event_jtfCodigoClienteFocusLost

    private void jspQuantidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jspQuantidadeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            adicionarProduto(Integer.parseInt(jtfCodigoProduto.getText()));
        }
    }//GEN-LAST:event_jspQuantidadeKeyPressed

    private void jtfCodigoProdutoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCodigoProdutoFocusLost

    }//GEN-LAST:event_jtfCodigoProdutoFocusLost

    private void jtfCodigoProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfCodigoProdutoMouseClicked
        // TODO add your handling code here:

        jtfCodigoProduto.setSelectionStart(0);
        jtfCodigoProduto.setSelectionEnd(100);
    }//GEN-LAST:event_jtfCodigoProdutoMouseClicked

    private void jtfCodigoProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfCodigoProdutoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (statusEdicao) {
                try {
                    if (!jtfCodigoProduto.getText().equals("")) {
                        adicionarProduto(Integer.parseInt(jtfCodigoProduto.getText()));
                    }
                    jtfCodigoProduto.setText("");

                } catch (Exception e) {
                    preencherCodigoProdutoPeloCombobox();
                    janelaSelecaoProduto();
                }
            }
        }
    }//GEN-LAST:event_jtfCodigoProdutoKeyPressed

    private void jtProdutosVendaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtProdutosVendaKeyReleased
        // TODO add your handling code here:

        editarLinhaDaTabela();
        somarValorTotalProdutos();
    }//GEN-LAST:event_jtProdutosVendaKeyReleased

    private void jbRemoverProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRemoverProdutosActionPerformed
        // TODO add your handling code here:
        try {
            int linha = jtProdutosVenda.getSelectedRow();//linha será igual a linha selecionada na tabela
            DefaultTableModel modelo = (DefaultTableModel) jtProdutosVenda.getModel();//cria uma instancia nova da tabela de vendas
            modelo.removeRow(linha);//comando que remove a linha selecionada na tabela
            somarValorTotalProdutos();//Soma novamente o total após excluir itens
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Erro deletar, selecione uma linha. Se o problema persistir cunsulte a SSD e informe o código de erro ", "Erro - Código 1714", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbRemoverProdutosActionPerformed


    private void uJPanelImagem1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_uJPanelImagem1KeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_uJPanelImagem1KeyReleased

    private void btNovaVendaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btNovaVendaMouseReleased
        // TODO add your handling code here:
        iniciarNovaVenda();
    }//GEN-LAST:event_btNovaVendaMouseReleased

    private void btNovoClienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btNovoClienteMouseReleased
        // TODO add your handling code here:
        String telefone = jtfTelefone.getText();
        String ddd = jtfDDDTelefone.getText();

        try {
            viewCliente.setViewVendasFrame(this);
            viewCliente.novoCliente("salvar", telefone, ddd);
            viewCliente.setVisible(true);

        } catch (Exception e) {
            viewCliente.setViewVendasFrame(this);
            viewCliente = new ViewClienteFinal();
            viewCliente.novoCliente("salvar", telefone, ddd);
            viewCliente.setVisible(true);
        }

    }//GEN-LAST:event_btNovoClienteMouseReleased


    private void btAddProdutoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAddProdutoMouseReleased

        //Condição que controla quando o botão vai funcionar
        boolean status = false;

        if (jtfTelefone.getText().equals("")) {
            jtfTelefone.requestFocus();
            status = true;
        }
        if (jtfBairro.getText().equals("")) {
            jtfBairro.requestFocus();
            status = true;
        }
        if (jtfNomeDoCliente.getText().equals("")) {
            jtfNomeDoCliente.requestFocus();
            status = true;
        }
        if (jtfEndereco.getText().equals("")) {
            jtfEndereco.requestFocus();
            status = true;
        }
        if (status) {
            //caso não for nenhum desses casos 
            JOptionPane.showMessageDialog(this, "Voce precisa preencher os dados do cliente antes de ralizar o pedido", "Atenção", JOptionPane.WARNING_MESSAGE);

        }

        try {
            if (statusEdicao) {
                adicionarProduto(Integer.parseInt(jtfCodigoProduto.getText()));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Produto não cadastrado, Selecione um produto na próxima janela", "Atenção", JOptionPane.WARNING_MESSAGE);
            janelaSelecaoProduto();

        }

    }//GEN-LAST:event_btAddProdutoMouseReleased

    private void btCancelarPedidoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCancelarPedidoMouseReleased
        // TODO add your handling code here:
        habilitarDesabilitarCambos(false);
        limparFormulario();
    }//GEN-LAST:event_btCancelarPedidoMouseReleased

    private void btSalvarPedidoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSalvarPedidoMouseReleased

        listaModelProdutos = new ArrayList<>();//reseta o listaModelProdutos para não receber lixos de outras vendas
        modelVendasProdutos = new ModelProdutosDaVenda();//Cria um novo ModelVendas para não ficar nehum lixo das vendas anteriores
        listaModelVendasProdutos = new ArrayList<>();//reseta o listaModelVendasProdutos para não receber lixos de outras vendas

        //Condição que controla quando o botão vai funcionar
        if (statusEdicao) {

            btSalvarPedido.setSize(btSalvarPedido.getWidth() + 3, btSalvarPedido.getHeight() + 3);
            int codigoVenda = 0;
            double desconto;
            double valorRecebido;
            //Tratamento do desconto, se o desconto estiver vazio ele vai setar zero.
            if (jtfDesconto.getText().isEmpty()) {
                desconto = 0;
            } else {
                try {
                    desconto = formatador.converterVirgulaParaPontoReturn((jtfDesconto.getText()));

                } catch (NumberFormatException e) {
                    try {
                        desconto = Double.parseDouble(JOptionPane.showInputDialog(this, "Preencha o desconto apenas com números simples. \n Ex: \n 10.00 (Dez reais) \n 3.50 (Três reais e cinquenta centavos) ", "Valor do desconto Invalido", WIDTH));

                    } catch (NumberFormatException x) {
                        JOptionPane.showMessageDialog(this, "O Valor preenchido está errado, será concedido R$0.00 de desconto");
                        desconto = 0;
                    }
                }

            }

            //Tratamento do desconto, se o desconto estiver vazio ele vai setar zero.
            if (jtfRecebido.getText().isEmpty()) {
                valorRecebido = 0;
            } else {
                try {
                    valorRecebido = formatador.converterVirgulaParaPontoReturn((jtfRecebido.getText()));

                } catch (NumberFormatException e) {
                    try {
                        valorRecebido = Double.parseDouble(JOptionPane.showInputDialog(this, "Preencha o valor recebido apenas com números simples. \n Ex: \n 10.00 (Dez reais) \n 3.50 (Três reais e cinquenta centavos) ", "Valor recebido Invalido", WIDTH));

                    } catch (NumberFormatException x) {
                        JOptionPane.showMessageDialog(this, "O Valor recebido está errado, será recebido R$00.00");
                        valorRecebido = 0.00;
                    }
                }

            }

            /**
             * O próximo if serve para escolher quando o evento vai salvar ou
             * alterar a venda SALVAR
             */
            if (alterarSalvar.equals("salvar")) {
                /**
                 * Preencher o listaModelProdutos com os produtos que foram
                 * vendidos, os produtos que estão na tabele jtableProdutosVenda
                 */
                for (int i = 0; i < jtProdutosVenda.getRowCount(); i++) {
                    modelProduto = new ModelProduto();//reseta o modelProduto para não receber lixos de outras vendas
                    modelProduto = controllerProdutos.retornarProdutoController(String.valueOf(jtProdutosVenda.getValueAt(i, 1)));
                    modelProduto.setProValor(Double.parseDouble(jtProdutosVenda.getValueAt(i, 3) + ""));
                    modelProduto.setProQuantidadeVendida(Integer.parseInt(jtProdutosVenda.getValueAt(i, 2) + ""));

//Recupera do banco de dados o estoque atual e atribui ao produto
                    modelProduto.setProEstoque(
                            controllerProdutos.retornarProdutoController(modelProduto.getIdProduto()).getProEstoque()
                            - modelProduto.getProQuantidadeVendida());
                    listaModelProdutos.add(modelProduto);

                }

                /**
                 * Cria um novo ModelVenda
                 */
                int codigoCliente = Integer.parseInt(jtfCodigoCliente.getText());
                Double valorLiquido = Double.parseDouble(jtfValorTotal.getText());
                Double valorBruto = Double.parseDouble(jtfValorTotal.getText()) + desconto;
                try {
                    Date dataVenda = blDatas.converterDataParaDateUS(new java.util.Date(System.currentTimeMillis()));
                    modelVenda = new ModelVendas(listaModelProdutos, codigoCliente, valorLiquido, valorBruto, desconto, dataVenda);
                    modelVenda.setVenValorRecebido(valorRecebido);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Salva a venda no banco de dados e retorna a o id da venda que foi salva.
                codigoVenda = controllerVendas.salvarVendasController(modelVenda);
                if (codigoVenda > 0) {
                    modelVenda.setIdVenda(codigoVenda);

                    //se o produto for cadastrado salvo no banco vai executar o código deste if
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar venda, cunsulta a SSD e informe o código de erro 12091014", "Erro", JOptionPane.ERROR_MESSAGE);
                }

                /**
                 * Atributos: fkProduto, fkVenda,valorUnitário,
                 * QuantidadeVendida
                 */
                for (int i = 0; i < listaModelProdutos.size(); i++) {
                    modelVendasProdutos = new ModelProdutosDaVenda();
                    modelVendasProdutos.setProduto(listaModelProdutos.get(i).getIdProduto());
                    modelVendasProdutos.setIdDaVenda(modelVenda.getIdVenda());
                    modelVendasProdutos.setVenProValor(listaModelProdutos.get(i).getProValor());//Busca o valor unitário do produto na tabela jtProdutos
                    modelVendasProdutos.setVenProQuantidadeVendida(listaModelProdutos.get(i).getProQuantidadeVendida());//Busca a quantidade de produtos que foi vendida
                    modelVendasProdutos.setNomeProduto(listaModelProdutos.get(i).getProNome());
                    listaModelVendasProdutos.add(modelVendasProdutos);

                }

                //Salvar lista de protudos da vendas no banco de dados
                if (controllerVendasProdutos.salvarVendasProdutosController(listaModelVendasProdutos)) {
                    //Atualiza o estoque após efetivar avenda
                    controllerProdutos.alterarEstoqueProdutoController(listaModelProdutos);
                    limparFormulario();
                    habilitarDesabilitarCambos(false);
                    jtfRecebido.setText("0");//zera o valor recebido quando clica em alterar venda

                    /**
                     * Fecha a janela atual e abre uma janela nova exibindo os
                     * pedidos que foram realizados.
                     */
                    try {
                        viewVisualizarVendas.carregarVendas(modelVenda.getIdVenda() + "");
                        viewVisualizarVendas.marcarUltimaVenda();
                        viewVisualizarVendas.setVisible(true);
                    } catch (Exception e) {
                        viewVisualizarVendas = new ViewVisualizarVendas();
                        viewVisualizarVendas.carregarVendas(modelVenda.getIdVenda() + "");
                        viewVisualizarVendas.marcarUltimaVenda();
                        viewVisualizarVendas.setVisible(true);
                    }

                    JOptionPane.showMessageDialog(this, "Venda salva com sucesso", "Obrigado", JOptionPane.INFORMATION_MESSAGE);

//---------------------------imprimir cupom não fiscal-------------
                    //imprimirCupom(listaModelVendasProdutos, modelVenda);
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar produtos, cunsulta a SSD e informe o código de erro VV-SPV", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } else {

                /**
                 * ---------ALTERAR----------- Quando a opção selecionada for
                 * ALTERAR ele vai fazer isto: O comando tem 5 etapas 1 - Voltar
                 * os produtos para o estoque 2 - Remover os produtos
                 * relacionados a venda 3 - Atualizar o valor do pedido 4 -
                 * salvar os novos produtos relacionados a venda 5 - atualizar
                 * novamente o estoque
                 */
                try {
                    codigoVenda = Integer.parseInt(jtfCodigoDaVenda.getText());

                } catch (NumberFormatException e) {
                    codigoVenda = Integer.parseInt(JOptionPane.showInputDialog(this, "A Venda que está sendo alterada não pode ser confirmada, insira o id da venda"));

                }

                modelVenda = controllerVendas.getVendasController(codigoVenda);

                if (modelVenda == null) {
                    JOptionPane.showMessageDialog(this, "Cancelando alteração, o número informado é invalido", "Erro", JOptionPane.ERROR_MESSAGE);
                    limparFormulario();
                    habilitarDesabilitarCambos(false);
                }
                //---------------------------ETAPA 1---------------------------------
                /**
                 * Preencher o listaModelProdutos com os produtos que foram
                 * vendidos, os produtos que estão na tabele jtableProdutosVenda
                 */
                ArrayList<ModelProdutosDaVenda> listaDeProdutosDaVenda = controllerVendasProdutos.getListaDeProdutosDaVendaController(modelVenda.getIdVenda());
                for (int i = 0; i < listaDeProdutosDaVenda.size(); i++) {

                    modelProduto = new ModelProduto();//reseta o modelProduto para não receber lixos de outras vendas
                    modelProduto = controllerProdutos.retornarProdutoController(listaDeProdutosDaVenda.get(i).getProduto());
                    modelProduto.setProValor(listaDeProdutosDaVenda.get(i).getVenProValor());
                    modelProduto.setProQuantidadeVendida(listaDeProdutosDaVenda.get(i).getVenProQuantidadeVendida());
                    //Recupera do banco de dados o estoque atual e atribui ao produto
                    modelProduto.setProEstoque(
                            controllerProdutos.retornarProdutoController(modelProduto.getIdProduto()).getProEstoque()
                            + modelProduto.getProQuantidadeVendida());
                    listaModelProdutos.add(modelProduto);

                }

                //Voltar para estoque todos os produtos que foram vendidos.
                controllerProdutos.alterarEstoqueProdutoController(listaModelProdutos);

                //---------------------------ETAPA 2---------------------------------
                //Exclui os produtos relacionados ao pedido da tabela vendas_Produtos no banco de dados.
                if (!controllerVendasProdutos.excluirVendasProdutosController(codigoVenda)) {
                    // Se os produtos da venda não forem excluídas ele executa esta mensagem
                    JOptionPane.showMessageDialog(this, "Erro ao modificar o estoque dos produtos, consulte o código de erro 13091355", "Erro", JOptionPane.ERROR_MESSAGE);
                    limparFormulario();
                    habilitarDesabilitarCambos(false);
                } else {

                }
                //---------------------------ETAPA 3---------------------------------
                listaModelProdutos = new ArrayList<>();
                /**
                 * Preencher o listaModelProdutos com os produtos que foram
                 * vendidos, os produtos que estão na tabele jtableProdutosVenda
                 */
                for (int i = 0; i < jtProdutosVenda.getRowCount(); i++) {

                    modelProduto = new ModelProduto();//reseta o modelProduto para não receber lixos de outras vendas
                    modelProduto = controllerProdutos.retornarProdutoController(String.valueOf(jtProdutosVenda.getValueAt(i, 1)));
                    modelProduto.setProValor(Double.parseDouble(jtProdutosVenda.getValueAt(i, 3) + ""));
                    modelProduto.setProQuantidadeVendida(Integer.parseInt(jtProdutosVenda.getValueAt(i, 2) + ""));
                    //Recupera do banco de dados o estoque atual e atribui ao produto
                    modelProduto.setProEstoque(
                            controllerProdutos.retornarProdutoController(modelProduto.getIdProduto()).getProEstoque()
                            - modelProduto.getProQuantidadeVendida());
                    listaModelProdutos.add(modelProduto);

                }
                /**
                 * Cria um novo ModelVenda
                 */
                int codigoCliente = Integer.parseInt(jtfCodigoCliente.getText());
                Double valorLiquido = Double.parseDouble(jtfValorTotal.getText());
                Double valorBruto = Double.parseDouble(jtfValorTotal.getText()) + desconto;
                try {
                    Date dataVenda = blDatas.converterDataParaDateUS(new java.util.Date(System.currentTimeMillis()));
                    modelVenda = new ModelVendas(listaModelProdutos, codigoCliente, valorLiquido, valorBruto, desconto, dataVenda);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                modelVenda.setIdVenda(codigoVenda);
                modelVenda.setVenValorRecebido(valorRecebido);
                if (!controllerVendas.atualizarVendasController(modelVenda)) {
                    //se o produto não for salvo no banco vai executar o código deste if
                    JOptionPane.showMessageDialog(this, "Erro ao salvar venda, cunsulta a SSD e informe o código de erro 12091014", "Erro", JOptionPane.ERROR_MESSAGE);

                }
                //---------------------------ETAPA 4---------------------------------

                //Adicionar os novos produtos ao pedido que está sendo alterado
                for (int i = 0; i < listaModelProdutos.size(); i++) {
                    modelVendasProdutos = new ModelProdutosDaVenda();
                    modelVendasProdutos.setProduto(listaModelProdutos.get(i).getIdProduto());
                    modelVendasProdutos.setIdDaVenda(modelVenda.getIdVenda());
                    modelVendasProdutos.setVenProValor(listaModelProdutos.get(i).getProValor());//Busca o valor unitário do produto na tabela jtProdutos
                    modelVendasProdutos.setVenProQuantidadeVendida(listaModelProdutos.get(i).getProQuantidadeVendida());//Busca a quantidade de produtos que foi vendida
                    listaModelVendasProdutos.add(modelVendasProdutos);

                }
                //---------------------------ETAPA 5---------------------------------
                //Salvar lista de protudos da vendas no banco de dados
                if (controllerVendasProdutos.salvarVendasProdutosController(listaModelVendasProdutos)) {
                    //Atualiza o estoque após efetivar avenda
                    controllerProdutos.alterarEstoqueProdutoController(listaModelProdutos);
                    limparFormulario();
                    habilitarDesabilitarCambos(false);
                    jtfRecebido.setText("0");//zera o valor recebido quando clica em alterar venda

                    /**
                     * Fecha a janela atual e abre uma janela nova exibindo os
                     * pedidos que foram realizados.
                     */
                    try {
                        viewVisualizarVendas.carregarVendas(modelVenda.getIdVenda() + "");
                        viewVisualizarVendas.marcarUltimaVenda();
                        viewVisualizarVendas.setVisible(true);
                    } catch (Exception e) {
                        viewVisualizarVendas = new ViewVisualizarVendas();
                        viewVisualizarVendas.carregarVendas(modelVenda.getIdVenda() + "");
                        viewVisualizarVendas.marcarUltimaVenda();
                        viewVisualizarVendas.setVisible(true);
                    }

                    JOptionPane.showMessageDialog(this, "Alteração salva com sucesso!", "Obrigado", JOptionPane.INFORMATION_MESSAGE);
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar produtos, cunsulta a SSD e informe o código de erro 13091407", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            }

        }

        viewVisualizarVendas.visualizarVenda(modelVenda.getIdVenda());


    }//GEN-LAST:event_btSalvarPedidoMouseReleased

    private void uJPanelImagem11MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uJPanelImagem11MouseReleased
        // TODO add your handling code here:
        setExtendedState(ICONIFIED);
    }//GEN-LAST:event_uJPanelImagem11MouseReleased

    private void uJPanelImagem1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uJPanelImagem1MouseReleased
        // TODO add your handling code here:
        //this.dispose();
        dispose();
        //System.exit(0);

    }//GEN-LAST:event_uJPanelImagem1MouseReleased

    private void selecionarProdutoNaLista(javax.swing.event.PopupMenuEvent evt) {
        // TODO add your handling code here:
        ModelProduto produtoSelecionado = listaModelProdutos.get(produtos.getSelectedIndex());
        jtfCodigoProduto.setText(String.valueOf(produtoSelecionado.getIdProduto()));
        janelaProdutosCadastrados.dispose();

    }


    private void btSalvarPedidoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSalvarPedidoMousePressed
        // TODO add your handling code here:

        btSalvarPedido.setSize(btSalvarPedido.getWidth() - 3, btSalvarPedido.getHeight() - 3);


    }//GEN-LAST:event_btSalvarPedidoMousePressed

    private void barraDeMoverMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barraDeMoverMouseDragged

        Point p = getLocation();
        setLocation(p.x + evt.getX() - point.x, p.y + evt.getY() - point.y);

    }//GEN-LAST:event_barraDeMoverMouseDragged

    private void barraDeMoverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barraDeMoverMousePressed

        point.x = evt.getX();
        point.y = evt.getY();

    }//GEN-LAST:event_barraDeMoverMousePressed

    private void jbtVendasRealizadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtVendasRealizadasActionPerformed
        // TODO add your handling code here:
        try {
            viewVisualizarVendas.setVisible(true);
            dispose();

        } catch (Exception e) {
            viewVisualizarVendas = new ViewVisualizarVendas();
            viewVisualizarVendas.setViewTelaVendas(this);
            viewVisualizarVendas.setVisible(true);
        }
        viewVisualizarVendas.setLocationRelativeTo(viewVisualizarVendas);

    }//GEN-LAST:event_jbtVendasRealizadasActionPerformed

    private void btPesquisarProdutoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btPesquisarProdutoMouseReleased
        // TODO add your handling code here:
        //Condição que controla quando o botão vai funcionar
        if (statusEdicao) {
            janelaSelecaoProduto();
        }

    }//GEN-LAST:event_btPesquisarProdutoMouseReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtfPontoDeReferenciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfPontoDeReferenciaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfPontoDeReferenciaFocusLost

    private void jtfNomeDoClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfNomeDoClienteKeyReleased
        if (jtfNomeDoCliente.getText().length() >= 3) {
            preencherCodigoClientePeloCombobox();
        }
    }//GEN-LAST:event_jtfNomeDoClienteKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            modelCliente = controllerCliente.getClienteController(Integer.parseInt(jtfCodigoCliente.getText()));
            viewCliente.setViewVendasFrame(this);
            viewCliente.alterarCliente(modelCliente);
            viewCliente.setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Selecione primeiro um cliente digitando o número do telefone.");
            jtfTelefone.requestFocus();

        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jtfDDDTelefoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfDDDTelefoneKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtfTelefone.requestFocus();
        }
    }//GEN-LAST:event_jtfDDDTelefoneKeyReleased

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
            java.util.logging.Logger.getLogger(ViewVendasFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewVendasFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewVendasFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewVendasFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewVendasFinal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barraDeMover;
    private componentes.UJPanelImagem btAddProduto;
    private componentes.UJPanelImagem btCancelarPedido;
    private componentes.UJPanelImagem btNovaVenda;
    private componentes.UJPanelImagem btNovoCliente;
    private componentes.UJPanelImagem btPesquisarProduto;
    private componentes.UJPanelImagem btSalvarPedido;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbRemoverProdutos;
    private javax.swing.JButton jbtVendasRealizadas;
    private javax.swing.JPanel jpPlanoDeFundo;
    private javax.swing.JSpinner jspQuantidade;
    private javax.swing.JTable jtProdutosVenda;
    private componentes.UJTextField jtfBairro;
    private componentes.UJTextField jtfCidade;
    private javax.swing.JTextField jtfCodigoCliente;
    private javax.swing.JTextField jtfCodigoDaVenda;
    private javax.swing.JTextField jtfCodigoProduto;
    private componentes.UJTextField jtfDDDTelefone;
    private javax.swing.JTextField jtfDesconto;
    private componentes.UJTextField jtfEndereco;
    private componentes.UJTextField jtfEstado;
    private componentes.UJTextField jtfNomeDoCliente;
    private componentes.UJTextField jtfNumCasa;
    private javax.swing.JTextField jtfPontoDeReferencia;
    private javax.swing.JTextField jtfRecebido;
    private javax.swing.JTextField jtfTelefone;
    private javax.swing.JTextField jtfTroco;
    private javax.swing.JTextField jtfValorTotal;
    private componentes.UJPanelImagem uJPanelImagem1;
    private componentes.UJPanelImagem uJPanelImagem11;
    private componentes.UJPanelImagem uJPanelImagem7;
    // End of variables declaration//GEN-END:variables

    public ViewVisualizarVendas getViewVisualizarVendas() {
        return viewVisualizarVendas;
    }

    public void setViewVisualizarVendas(ViewVisualizarVendas viewVisualizarVendas) {
        this.viewVisualizarVendas = viewVisualizarVendas;
    }

    private void imprimirCupom(ArrayList<ModelProdutosDaVenda> listaModelVendasProdutos, ModelVendas modelVenda) {

        String dataF = "dd/MM/yyyy";
        String horaF = "H:mm - a";
        String data, hora;
        //pega a data e formata no padrão acima
        java.util.Date tempoAtual = new java.util.Date();
        SimpleDateFormat formata = new SimpleDateFormat(dataF);
        data = formata.format(tempoAtual);
        //pega a data e formata no padrão acima
        formata = new SimpleDateFormat(horaF);
        hora = formata.format(tempoAtual);
        //-------------Iniciando funções do cupom---------------

        String conteudoImprimir = "";

        for (int i = 0; i < listaModelVendasProdutos.size(); i++) {
            conteudoImprimir += listaModelVendasProdutos.get(i).getProduto() + "  "
                    + listaModelVendasProdutos.get(i).getVenProQuantidadeVendida() + " "
                    + listaModelVendasProdutos.get(i).getVenProValor() + " "
                    + listaModelVendasProdutos.get(i).getNomeProduto() + "\n \r";
        }

        //detectaImpressoras();
        String cupomFiscal = ""
                + "'" + nomeDaEmpresa + "'\n\r"
                + "------------------------------\n\r"
                + "-       CUPOM NÃO FISCAL     -\n\r"
                + "------------------------------\n\r"
                + "Cod | QTD | Preço | Descrição \n\r"
                + conteudoImprimir + ""
                + "------------------------------\n\r"
                + "Total da compra: R$ " + modelVenda.getVenValorBruto() + "\n\r"
                + "Descontos      : R$ " + modelVenda.getVenDesconto() + "\n\r"
                + "Total a pagar  : R$ " + modelVenda.getVenValorLiquido() + "\n\r"
                + "------------------------------\n\r"
                + "Obrigado pela preferência! \n\r"
                + data + " - " + hora + "\n\r"
                + "------------------------------\n\r"
                + "\n\r"
                + "\n\r"
                + "\f";

        //imprimir(cupomFiscal);
        salvarEImprimir(cupomFiscal);
        System.out.println(cupomFiscal);
    }

    String caminhoImpressao = "C:\\SSDProjeto\\src\\relatorios\\cupom.txt";

    private void salvarEImprimir(String pConteudo) {

        File arquivo = new File(caminhoImpressao);
        try {
            FileWriter grava
                    = new FileWriter(arquivo, false);
            PrintWriter escreve = new PrintWriter(grava);
            escreve.println(pConteudo);
            escreve.close();
            grava.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        /**
         * Desktop desktop = Desktop.getDesktop();
         *
         * try { File arquivoprint = new File(pCaminho);
         * desktop.print(arquivoprint); } catch (IOException ex) {
         * ex.printStackTrace(); }*
         */
    }

    private void imprimir(String pTextoImprimir) {
        String defaultPrinter = PrintServiceLookup.lookupDefaultPrintService().getName();

        System.out.println("Impressora padrão: " + defaultPrinter);
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();

        try {

            InputStream is = new ByteArrayInputStream(pTextoImprimir.getBytes("UTF8"));
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            pras.add(new Copies(1));

            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc doc = new SimpleDoc(is, flavor, null);
            DocPrintJob job = service.createPrintJob();

            PrintJobWatcher pjw = new PrintJobWatcher(job);
            job.print(doc, pras);
            pjw.waitForDone();
            System.out.println("Texto impresso:");
            System.out.println(pTextoImprimir);

            is.close();
        } catch (Exception ex) {
            Logger.getLogger(ViewVendasFinal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @return the modelVendaAlteracao
     */
    public ModelVendas getModelVendaAlteracao() {
        return modelVendaAlteracao;
    }

    /**
     * @param modelVendaAlteracao the modelVendaAlteracao to set
     */
    public void setModelVendaAlteracao(ModelVendas modelVendaAlteracao) {
        this.modelVendaAlteracao = modelVendaAlteracao;
    }

    class PrintJobWatcher {

        boolean done = false;

        PrintJobWatcher(DocPrintJob job) {
            job.addPrintJobListener(new PrintJobAdapter() {
                public void printJobCanceled(PrintJobEvent pje) {
                    allDone();
                }

                public void printJobCompleted(PrintJobEvent pje) {
                    allDone();
                }

                public void printJobFailed(PrintJobEvent pje) {
                    allDone();
                }

                public void printJobNoMoreEvents(PrintJobEvent pje) {
                    allDone();
                }

                void allDone() {
                    synchronized (PrintJobWatcher.this) {
                        done = true;
                        System.out.println("Arquivo enviado para impressão ...");

                        PrintJobWatcher.this.notify();
                    }
                }
            });
        }

        public synchronized void waitForDone() {
            try {
                while (!done) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
        }
    }

}
