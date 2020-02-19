/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexoes.ConexaoMySql;
import java.util.ArrayList;
import model.ModelProdutosVendasProdutos;
import model.ModelProduto;
import model.ModelProdutosDaVenda;

/**
 *
 * @author Darlan
 */
public class DAOProdutosVendasProdutos extends ConexaoMySql {

    public ArrayList<ModelProdutosVendasProdutos> getListaProdutosVendasProdutosDAO(int pCodigoVenda) {
        ArrayList<ModelProdutosVendasProdutos> listaModelProdutoVendasProdutoses = new ArrayList<>();
        ModelProdutosVendasProdutos modelProdutosVendasProdutos = new ModelProdutosVendasProdutos();
        ModelProduto modelProdutos = new ModelProduto();
        ModelProdutosDaVenda modelProdutosDaVenda = new ModelProdutosDaVenda();

        try {
            this.conectar();
            this.executarSQL("SELECT "
                    + " tbl_produto.pk_id_produto, "
                    + " tbl_produto.pro_estoque, "
                    + " tbl_produto.pro_nome, "
                    + " tbl_produto.pro_valor, "
                    + " tbl_vendas_produtos.fk_produto, "
                    + " tbl_vendas_produtos.fk_vendas, "
                    + " tbl_vendas_produtos.pk_id_venda_produtos, "
                    + " tbl_vendas_produtos.ven_pro_quantidade, "
                    + " tbl_vendas_produtos.ven_pro_valor "
                    + " FROM tbl_vendas_produtos "
                    + " INNER JOIN tbl_produto ON tbl_produto.pk_id_produto = tbl_vendas_produtos.fk_produto "
                    + " WHERE tbl_vendas_produtos.fk_vendas = '" + pCodigoVenda + "';");

            while (this.getResultSet().next()) {
                //zera os três principais models antes de iniciar o salvamento no banco para que não sobre nenhum lixo
                modelProdutosVendasProdutos = new ModelProdutosVendasProdutos();
                modelProdutos = new ModelProduto();
                modelProdutosDaVenda = new ModelProdutosDaVenda();

                //Seta no modelProdutos os ítens que estão vindo do banco de dados no comando getResultSet
                modelProdutos.setIdProduto(this.getResultSet().getInt(1));
                modelProdutos.setProEstoque(this.getResultSet().getInt(2));
                modelProdutos.setProNome(this.getResultSet().getString(3));
                modelProdutos.setProValor(this.getResultSet().getDouble(4));

                //Seta no modelVendasProdutos os ítens que estão vindo do banco de dados no comando getResultSet
                modelProdutosDaVenda.setProduto(this.getResultSet().getInt(5));
                modelProdutosDaVenda.setIdDaVenda(this.getResultSet().getInt(6));
                modelProdutosDaVenda.setIdVendaProduto(this.getResultSet().getInt(7));
                modelProdutosDaVenda.setVenProQuantidadeVendida(this.getResultSet().getInt(8));
                modelProdutosDaVenda.setVenProValor(this.getResultSet().getDouble(9));

                //Salva os itens anteriores na classe modelProdutosVendasProdutos e salva tudo em um ArrayList
                modelProdutosVendasProdutos.setModelProdutos(modelProdutos);
                modelProdutosVendasProdutos.setModelProdutosDaVenda(modelProdutosDaVenda);

                listaModelProdutoVendasProdutoses.add(modelProdutosVendasProdutos);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        this.fecharConexao();
        }
        return listaModelProdutoVendasProdutoses;
    }

}
