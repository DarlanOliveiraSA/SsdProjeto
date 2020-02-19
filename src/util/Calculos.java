/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import controller.ControllerProdutos;
import controller.ControllerVendas;
import controller.ControllerVendasProdutos;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.ModelProdutoSimples;
import model.ModelProduto;
import model.ModelSessaoUsuario;
import model.ModelVendas;
import model.ModelProdutosDaVenda;

/**
 * Esta classe calcula as vendas e faz outros calculos para serem usados em todo
 * o programa
 *
 * @author Darlan
 */
public class Calculos {

    ModelProduto modelProdutos = new ModelProduto();
    ModelVendas modelVendas = new ModelVendas();
    ModelProdutosDaVenda modelVendasProdutos = new ModelProdutosDaVenda();
    ModelSessaoUsuario modelSessaoUsuario = new ModelSessaoUsuario();
    ArrayList<ModelProdutosDaVenda> listaModelVendasProdutos = new ArrayList<>();
    ArrayList<ModelProduto> listaModelProdutos = new ArrayList<>();
    ControllerProdutos controllerProdutos = new ControllerProdutos();
    ControllerVendas controllerVendas = new ControllerVendas();
    ControllerVendasProdutos controllerVendasProdutos = new ControllerVendasProdutos();
    BLDatas blDatas = new BLDatas();
    ModelProdutoSimples produto = new ModelProdutoSimples();

    /**
     * Metodo usado para salvar uma venda
     */
    //inicio parametros
    private int codigoCliente = 1;
    private int codigoProduto;
    private int codigoVenda;
    private double valorBruto = 0;
    private double desconto = 0;
    private double valorLiquido;
    private int colQuantTabela = 2;
    private int colPrecoTabela = 3;
    private int colTotalTabela = 4;
    private int formaPagamento = 1;
    private DefaultTableModel tabelaVenda = (DefaultTableModel) new DefaultTableModel();

    /**
     * Salva uma venda passando como parametro uma tabela, a coluna de
     * quantidade da tabela, a coluna preco unitário da tabela, o código do
     * cliente, o valor do desconto e a forma de pagamento.
     *
     * @param tabelaProdutosP
     * @param colQuantP
     * @param colPrecoP
     * @param idClienteP
     * @param descontoP
     * @param formaPagP
     */
    public void salvarVenda(DefaultTableModel tabelaProdutosP, int colQuantP, int colPrecoP, int idClienteP, double descontoP, int formaPagP) {
        //Inicio . zera todos os controladores para iniciar uma nova venda
        modelVendas = new ModelVendas();
        controllerVendas = new ControllerVendas();
        modelProdutos = new ModelProduto();
        controllerProdutos = new ControllerProdutos();
        modelVendasProdutos = new ModelProdutosDaVenda();
        listaModelVendasProdutos = new ArrayList<>();
        listaModelProdutos = new ArrayList<>();
        controllerVendasProdutos = new ControllerVendasProdutos();
        valorBruto = 0;
        valorLiquido = 0;
        //Fim . zera todos os controladores para iniciar uma nova venda
        /**
         * Seta os valores passados nos parametros.
         */
        setTabelaVenda(tabelaProdutosP);
        setColQuantTabela(colQuantP);
        setColPrecoTabela(colPrecoP);
        setCodigoCliente(idClienteP);
        setDesconto(descontoP);
        setFormaPagamento(formaPagP);

        int cont = tabelaVenda.getRowCount();
        for (int i = 0; i < cont; i++) {
            double quant = Double.parseDouble(tabelaVenda.getValueAt(i, colQuantTabela) + "");
            double preco = Double.parseDouble(tabelaVenda.getValueAt(i, colPrecoTabela) + "");
            this.valorBruto += preco * quant;
        }

        modelVendas.setCliente(codigoCliente);//seleciona o cliente número 1 definido no banco de dados como balcão
        modelVendas.setFormaPagamento(getFormaPagamento());
        try {
            //Salva a data da venda e já converte para o formato que o banco de dados aceita, formato DatesUS
            modelVendas.setVenDataVenda(blDatas.converterDataParaDateUS(new java.util.Date(System.currentTimeMillis())));
        } catch (Exception e) {
            //Se der algum erro ao converter a data ele imprime no console
            e.printStackTrace();
        }
        modelVendas.setVenValorBruto(valorBruto);//salva o Valor bruto da venda no model
        modelVendas.setVenDesconto(desconto);//Salva o valor do desconto
        valorLiquido = valorBruto - desconto;
        modelVendas.setVenValorLiquido(valorLiquido);//salva o Valor liquido da venda no model

        //Salvar venda
        codigoVenda = controllerVendas.salvarVendasController(modelVendas);

        for (int i = 0; i < cont; i++) {
            modelVendasProdutos = new ModelProdutosDaVenda();//Cria um novo ModelVendas para não ficar nehum lixo das vendas anteriores
            codigoProduto = Integer.parseInt(tabelaVenda.getValueAt(i, 0) + "");

            modelVendasProdutos.setProduto(codigoProduto);
            modelVendasProdutos.setIdDaVenda(codigoVenda);
            modelVendasProdutos.setVenProValor((double) tabelaVenda.getValueAt(i, 3));//Busca o valor unitário do produto na tabela jtProdutos
            modelVendasProdutos.setVenProQuantidadeVendida(Integer.parseInt(tabelaVenda.getValueAt(i, 2).toString()));//Busca a quantidade de produtos na tabela jtProdutos

            modelProdutos = new ModelProduto();//Cria um novo ModelProduto para não ficar nehum lixo das vendas anteriores produtos da venda
            modelProdutos.setIdProduto(codigoProduto);//Salva o id do produto deste ciclo de for no modelProdutos
            //Atualiza o estoque do produto abatendo o que foi vendido
            modelProdutos.setProEstoque(
                    controllerProdutos.retornarProdutoController(codigoProduto).getProEstoque()//
                    - Integer.parseInt(tabelaVenda.getValueAt(i, 2).toString()));
            listaModelVendasProdutos.add(modelVendasProdutos);
            listaModelProdutos.add(modelProdutos);
            System.out.println("\nCod Venda: " + codigoVenda + "\nCod cli: " + codigoCliente + "\nCod Pro: " + codigoProduto + "\nVal Brut: " + valorBruto + "\nVal Liq: " + valorLiquido + "\n ");
        }
        //Salvar lista de protudos da vendas no banco de dados
        if (controllerVendasProdutos.salvarVendasProdutosController(listaModelVendasProdutos)) {
            //Atualiza o estoque estoque dos produtos da venda atual
            controllerProdutos.alterarEstoqueProdutoController(listaModelProdutos);
            //Mostra na tela uma mensagem com os dadosda venda que foi realizada

            System.out.println("Venda salva com sucesso no banco de dados");
        } else {
            System.out.println("Erro ao salvar a venda no banco de dados");
        }

    }

    
    public Calculos() {
    }

