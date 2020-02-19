package model;

/**
 * ModelProdutosDaVenda é um construtor contendo "ID da Venda Realizada " ,
 fk_produto, fk_venda, venProValor, venProQuantidadeVendida
 *
 * @author Darlan
 */
public class ModelProdutosDaVenda {

    private int idVendaProduto;
    private int produto;//código do produto
    private int idDaVenda;//código da idDaVenda
    private double venProValor; //valor unitário do produto
    private int venProQuantidadeVendida;//quantidade de produtos vendidos na idDaVenda
    private int formaPagamento;//Forma depagamento 
    private String nomeProduto;
 
    /**
     * Construtor ModelVendasProdutos é um construtor contendo "ID da Venda
 Realizada " , fk_produto, fk_venda "id da idDaVenda", venProValor,
 venProQuantidadeVendida
     */
    public ModelProdutosDaVenda() {
    }

    /**
     * seta o valor de idVendaProduto
     *
     * @param pIdVendaProduto
     */
    public void setIdVendaProduto(int pIdVendaProduto) {
        this.idVendaProduto = pIdVendaProduto;
    }

    /**
     * return pk_idVendaProduto
     */
    public int getIdVendaProduto() {
        return this.idVendaProduto;
    }

    /**
     * seta o valor de produto
     *
     * @param pProduto
     */
    public void setProduto(int pProduto) {
        this.produto = pProduto;
    }

    /**
     * return fk_produto
     */
    public int getProduto() {
        return this.produto;
    }

    /**
     * seta o valor de idDaVenda
     *
     * @param pVendas
     */
    public void setIdDaVenda(int pVendas) {
        this.idDaVenda = pVendas;
    }

    /**
     * return fk_vendas
     */
    public int getIdDaVenda() {
        return this.idDaVenda;
    }

    /**
     * seta o valor de venProValor
     *
     * @param pVenProValor
     */
    public void setVenProValor(double pVenProValor) {
        this.venProValor = pVenProValor;
    }

    /**
     * return venProValor
     */
    public double getVenProValor() {
        return this.venProValor;
    }

    /**
     * seta o valor de venProQuantidadeVendida
     *
     * @param pVenProQuantidade
     */
    public void setVenProQuantidadeVendida(int pVenProQuantidade) {
        this.venProQuantidadeVendida = pVenProQuantidade;
    }

    /**
     * return venProQuantidadeVendida
     */
    public int getVenProQuantidadeVendida() {
        return this.venProQuantidadeVendida;
    }

    public int getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(int formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public String toString() {
        return "ModelVendasProdutos {" + "::idVendaProduto = " + getIdVendaProduto() + "::produto = " + getProduto() + "::vendas = " + getIdDaVenda() + "::venProValor = " + getVenProValor() + "::venProQuantidade = " + getVenProQuantidadeVendida() + "}";
    }

    /**
     * @return the nomeProduto
     */
    public String getNomeProduto() {
        return nomeProduto;
    }

    /**
     * @param nomeProduto the nomeProduto to set
     */
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
}
