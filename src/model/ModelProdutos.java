/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Um model produtos é um construtor de cada produto contendo ID, NOME, VALOR, E ESTOQUE.
 * basicamente só serve para definir uma estrutura de um produto mesmo
 * @author Darlan
 */
public class ModelProdutos {

    private int idProduto;
    private String proNome;
    private Double proValor;
    private int proEstoque;
    private int proQuantidadeVendida;

    /**
     * @return the idProduto
     */
    public int getIdProduto() {
        return idProduto;
    }

    /**
     * @param idProduto the idProduto to set
     */
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * @return the proNome
     */
    public String getProNome() {
        return proNome;
    }

    /**
     * @param proNome the proNome to set
     */
    public void setProNome(String proNome) {
        this.proNome = proNome;
    }

    /**
     * @return the proValor
     */
    public Double getProValor() {
        return proValor;
    }

    /**
     * @param proValor the proValor to set
     */
    public void setProValor(Double proValor) {
        this.proValor = proValor;
    }

    /**
     * @return the proEstoque
     */
    public int getProEstoque() {
        return proEstoque;
    }

    /**
     * Passa o novo valor do estoque para o ModelProduto
     * @param proEstoque the proEstoque to set
     */
    public void setProEstoque(int proEstoque) {
        this.proEstoque = proEstoque;
    }

    /**
     * @return the proQuantidadeVendida
     */
    public int getProQuantidadeVendida() {
        return proQuantidadeVendida;
    }

    /**
     * @param proQuantidadeVendida the proQuantidadeVendida to set
     */
    public void setProQuantidadeVendida(int proQuantidadeVendida) {
        this.proQuantidadeVendida = proQuantidadeVendida;
    }

    @Override
    public String toString() {
        return getProNome();
    }

    
    
    

}
