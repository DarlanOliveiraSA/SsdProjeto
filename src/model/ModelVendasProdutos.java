package model;
/**
* ModelVendasProdutos é um construtor contendo "ID da Venda Realizada " , fk_produto, fk_venda "id da venda", venProValor, venProQuantidade 
* @author Darlan
*/
public class ModelVendasProdutos {

    private int idVendaProduto;
    private int produto;//código do produto
    private int vendas;//código da venda
    private double venProValor; //valor unitário do produto
    private int venProQuantidade;//quantidade de produtos vendidos na venda
    private int formaPagamento;//Forma depagamento
    private String nomeProduto;

    /**
    * Construtor
    */
    public ModelVendasProdutos(){}

    /**
    * seta o valor de idVendaProduto
    * @param pIdVendaProduto
    */
    public void setIdVendaProduto(int pIdVendaProduto){
        this.idVendaProduto = pIdVendaProduto;
    }
    /**
    * return pk_idVendaProduto
    */
    public int getIdVendaProduto(){
        return this.idVendaProduto;
    }

    /**
    * seta o valor de produto
    * @param pProduto
    */
    public void setProduto(int pProduto){
        this.produto = pProduto;
    }
    /**
    * return fk_produto
    */
    public int getProduto(){
        return this.produto;
    }

    /**
    * seta o valor de vendas
    * @param pVendas
    */
    public void setVendas(int pVendas){
        this.vendas = pVendas;
    }
    /**
    * return fk_vendas
    */
    public int getVendas(){
        return this.vendas;
    }

    /**
    * seta o valor de venProValor
    * @param pVenProValor
    */
    public void setVenProValor(double pVenProValor){
        this.venProValor = pVenProValor;
    }
    /**
    * return venProValor
    */
    public double getVenProValor(){
        return this.venProValor;
    }

    /**
    * seta o valor de venProQuantidade
    * @param pVenProQuantidade
    */
    public void setVenProQuantidade(int pVenProQuantidade){
        this.venProQuantidade = pVenProQuantidade;
    }
    /**
    * return venProQuantidade
    */
    public int getVenProQuantidade(){
        return this.venProQuantidade;
    }


    public int getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(int formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    @Override
    public String toString(){
        return "ModelVendasProdutos {" + "::idVendaProduto = " + this.idVendaProduto + "::produto = " + this.produto + "::vendas = " + this.vendas + "::venProValor = " + this.venProValor + "::venProQuantidade = " + this.venProQuantidade +  "}";
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