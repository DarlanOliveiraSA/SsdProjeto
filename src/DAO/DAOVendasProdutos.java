package DAO;

import model.ModelProdutosDaVenda;
import conexoes.ConexaoMySql;
import java.util.ArrayList;

/**
 *
 * @author Darlan
 */
public class DAOVendasProdutos extends ConexaoMySql {

    /**
     * grava VendasProdutos
     *
     * @param pModelVendasProdutos return int
     */
    public int salvarVendasProdutosDAO(ModelProdutosDaVenda pModelVendasProdutos) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO tbl_vendas_produtos ("
                    + "fk_produto,"
                    + "fk_vendas,"
                    + "ven_pro_valor,"
                    + "ven_pro_quantidade"
                    + ") VALUES ("
                    + "'" + pModelVendasProdutos.getIdDaVenda() + "',"
                    + "'" + pModelVendasProdutos.getProduto() + "',"
                    + "'" + pModelVendasProdutos.getVenProValor() + "',"
                    + "'" + pModelVendasProdutos.getVenProQuantidadeVendida() + "'"
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
     * recupera VendasProdutos
     *
     * @param pIdVendaProduto return ModelProdutosDaVenda
     */
    public ModelProdutosDaVenda getVendasProdutosDAO(int pIdVendaProduto) {
        ModelProdutosDaVenda modelVendasProdutos = new ModelProdutosDaVenda();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_id_venda_produtos,"
                    + "fk_produto,"
                    + "fk_vendas,"
                    + "ven_pro_valor,"
                    + "ven_pro_quantidade"
                    + " FROM"
                    + " tbl_vendas_produtos"
                    + " WHERE"
                    + " pk_id_venda_produtos = '" + pIdVendaProduto + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelVendasProdutos.setIdVendaProduto(this.getResultSet().getInt(1));
                modelVendasProdutos.setProduto(this.getResultSet().getInt(2));
                modelVendasProdutos.setIdDaVenda(this.getResultSet().getInt(3));
                modelVendasProdutos.setVenProValor(this.getResultSet().getDouble(4));
                modelVendasProdutos.setVenProQuantidadeVendida(this.getResultSet().getInt(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return modelVendasProdutos;
    }

    /**
     * recupera uma lista de VendasProdutos return ArrayList
     */
    public ArrayList<ModelProdutosDaVenda> getListaVendasProdutosDAO() {
        ArrayList<ModelProdutosDaVenda> listamodelVendasProdutos = new ArrayList();
        ModelProdutosDaVenda modelVendasProdutos = new ModelProdutosDaVenda();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_id_venda_produtos,"
                    + "fk_produto,"
                    + "fk_vendas,"
                    + "ven_pro_valor,"
                    + "ven_pro_quantidade"
                    + " FROM"
                    + " tbl_vendas_produtos"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelVendasProdutos = new ModelProdutosDaVenda();
                modelVendasProdutos.setIdVendaProduto(this.getResultSet().getInt(1));
                modelVendasProdutos.setProduto(this.getResultSet().getInt(2));
                modelVendasProdutos.setIdDaVenda(this.getResultSet().getInt(3));
                modelVendasProdutos.setVenProValor(this.getResultSet().getDouble(4));
                modelVendasProdutos.setVenProQuantidadeVendida(this.getResultSet().getInt(5));
                listamodelVendasProdutos.add(modelVendasProdutos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelVendasProdutos;
    }

    /**
     * atualiza VendasProdutos
     *
     * @param pModelVendasProdutos return boolean
     */
    public boolean atualizarVendasProdutosDAO(ModelProdutosDaVenda pModelVendasProdutos) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE tbl_vendas_produtos SET "
                    + "pk_id_venda_produto = '" + pModelVendasProdutos.getIdVendaProduto() + "',"
                    + "fk_produto = '" + pModelVendasProdutos.getProduto() + "',"
                    + "fk_vendas = '" + pModelVendasProdutos.getIdDaVenda() + "',"
                    + "ven_pro_valor = '" + pModelVendasProdutos.getVenProValor() + "',"
                    + "ven_pro_quantidade = '" + pModelVendasProdutos.getVenProQuantidadeVendida() + "'"
                    + " WHERE "
                    + "pk_id_venda_produto = '" + pModelVendasProdutos.getIdVendaProduto() + "'"
                    + ";"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Exclui uma VendasProdutos
     *
     * @param pIdVendaProduto return boolean
     */
    public boolean excluirVendasProdutosDAO(int pIdVendaProduto) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM tbl_vendas_produtos "
                    + " WHERE "
                    + "fk_vendas = '" + pIdVendaProduto + "'"
                    + ";"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Salva uma nova venda de produtos
     *
     * @param plistaModelVendasProdutos return boolean
     */
    public boolean salvarVendasProdutosDAO(ArrayList<ModelProdutosDaVenda> plistaModelVendasProdutos) {
        try {
            this.conectar();
            int cont = plistaModelVendasProdutos.size();
            for (int i = 0; i < cont; i++) {

                this.insertSQL(
                        "INSERT INTO tbl_vendas_produtos ("
                        + "fk_produto,"
                        + "fk_vendas,"
                        + "ven_pro_valor,"
                        + "ven_pro_quantidade"
                        + ") VALUES ("
                        + "'" + plistaModelVendasProdutos.get(i).getProduto() + "',"
                        + "'" + plistaModelVendasProdutos.get(i).getIdDaVenda() + "',"
                        + "'" + plistaModelVendasProdutos.get(i).getVenProValor() + "',"
                        + "'" + plistaModelVendasProdutos.get(i).getVenProQuantidadeVendida() + "'"
                        + ");"
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
     *  * ModelProdutosDaVenda é um construtor contendo "ID da Venda Realizada "
     * , fk_produto, fk_venda, venProValor, venProQuantidadeVendida
     *
     * @param pIdVenda
     * @return
     */
    public ArrayList<ModelProdutosDaVenda> getListaDeProdutosDaVendaDAO(int pIdVenda) {

        ArrayList<ModelProdutosDaVenda> listamodelVendasProdutos = new ArrayList();
        ModelProdutosDaVenda modelVendasProdutos = new ModelProdutosDaVenda();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_id_venda_produtos,"
                    + "fk_produto,"
                    + "fk_vendas,"
                    + "ven_pro_valor,"
                    + "ven_pro_quantidade"
                    + " FROM"
                    + " tbl_vendas_produtos"
                    + " WHERE"
                    + " fk_vendas = '" + pIdVenda + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelVendasProdutos = new ModelProdutosDaVenda();
                modelVendasProdutos.setIdVendaProduto(this.getResultSet().getInt(1));
                modelVendasProdutos.setProduto(this.getResultSet().getInt(2));
                modelVendasProdutos.setIdDaVenda(this.getResultSet().getInt(3));
                modelVendasProdutos.setVenProValor(this.getResultSet().getDouble(4));
                modelVendasProdutos.setVenProQuantidadeVendida(this.getResultSet().getInt(5));
                listamodelVendasProdutos.add(modelVendasProdutos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelVendasProdutos;

    }

}
