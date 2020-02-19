package controller;

import model.ModelCliente;
import DAO.DAOCliente;
import java.util.ArrayList;

/**
*
* @author Darlan
*/
public class ControllerCliente {

    private DAOCliente daoCliente = new DAOCliente();

    /**
    * grava Cliente
    * @param pModelCliente
    * return int
    */
    public int salvarClienteController(ModelCliente pModelCliente){
        return this.daoCliente.salvarClienteDAO(pModelCliente);
    }

    /**
    * recupera Cliente
    * @param pIdCliente
    * return ModelCliente
    */
    public ModelCliente getClienteController(int pIdCliente){
        return this.daoCliente.getClienteDAO(pIdCliente);
    }
    
    /**
    * recupera Cliente pelo nome
    * @param pNomeCliente
    * return ModelCliente
    */
    public ModelCliente getClienteController(String pNomeCliente){
        return this.daoCliente.getClienteDAO(pNomeCliente);
       
    }
    
    /**
    * recupera Cliente pelo telfone
    * @param pTelefone
    * return ModelCliente
    */
    public ModelCliente getClienteTelefoneController(String pTelefone){
        return this.daoCliente.getClienteTelefone(pTelefone);
       
    }
    
    
    /**
    * recupera Cliente pelo telfone
     * @param pModelCliente
     * @return 
    */
    public ArrayList<ModelCliente> getCliNomeTelEndereco(ModelCliente pModelCliente){
        return this.daoCliente.getCliNomeTelEndereco(pModelCliente);
       
    }

    /**
    * recupera uma lista deCliente
    * @param pIdCliente
    * return ArrayList
    */
    public ArrayList<ModelCliente> getListaClienteController(){
        return this.daoCliente.getListaClienteDAO();
    }

    /**
    * atualiza Cliente
    * @param pModelCliente
    * return boolean
    */
    public boolean atualizarClienteController(ModelCliente pModelCliente){
        return this.daoCliente.atualizarClienteDAO(pModelCliente);
    }

    /**
    * exclui Cliente
    * @param pIdCliente
    * return boolean
    */
    public boolean excluirClienteController(int pIdCliente){
        return this.daoCliente.excluirClienteDAO(pIdCliente);
    }

    public boolean gerarRelatorioCliente() {
        return this.daoCliente.gerarRelatorioCliente();
    }
}