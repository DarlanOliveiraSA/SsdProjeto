/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.DaoProdutos;
import java.util.ArrayList;
import model.ModelProduto;

/**
 * No caso, tudo que está na pasta controller só serve para intermediar o
 * comando real que ficará no DAO.
 *
 * @author Darlan
 */
public class ControllerProdutos {

     private DaoProdutos daoProdutos = new DaoProdutos();

    /**
     * Salvar produtos Controller usando como referencia a classe Pmodel
     *
     * @param pModelProdutos
     * @return
     */
    public int salvarProdutoController(ModelProduto pModelProdutos) {
        return this.daoProdutos.salvarProdutosDAO(pModelProdutos);
    }

    /**
     * Excluir produto usando como referencia código
     *
     * @param pCodigo
     * @return
     */
    public boolean excluirProdutoController(int pCodigo) {
        return this.daoProdutos.excluirProdutoDAO(pCodigo);
    }

    /**
     * Alterar produto usando como referencia código
     *
     * @param pModelProdutos
     * @return
     */
    public boolean alterarProdutoController(ModelProduto pModelProdutos) {
        return this.daoProdutos.alterarProdutoDAO(pModelProdutos);
    }

    /**
     * Busca no banco de dados o produto e armazena em um ModelProduto o
 parametro de busca é o código do produto Lembrando que o ModelProduto
 contem id, nome, valor e estoque do produto
     *
     * @param pCodigo
     * @return
     */
    public ModelProduto retornarProdutoController(int pCodigo) {
        return this.daoProdutos.retornarProdutoDAO(pCodigo);
    }

    /**
     * Retornar um ModelProduto usando como parametro o nome do produto
     *
     * @param pNomeProduto
     * @return
     */
    public ModelProduto retornarProdutoController(String pNomeProduto) {
        return this.daoProdutos.retornarProdutoDAO(pNomeProduto);
    }

    /**
     * Retornar lista completa de produtos
     *
     * @return Lista Model Produtos
     */
    public ArrayList<ModelProduto> retornarListaProdutoController() {
        return this.daoProdutos.retornarListaProdutosDAO();
    }

    /**
     * Alterar o estoque dos produtos no banco de dados através da lista enviada
     * no parametros
     *
     * @param pListaModelProdutos
     * @return
     */
    public boolean alterarEstoqueProdutoController(ArrayList<ModelProduto> pListaModelProdutos) {
        return this.daoProdutos.alterarEstoqueProdutosDao(pListaModelProdutos);
    }
    
        /**
     * Alterar o estoque dos produtos no banco de dados através do produto enviado
     * no parametro
     *
     * @param pModelProduto
     * @return
     */
    public boolean alterarEstoqueProdutoController(ModelProduto pModelProduto) {
        return this.daoProdutos.alterarEstoqueProdutosDao(pModelProduto);
    }

}
