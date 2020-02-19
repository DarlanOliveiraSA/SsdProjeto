/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexoes.ConexaoMySql;
import java.util.ArrayList;
import model.ModelProduto;

/**
 *
 * @author Darlan
 */
public class DaoProdutos extends ConexaoMySql {

    /**
     * Cadastrar um produto no banco através do camando SQL
     *
     * @param pModelProdutos
     * @return 0 qualquer valor maior que 0 significa que deu erro
     */
    public int salvarProdutosDAO(ModelProduto pModelProdutos) {
        try {
            this.conectar();
            return this.insertSQL("INSERT INTO tbl_produto ("
                    + "pro_nome,"
                    + "pro_valor,"
                    + "pro_estoque"
                    + ") VALUE ("
                    + "'" + pModelProdutos.getProNome() + "',"
                    + "'" + pModelProdutos.getProValor() + "',"
                    + "'" + pModelProdutos.getProEstoque() + "'"
                    + ");"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Excluir produto do banco de dados através do comando SQL
     *
     * @param pIdProduto
     * @return boolean verdadeiro ou falso
     */
    public boolean excluirProdutoDAO(int pIdProduto) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM tbl_produto WHERE pk_id_produto = '" + pIdProduto + "'"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }

    }

    /**
     * Alterar produto no banco de dados através do comando SQL UPDATE TBL
     * SET....
     *
     * @param pModelProdutos
     * @return FALSE
     */
    public boolean alterarProdutoDAO(ModelProduto pModelProdutos) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE tbl_produto SET "
                    + "pro_nome = '" + pModelProdutos.getProNome() + "',"
                    + "pro_valor ='" + pModelProdutos.getProValor() + "',"
                    + "pro_estoque = '" + pModelProdutos.getProEstoque() + "'"
                    + "WHERE pk_id_produto = '" + pModelProdutos.getIdProduto() + "'"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Busca o produto pelo código e salva na variavel modelProdutos
     *
     * @param pId_produto
     * @return modelProdutos
     */
    public ModelProduto retornarProdutoDAO(int pId_produto) {
        ModelProduto modelProdutos = new ModelProduto();
        try {
            this.conectar();
            this.executarSQL("SELECT pk_id_produto,"
                    + "pro_nome,"
                    + "pro_valor,"
                    + "pro_estoque "
                    + "FROM tbl_produto "
                    + "WHERE pk_id_produto = '" + pId_produto + "'");
            while (this.getResultSet().next()) {
                modelProdutos.setIdProduto(this.getResultSet().getInt(1));
                modelProdutos.setProNome(this.getResultSet().getString(2));
                modelProdutos.setProValor(this.getResultSet().getDouble(3));
                modelProdutos.setProEstoque(this.getResultSet().getInt(4));
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            this.fecharConexao();
        }
        return modelProdutos;
    }

    /**
     * Busca o produto pelo nome e salva na variavel modelProdutos
     *
     * @param pNomeProduto
     * @return modelProdutos
     */
    public ModelProduto retornarProdutoDAO(String pNomeProduto) {
        ModelProduto modelProdutos = new ModelProduto();
        try {
            this.conectar();
            this.executarSQL("SELECT pk_id_produto,pro_nome,pro_valor,pro_estoque FROM tbl_produto WHERE pro_nome = '" + pNomeProduto + "'");
            while (this.getResultSet().next()) {
                modelProdutos.setIdProduto(this.getResultSet().getInt(1));
                modelProdutos.setProNome(this.getResultSet().getString(2));
                modelProdutos.setProValor(this.getResultSet().getDouble(3));
                modelProdutos.setProEstoque(this.getResultSet().getInt(4));
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            this.fecharConexao();
        }
        return modelProdutos;
    }

    /**
     * Busca todos os produtos da tabela e cria um Array de vários ModelProduto
 com todos eles, o RS.Next vai percorrer toda a tabela até preencher um
 array completo com todos os produtos cadastrados.
     *
     * @return listaModelProdutos
     */
    public ArrayList<ModelProduto> retornarListaProdutosDAO() {
        ArrayList<ModelProduto> listaModelProdutos = new ArrayList<>();
        ModelProduto modelProdutos = new ModelProduto();
        try {
            this.conectar();
            this.executarSQL("SELECT "
                    + "pk_id_produto,"
                    + "pro_nome,"
                    + "pro_valor,"
                    + "pro_estoque FROM tbl_produto;");

            while (this.getResultSet().next()) {
                modelProdutos = new ModelProduto();
                modelProdutos.setIdProduto(this.getResultSet().getInt(1));
                modelProdutos.setProNome(this.getResultSet().getString(2));
                modelProdutos.setProValor(this.getResultSet().getDouble(3));
                modelProdutos.setProEstoque(this.getResultSet().getInt(4));
                listaModelProdutos.add(modelProdutos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listaModelProdutos;
    }

    /**
     * Este comando serve parar alterar a quantidade de estoque de um
     * determinado produto no meu banco de dados através do parametro
     *
     * @param pListaModelProdutos
     * @return
     */
    public boolean alterarEstoqueProdutosDao(ArrayList<ModelProduto> pListaModelProdutos) {
        try {
            this.conectar();
            int cont = pListaModelProdutos.size();
            for (int i = 0; i < cont; i++) {

                this.executarUpdateDeleteSQL(//este carinha que deve atualizar o estoque corretamente, se o estoque estiver sendo atualizado errado a culpa é dele.
                        "UPDATE tbl_produto SET "
                        + "pro_estoque = '" + pListaModelProdutos.get(i).getProEstoque() + "'"
                        + "WHERE pk_id_produto = '" + pListaModelProdutos.get(i).getIdProduto() + "'"
                );
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }

    }
    
    
    
      /**
     * Alterar o estoque dos produtos no banco de dados através do produto enviado
     * no parametro.
     *
     * @param pModelProduto
     * @return
     */
    public boolean alterarEstoqueProdutosDao(ModelProduto pModelProduto) {
        try {
            this.conectar();
            this.executarUpdateDeleteSQL(//este carinha que deve atualizar o estoque corretamente, se o estoque estiver sendo atualizado errado a culpa é dele.
                        "UPDATE tbl_produto SET "
                        + "pro_estoque = '" + pModelProduto.getProEstoque() + "'"
                        + "WHERE pk_id_produto = '" + pModelProduto.getIdProduto() + "'"
                );
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }

    }

}
