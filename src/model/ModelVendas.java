package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 * A classe Model vendas contem os atributos de uma venda como id Da venda o
 * cliente que está comprando a data da venda o valor líquido o valor bruto e o
 * desconto. idVenda, iDcliente, venDataVenda, venValorLiquido, venValorBruto,
 * venDesconto também tem uma listaDeProdutosDaVenda
 *
 * @author Darlan
 */
public class ModelVendas {

    private ArrayList<ModelProdutosDaVenda> listaDeProdutosDaVenda;
    private int idVenda;
    private int cliente;
    private int formaPagamento;//Forma depagamento
    private double venValorLiquido;
    private double venValorBruto;
    private double venDesconto;
    private Date venDataVenda;
    private double venValorRecebido;
    

    /**
     * Construtor
     */
    public ModelVendas() {
    }

    public ModelVendas(ArrayList pListaProd,int pIdCliente, double pVenValorLiquido, double pVenValorBruto, double pDesconto, Date pData) {
        this.listaDeProdutosDaVenda = pListaProd;
        this.cliente = pIdCliente;
        this.venValorLiquido = pVenValorLiquido;
        this.venValorBruto = pVenValorBruto;
        this.venDesconto = pDesconto;
        this.venDataVenda = pData;
        

    }

    /**
     * seta o valor de idVenda
     *
     * @param pIdVenda
     */
    public void setIdVenda(int pIdVenda) {
        this.idVenda = pIdVenda;
    }

    /**
     * return pk_idVenda
     *
     * @return
     */
    public int getIdVenda() {
        return this.idVenda;
    }

    /**
     * seta o valor de cliente
     *
     * @param pCliente
     */
    public void setCliente(int pCliente) {
        this.cliente = pCliente;
    }

    /**
     * return fk_cliente
     *
     * @return
     */
    public int getCliente() {
        return this.cliente;
    }

    /**
     * seta o valor de venDataVenda
     *
     * @param pVenDataVenda
     */
    public void setVenDataVenda(Date pVenDataVenda) {
        this.venDataVenda = pVenDataVenda;
    }

    /**
     * return venDataVenda
     *
     * @return
     */
    public Date getVenDataVenda() {
        
        return this.venDataVenda;
    }

    /**
     * seta o valor de venValorLiquido
     *
     * @param pVenValorLiquido
     */
    public void setVenValorLiquido(double pVenValorLiquido) {
        this.venValorLiquido = pVenValorLiquido;
    }

    /**
     * return venValorLiquido
     *
     * @return
     */
    public double getVenValorLiquido() {
        return this.venValorLiquido;
    }

    /**
     * seta o valor de venValorBruto
     *
     * @param pVenValorBruto
     */
    public void setVenValorBruto(double pVenValorBruto) {
        this.venValorBruto = pVenValorBruto;
    }

    /**
     * return venValorBruto
     *
     * @return
     */
    public double getVenValorBruto() {
        return this.venValorBruto;
    }

    /**
     * seta o valor de venDesconto
     *
     * @param pVenDesconto
     */
    public void setVenDesconto(double pVenDesconto) {
        this.venDesconto = pVenDesconto;
    }

    /**
     * return venDesconto
     *
     * @return
     */
    public double getVenDesconto() {
        return this.venDesconto;
    }

    public int getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(int formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public String toString() {
        return "ModelVendas {" + "::idVenda = " + this.idVenda + "::cliente = " + this.getCliente() + "::venDataVenda = " + this.venDataVenda + "::venValorLiquido = " + this.venValorLiquido + "::venValorBruto = " + this.venValorBruto + "::venDesconto = " + this.venDesconto + "::fkforPag = " + this.formaPagamento + "}";
    }

    /**
     * @return the listaDeProdutosDaVenda
     */
    public ArrayList<ModelProdutosDaVenda> getListaDeProdutosDaVenda() {
        return listaDeProdutosDaVenda;
    }

    /**
     * @param listaDeProdutosDaVenda the listaDeProdutosDaVenda to set
     */
    public void setListaDeProdutosDaVenda(ArrayList<ModelProdutosDaVenda> listaDeProdutosDaVenda) {
        this.listaDeProdutosDaVenda = listaDeProdutosDaVenda;
    }

    /**
     * @return the venValorRecebido
     */
    public double getVenValorRecebido() {
        return venValorRecebido;
    }

    /**
     * @param venValorRecebido the venValorRecebido to set
     */
    public void setVenValorRecebido(double venValorRecebido) {
        this.venValorRecebido = venValorRecebido;
    }
}
