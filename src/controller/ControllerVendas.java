package controller;

import model.ModelVendas;
import DAO.DAOVendas;
import java.util.ArrayList;

/**
 * No caso, tudo que está na pasta controller só serve para intermediar o
 * comando real que ficará no DAO. neste controller posso executar os comandos
 * sql a seguir: salvarVendasController, getVendasController,
 * getListaVendasController, atualizarVendasController, excluirVendasController
 *
 * @author Darlan
 */
public class ControllerVendas {

    private DAOVendas daoVendas = new DAOVendas();

    public int recuperarUltimaVendaController() {

        return this.daoVendas.recuperarUltimoID();
    }

    /**
     * Salva a venda no banco de dados -- Retorna o ID da venda que está sendo
     * salva se estiver tudo certo, se der algum erro retorna 0 (zero).
     *
     * @param pModelVendas return int
     */
    public int salvarVendasController(ModelVendas pModelVendas) {
        return this.daoVendas.salvarVendasDAO(pModelVendas);
    }

    /**
     * recupera Vendas
     *
     * @param pIdVenda return ModelVendas
     */
    public ModelVendas getVendasController(int pIdVenda) {
        return this.daoVendas.getVendasDAO(pIdVenda);
    }

    /**
     * recupera uma lista deVendas
     *
     * @param pIdVenda return ArrayList
     */
    public ArrayList<ModelVendas> getListaVendasController() {
        return this.daoVendas.getListaVendasDAO();
    }

    /**
     * atualiza Vendas
     *
     * @param pModelVendas return boolean
     */
    public boolean atualizarVendasController(ModelVendas pModelVendas) {
        return this.daoVendas.atualizarVendasDAO(pModelVendas);
    }

    /**
     * Este comando vai intermediar a exclusão da venda através do id da venda
     * passada como parametro entre ()parenteses o ID DA VENDA No caso, tudo que
     * está na pasta controller só serve para intermediar o comando que ficará
     * no DAO.
     *
     * @param pIdVenda return boolean
     */
    public boolean excluirVendasController(int pIdVenda) {
        return this.daoVendas.excluirVendasDAO(pIdVenda);
    }

    /**
     * Gerar relatório da venda selecionada para imprimir a venda passada como
     * id deve receber como parametro o código da venda
     *
     * @param codigoVenda
     * @return
     */
    public boolean gerarRelatoriovenda(int codigoVenda) {

        return this.daoVendas.gerarRelatorioDAO(codigoVenda);
    }
}
