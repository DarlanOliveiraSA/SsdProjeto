/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import DAO.DAOVendasCliente;
import java.sql.Date;
import java.util.ArrayList;
import model.ModelVendasCliente;

/**
 *
 * @author Darlan
 */
public class ControllerVendasCliente {
    
    private DAOVendasCliente dAOVendasCliente = new DAOVendasCliente();

       public ArrayList<ModelVendasCliente> getListaVendasClienteController() {
           return this.dAOVendasCliente.getListaVendasClienteDAO();

    }
       /**
        * Serve para buscar no banco de dados todas as vendas realizadas para este cliente.
        * Pode ser buscado por ID ou pelo nome do cliente
        * @param pBusca
        * @return 
        */
       public ArrayList<ModelVendasCliente> getBuscarTodasVendasDoCLiente(String pBusca) {
           return this.dAOVendasCliente.getBuscarTodasVendasDoCLiente(pBusca);

    }

    public ArrayList<ModelVendasCliente> getBuscarVendasPorDataController(Date pData) {
        return this.dAOVendasCliente.getBuscarVendasPorDataController(pData);
    }

    
    
}
