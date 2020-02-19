/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.DAOProdutosVendasProdutos;
import java.util.ArrayList;
import model.ModelProdutosVendasProdutos;

/**
 * Retorno os produtos da venda de acordo com o código da venda que foi passado como parametro
 * @author Darlan
 */
public class ControllerProdutosVendasProdutos {

    private DAOProdutosVendasProdutos dAOProdutosVendasProdutos = new DAOProdutosVendasProdutos();
/**
 * Este getListaProdutos busca no banco de dados todos os produtos da venda selecionada, para selecionar uma venda 
 * usamos como parametro o código da venda. então get(codigo da venda) é o mesmo que pegar a venda que possúi este código no banco
 * @param pCodigoVenda
 * @return 
 */
    public ArrayList<ModelProdutosVendasProdutos> getListaProdutosVendasProdutosController(int pCodigoVenda) {
        return this.dAOProdutosVendasProdutos.getListaProdutosVendasProdutosDAO(pCodigoVenda);

    }

}
