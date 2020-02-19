package controller;

import model.ModelProdutosDaVenda;
import DAO.DAOVendasProdutos;
import java.util.ArrayList;

/**
 *
 * @author Darlan
 */
public class ControllerVendasProdutos {

    private DAOVendasProdutos daoVendasProdutos = new DAOVendasProdutos();

    /**
     * grava VendasProdutos
     *
     * @param pModelVendasProdutos return int
     */
    public int salvarVendasProdutosController(ModelProdutosDaVenda pModelVendasProdutos) {
        return this.daoVendasProdutos.salvarVendasProdutosDAO(pModelVendasProdutos);
    }

    /**
     * recupera VendasProdutos
     *
     * @param pIdVendaProduto return ModelProdutosDaVenda
     */
    public ModelProdutosDaVenda getVendasProdutosController(int pIdVendaProduto) {
        return this.daoVendasProdutos.getVendasProdutosDAO(pIdVendaProduto);
    }

    /**
     * recupera uma lista deVendasProdutos
     *
     * @param pIdVendaProduto return ArrayList
     */
    public ArrayList<ModelProdutosDaVenda> getListaVendasProdutosController() {
        return this.daoVendasProdutos.getListaVendasProdutosDAO();
    }

    /**
     * atualiza VendasProdutos
     *
     * @param pModelVendasProdutos return boolean
     */
    public boolean atualizarVendasProdutosController(ModelProdutosDaVenda pModelVendasProdutos) {
        return this.daoVendasProdutos.atualizarVendasProdutosDAO(pModelVendasProdutos);
    }

    /**
     * exclui VendasProdutos
     *
     * @param pIdVendaProduto return boolean
     */
    public boolean excluirVendasProdutosController(int pIdVendaProduto) {
        return this.daoVendasProdutos.excluirVendasProdutosDAO(pIdVendaProduto);
    }

    /**
     * Salva uma nova venda de produtos
     *
     * @param plistaModelVendasProdutos return boolean
     */
    public boolean salvarVendasProdutosController(ArrayList<ModelProdutosDaVenda> plistaModelVendasProdutos) {
        return this.daoVendasProdutos.salvarVendasProdutosDAO(plistaModelVendasProdutos);
    }

    /**
     * Retorna do banco de daos uma lista de produtos passada como parametro *
     * ModelProdutosDaVenda é um construtor contendo "ID da Venda Realizada " ,
     * fk_produto, fk_venda, venProValor, venProQuantidadeVendida
     *
     * @param pIdVenda
     * @return
     */
    public ArrayList<ModelProdutosDaVenda> getListaDeProdutosDaVendaController(int pIdVenda) {
        return this.daoVendasProdutos.getListaDeProdutosDaVendaDAO(pIdVenda);
    }

}