    //Fim parametros
    /**
     * Colunas da tabela da venda 0 = item 1 = código do prod 2 = nome do prod 3
     * = quantidade 4 = valor unitário 5 = valor total
     *
     * @param tabelaDeProdutosP
     */
    public Calculos(DefaultTableModel tabelaDeProdutosP) {

    }

    /**
     * @return the codigoCliente
     */
    public int getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * @param codigoCliente the codigoCliente to set
     */
    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    /**
     * @return the codigoProduto
     */
    public int getCodigoProduto() {
        return codigoProduto;
    }

    /**
     * @param codigoProduto the codigoProduto to set
     */
    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    /**
     * @return the codigoVenda
     */
    public int getCodigoVenda() {
        return codigoVenda;
    }

    /**
     * @param codigoVenda the codigoVenda to set
     */
    public void setCodigoVenda(int codigoVenda) {
        this.codigoVenda = codigoVenda;
    }

    /**
     * @return the valorBruto
     */
    public double getValorBruto() {
        return valorBruto;
    }

    /**
     * @param valorBruto the valorBruto to set
     */
    public void setValorBruto(double valorBruto) {
        this.valorBruto = valorBruto;
    }

    /**
     * @return the desconto
     */
    public double getDesconto() {
        return desconto;
    }

    /**
     * @param desconto the desconto to set
     */
    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    /**
     * @return the valorLiquido
     */
    public double getValorLiquido() {
        return valorLiquido;
    }

    /**
     * @param valorLiquido the valorLiquido to set
     */
    public void setValorLiquido(double valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    /**
     * @return the tabelaColunas
     */
    public int getColQuantTabela() {
        return colQuantTabela;
    }

    /**
     * @param tabelaColunas the tabelaColunas to set
     */
    public void setColQuantTabela(int colQuantTabelaP) {
        this.colQuantTabela = colQuantTabelaP;
    }

    /**
     * @return the tabelaLinhas
     */
    public int getColPrecoTabela() {
        return colPrecoTabela;
    }

    /**
     * @param colPrecoTabelaP the tabelaLinhas to set
     */
    public void setColPrecoTabela(int colPrecoTabelaP) {
        this.colPrecoTabela = colPrecoTabelaP;
    }

    /**
     * @return the tabelaVenda
     */
    public DefaultTableModel getTabelaVenda() {
        return tabelaVenda;
    }

    /**
     * @param tabelaVenda the tabelaVenda to set
     */
    public void setTabelaVenda(DefaultTableModel tabelaVendaP) {
        this.tabelaVenda = tabelaVendaP;
    }

    public int getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(int formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public int getColTotalTabela() {
        return colTotalTabela;
    }

    public void setColTotalTabela(int colTotalTabela) {
        this.colTotalTabela = colTotalTabela;
    }

}
