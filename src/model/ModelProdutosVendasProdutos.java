/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 * Contem dois construtores produtos e produtos das vendas.
 * ModelProdutosDaVenda é como se fosse um link entre a venda e os produtos da venda ou seja
 fk_produtos + fk_vendas no banco de dados, a grosso modo.
 * @author Darlan
 */
public class ModelProdutosVendasProdutos {
    
    private ModelProduto modelProdutos;
    private ModelProdutosDaVenda modelProdutosDaVenda;

    /**
     * Busca um ModelProduto e retorna o modelProdutos com id, nome, valor e estoque
 Neste caso eu posso usar comandos do tipo listadeprodutos.get(1).getModelProdutos().getProestoque...id..valor...Nome e etc
 onde listadeprodutos.get(1) depende do array get(1) = produto 1 get(2) = produto 2 no array 
     * @return the modelProdutos
     */
    public ModelProduto getModelProdutos() {
        return modelProdutos;
    }

    /**
     * @param modelProdutos the modelProdutos to set
     */
    public void setModelProdutos(ModelProduto modelProdutos) {
        this.modelProdutos = modelProdutos;
    }

    /**
     * @return the modelProdutosDaVenda
     */
    public ModelProdutosDaVenda getModelProdutosDaVenda() {
        return modelProdutosDaVenda;
    }

    /**
     * @param modelProdutosDaVenda the modelProdutosDaVenda to set
     */
    public void setModelProdutosDaVenda(ModelProdutosDaVenda modelProdutosDaVenda) {
        this.modelProdutosDaVenda = modelProdutosDaVenda;
    }
    
}
