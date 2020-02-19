/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexoes.ConexaoMySql;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ModelCliente;
import model.ModelVendas;
import model.ModelVendasCliente;

/**
 *
 * @author Darlan
 */
public class DAOVendasCliente extends ConexaoMySql {

    public ArrayList<ModelVendasCliente> getListaVendasClienteDAO() {
        ArrayList<ModelVendasCliente> listaModelVendasClientes = new ArrayList();
        ModelVendas modelVendas = new ModelVendas();
        ModelCliente modelCliente = new ModelCliente();
        ModelVendasCliente modelVendasCliente = new ModelVendasCliente();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "tbl_vendas.pk_id_vendas,"
                    + "tbl_vendas.fk_cliente,"
                    + "tbl_vendas.ven_data_venda,"
                    + "tbl_vendas.ven_valor_liquido,"
                    + "tbl_vendas.ven_valor_bruto,"
                    + "tbl_vendas.ven_desconto,"
                    + "tbl_cliente.pk_id_cliente,"
                    + "tbl_cliente.cli_nome,"
                    + "tbl_cliente.cli_endereco,"
                    + "tbl_cliente.cli_bairro,"
                    + "tbl_cliente.cli_cidade,"
                    + "tbl_cliente.cli_uf,"
                    + "tbl_cliente.cli_cep,"
                    + "tbl_cliente.cli_telefone"
                    + " FROM"
                    + " tbl_vendas INNER JOIN tbl_cliente "
                    + "ON tbl_cliente.pk_id_cliente = tbl_vendas.fk_cliente ORDER BY tbl_vendas.pk_id_vendas ASC; "
            );

            while (this.getResultSet().next()) {
                modelVendas = new ModelVendas();
                modelCliente = new ModelCliente();
                modelVendasCliente = new ModelVendasCliente();
                //Daqui para baixo são os dados da venda
                modelVendas.setIdVenda(this.getResultSet().getInt(1));
                modelVendas.setCliente(this.getResultSet().getInt(2));
                modelVendas.setVenDataVenda(this.getResultSet().getDate(3));
                modelVendas.setVenValorLiquido(this.getResultSet().getDouble(4));
                modelVendas.setVenValorBruto(this.getResultSet().getDouble(5));
                modelVendas.setVenDesconto(this.getResultSet().getDouble(6));
                //Daqui para baixo são os dados do cliente
                modelCliente.setIdCliente(this.getResultSet().getInt(7));
                modelCliente.setCliNome(this.getResultSet().getString(8));
                modelCliente.setCliEndereco(this.getResultSet().getString(9));
                modelCliente.setCliBairro(this.getResultSet().getString(10));
                modelCliente.setCliCidade(this.getResultSet().getString(11));
                modelCliente.setCliUf(this.getResultSet().getString(12));
                modelCliente.setCliCep(this.getResultSet().getString(13));
                modelCliente.setCliTelefone(this.getResultSet().getString(14));
                //Daqui para baixo vou jogar os produtos em uma lista
                modelVendasCliente.setModelVendas(modelVendas);
                modelVendasCliente.setModelCliente(modelCliente);

                listaModelVendasClientes.add(modelVendasCliente);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listaModelVendasClientes;
    }

    /**
     * Este get busca no banco de dudados e retorna uma lista de vendas
     * realizadas para um cliente específico buscando por nome ou por id.
     *
     * @param pBusca
     * @return
     */
    public ArrayList<ModelVendasCliente> getBuscarTodasVendasDoCLiente(String pBusca) {
        Boolean verificarSeBuscaPorID = false;//Retorna true se a busca for por id e false se a busca for por nome
        int id = 0;

        try {
            Integer.parseInt(pBusca);
            verificarSeBuscaPorID = true;

        } catch (NumberFormatException e) {
            verificarSeBuscaPorID = false;
        }
        if (verificarSeBuscaPorID) {
            id = Integer.parseInt(pBusca);
        } else {
            id = 0;
        }
        ArrayList<ModelVendasCliente> listaModelVendasClientes = new ArrayList();
        ModelVendas modelVendas;
        ModelCliente modelCliente;
        ModelVendasCliente modelVendasCliente;
        try {
            this.conectar();
            if (verificarSeBuscaPorID) {
                this.executarSQL(
                        "SELECT "
                        + "tbl_vendas.pk_id_vendas,"
                        + "tbl_vendas.fk_cliente,"
                        + "tbl_vendas.ven_data_venda,"
                        + "tbl_vendas.ven_valor_liquido,"
                        + "tbl_vendas.ven_valor_bruto,"
                        + "tbl_vendas.ven_desconto,"
                        + "tbl_cliente.pk_id_cliente,"
                        + "tbl_cliente.cli_nome,"
                        + "tbl_cliente.cli_endereco,"
                        + "tbl_cliente.cli_bairro,"
                        + "tbl_cliente.cli_cidade,"
                        + "tbl_cliente.cli_uf,"
                        + "tbl_cliente.cli_cep,"
                        + "tbl_cliente.cli_telefone"
                        + " FROM"
                        + " tbl_vendas INNER JOIN tbl_cliente "
                        + "ON tbl_cliente.pk_id_cliente = tbl_vendas.fk_cliente WHERE tbl_vendas.pk_id_vendas ='" + pBusca + "' ORDER BY tbl_vendas.pk_id_vendas ASC; "
                );

            } else {
                this.executarSQL(
                        "SELECT "
                        + "tbl_vendas.pk_id_vendas,"
                        + "tbl_vendas.fk_cliente,"
                        + "tbl_vendas.ven_data_venda,"
                        + "tbl_vendas.ven_valor_liquido,"
                        + "tbl_vendas.ven_valor_bruto,"
                        + "tbl_vendas.ven_desconto,"
                        + "tbl_cliente.pk_id_cliente,"
                        + "tbl_cliente.cli_nome,"
                        + "tbl_cliente.cli_endereco,"
                        + "tbl_cliente.cli_bairro,"
                        + "tbl_cliente.cli_cidade,"
                        + "tbl_cliente.cli_uf,"
                        + "tbl_cliente.cli_cep,"
                        + "tbl_cliente.cli_telefone"
                        + " FROM"
                        + " tbl_vendas INNER JOIN tbl_cliente "
                        + "ON tbl_cliente.pk_id_cliente = tbl_vendas.fk_cliente WHERE tbl_cliente.cli_nome LIKE '%" + pBusca + "%' ORDER BY tbl_vendas.pk_id_vendas ASC; "
                );
            }

            while (this.getResultSet().next()) {
                modelVendas = new ModelVendas();
                modelCliente = new ModelCliente();
                modelVendasCliente = new ModelVendasCliente();
                //Daqui para baixo são os dados da venda
                modelVendas.setIdVenda(this.getResultSet().getInt(1));
                modelVendas.setCliente(this.getResultSet().getInt(2));
                modelVendas.setVenDataVenda(this.getResultSet().getDate(3));
                modelVendas.setVenValorLiquido(this.getResultSet().getDouble(4));
                modelVendas.setVenValorBruto(this.getResultSet().getDouble(5));
                modelVendas.setVenDesconto(this.getResultSet().getDouble(6));
                //Daqui para baixo são os dados do cliente
                modelCliente.setIdCliente(this.getResultSet().getInt(7));
                modelCliente.setCliNome(this.getResultSet().getString(8));
                modelCliente.setCliEndereco(this.getResultSet().getString(9));
                modelCliente.setCliBairro(this.getResultSet().getString(10));
                modelCliente.setCliCidade(this.getResultSet().getString(11));
                modelCliente.setCliUf(this.getResultSet().getString(12));
                modelCliente.setCliCep(this.getResultSet().getString(13));
                modelCliente.setCliTelefone(this.getResultSet().getString(14));
                //Daqui para baixo vou jogar os produtos em uma lista
                modelVendasCliente.setModelVendas(modelVendas);
                modelVendasCliente.setModelCliente(modelCliente);

                listaModelVendasClientes.add(modelVendasCliente);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listaModelVendasClientes;
    }

    /**
     * Este get busca no banco de dudados e retorna uma lista de vendas
     * realizadas para clientes entre uma data específica.
     *
     * @param pData
     * @return
     */
    public ArrayList<ModelVendasCliente> getBuscarVendasPorDataController(Date pData) {
        ArrayList<ModelVendasCliente> listaModelVendasClientes = new ArrayList();
        ModelVendas modelVendas;
        ModelCliente modelCliente;
        ModelVendasCliente modelVendasCliente;
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "tbl_vendas.pk_id_vendas,"
                    + "tbl_vendas.fk_cliente,"
                    + "tbl_vendas.ven_data_venda,"
                    + "tbl_vendas.ven_valor_liquido,"
                    + "tbl_vendas.ven_valor_bruto,"
                    + "tbl_vendas.ven_desconto,"
                    + "tbl_cliente.pk_id_cliente,"
                    + "tbl_cliente.cli_nome,"
                    + "tbl_cliente.cli_endereco,"
                    + "tbl_cliente.cli_bairro,"
                    + "tbl_cliente.cli_cidade,"
                    + "tbl_cliente.cli_uf,"
                    + "tbl_cliente.cli_cep,"
                    + "tbl_cliente.cli_telefone"
                    + " FROM"
                    + " tbl_vendas INNER JOIN tbl_cliente "
                    + "ON tbl_cliente.pk_id_cliente = tbl_vendas.fk_cliente WHERE tbl_vendas.ven_data_venda LIKE '%" + pData + "%' ORDER BY tbl_vendas.pk_id_vendas ASC; "
            );

            while (this.getResultSet().next()) {
                modelVendas = new ModelVendas();
                modelCliente = new ModelCliente();
                modelVendasCliente = new ModelVendasCliente();
                //Daqui para baixo são os dados da venda
                modelVendas.setIdVenda(this.getResultSet().getInt(1));
                modelVendas.setCliente(this.getResultSet().getInt(2));
                modelVendas.setVenDataVenda(this.getResultSet().getDate(3));
                modelVendas.setVenValorLiquido(this.getResultSet().getDouble(4));
                modelVendas.setVenValorBruto(this.getResultSet().getDouble(5));
                modelVendas.setVenDesconto(this.getResultSet().getDouble(6));
                //Daqui para baixo são os dados do cliente
                modelCliente.setIdCliente(this.getResultSet().getInt(7));
                modelCliente.setCliNome(this.getResultSet().getString(8));
                modelCliente.setCliEndereco(this.getResultSet().getString(9));
                modelCliente.setCliBairro(this.getResultSet().getString(10));
                modelCliente.setCliCidade(this.getResultSet().getString(11));
                modelCliente.setCliUf(this.getResultSet().getString(12));
                modelCliente.setCliCep(this.getResultSet().getString(13));
                modelCliente.setCliTelefone(this.getResultSet().getString(14));
                //Daqui para baixo vou jogar os produtos em uma lista
                modelVendasCliente.setModelVendas(modelVendas);
                modelVendasCliente.setModelCliente(modelCliente);

                listaModelVendasClientes.add(modelVendasCliente);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listaModelVendasClientes;

    }

}
