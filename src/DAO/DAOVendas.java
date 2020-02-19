package DAO;

import model.ModelVendas;
import conexoes.ConexaoMySql;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.collections.map.HashedMap;

/**
 *
 * @author Darlan
 */
public class DAOVendas extends ConexaoMySql {

    /**
     * Salva a venda no banco de dados -- Retorna o ID da venda que está sendo
     * salva se estiver tudo certo, se deralgum erro retorna 0 (zero)
     *
     * @param pModelVendas return int
     * @return
     */
    public int salvarVendasDAO(ModelVendas pModelVendas) {
        try {

            this.conectar();
            return this.insertSQL(
                    "INSERT INTO tbl_vendas ("
                    + "fk_cliente,"
                    + "ven_data_venda,"
                    + "ven_valor_liquido,"
                    + "ven_valor_bruto,"
                    + "ven_desconto,"
                    + "fk_forPag,"
                    + "ven_valor_recebido"
                    + ") VALUES ("
                    + "'" + pModelVendas.getCliente() + "',"
                    + "'" + pModelVendas.getVenDataVenda() + "',"
                    + "'" + pModelVendas.getVenValorLiquido() + "',"
                    + "'" + pModelVendas.getVenValorBruto() + "',"
                    + "'" + pModelVendas.getVenDesconto() + "',"
                    + "'" + pModelVendas.getFormaPagamento() + "',"
                    + "'" + pModelVendas.getVenValorRecebido()+ "'"
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
     * busca no banco de dados a venda através do idDaVenda passado como
     * parametro
     *
     * @param pIdVenda return ModelVendas
     * @return
     */
    public ModelVendas getVendasDAO(int pIdVenda) {
        ModelVendas modelVendas = new ModelVendas();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_id_vendas,"
                    + "fk_cliente,"
                    + "ven_data_venda,"
                    + "ven_valor_liquido,"
                    + "ven_valor_bruto,"
                    + "ven_desconto,"
                    + "fk_forPag,"
                    + "ven_valor_recebido"
                    + " FROM"
                    + " tbl_vendas"
                    + " WHERE"
                    + " pk_id_vendas = '" + pIdVenda + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelVendas.setIdVenda(this.getResultSet().getInt(1));
                modelVendas.setCliente(this.getResultSet().getInt(2));
                modelVendas.setVenDataVenda(this.getResultSet().getDate(3));
                modelVendas.setVenValorLiquido(this.getResultSet().getDouble(4));
                modelVendas.setVenValorBruto(this.getResultSet().getDouble(5));
                modelVendas.setVenDesconto(this.getResultSet().getDouble(6));
                modelVendas.setFormaPagamento(this.getResultSet().getInt(7));
                modelVendas.setVenValorRecebido(this.getResultSet().getDouble(8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return modelVendas;
    }

    /**
     * busca no banco de dados todas as vendas que foram registradas e salva em
     * um arrayList ModelVendas return ArrayList
     *
     * @return
     */
    public ArrayList<ModelVendas> getListaVendasDAO() {
        ArrayList<ModelVendas> listamodelVendas = new ArrayList();
        ModelVendas modelVendas = new ModelVendas();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_id_vendas,"
                    + "fk_cliente,"
                    + "ven_data_venda,"
                    + "ven_valor_liquido,"
                    + "ven_valor_bruto,"
                    + "ven_desconto,"
                    + "fk_forPag,"
                    + "ven_valor_recebido"
                    + " FROM"
                    + " tbl_vendas"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelVendas = new ModelVendas();
                modelVendas.setIdVenda(this.getResultSet().getInt(1));
                modelVendas.setCliente(this.getResultSet().getInt(2));
                modelVendas.setVenDataVenda(this.getResultSet().getDate(3));
                modelVendas.setVenValorLiquido(this.getResultSet().getDouble(4));
                modelVendas.setVenValorBruto(this.getResultSet().getDouble(5));
                modelVendas.setVenDesconto(this.getResultSet().getDouble(6));
                modelVendas.setFormaPagamento(this.getResultSet().getInt(7));
                modelVendas.setVenValorRecebido(this.getResultSet().getInt(8));
                listamodelVendas.add(modelVendas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelVendas;
    }

    /**
     * Atualiza no banco de dados a venda passando como parametro um objeto
     * ModelVendas Lembrando que um model vendas contem os atributos (idVenda,
     * iDcliente, venDataVenda, venValorLiquido, venValorBruto, venDesconto)
     * este comando só funciona se o model venda passado como parametro tiver
     * todos estes atributos acima
     *
     * @param pModelVendas return boolean
     * @return
     */
    public boolean atualizarVendasDAO(ModelVendas pModelVendas) {
        try {

            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE tbl_vendas SET "
                    + "pk_id_vendas = '" + pModelVendas.getIdVenda() + "',"
                    + "fk_cliente = '" + pModelVendas.getCliente() + "',"
                    + "ven_data_venda = '" + pModelVendas.getVenDataVenda() + "',"
                    + "ven_valor_liquido = '" + pModelVendas.getVenValorLiquido() + "',"
                    + "ven_valor_bruto = '" + pModelVendas.getVenValorBruto() + "',"
                    + "ven_desconto = '" + pModelVendas.getVenDesconto() + "',"
                    + "fk_forPag = '" + pModelVendas.getFormaPagamento() + "',"
                    + "ven_valor_recebido = '" + pModelVendas.getVenValorRecebido()+ "'"
                    + " WHERE "
                    + "pk_id_vendas = '" + pModelVendas.getIdVenda() + "'"
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
     * Este comando exclui a venda do banco de dados através do id da venda
     * passada como parametro entre parenteses pIdVenda
     *
     * @param pIdVenda return boolean
     * @return
     */
    public boolean excluirVendasDAO(int pIdVenda) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM tbl_vendas "
                    + " WHERE "
                    + "pk_id_vendas = '" + pIdVenda + "'"
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
     * Recupera o ultimo ID da venda selecionada e retorna um inteiro
     *
     * @return int idObtido
     */
    public int recuperarUltimoID() {
        int idObtido = 0;
        try {
            this.conectar();
            this.executarSQL("SELECT MAX(tbl_vendas.pk_id_vendas) FROM tbl_vendas");

            while (this.getResultSet().next()) {
                idObtido = this.getResultSet().getInt(1);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return idObtido;

    }

    public boolean gerarRelatorioDAO(int codVenda) {
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT"
                    + "     tbl_cliente.pk_id_cliente AS tbl_cliente_pk_id_cliente,"
                    + "     tbl_cliente.cli_nome AS tbl_cliente_cli_nome,"
                    + "     tbl_cliente.cli_endereco AS tbl_cliente_cli_endereco,"
                    + "     tbl_cliente.cli_bairro AS tbl_cliente_cli_bairro,"
                    + "     tbl_cliente.cli_cidade AS tbl_cliente_cli_cidade,"
                    + "     tbl_cliente.cli_uf AS tbl_cliente_cli_uf,"
                    + "     tbl_cliente.cli_cep AS tbl_cliente_cli_cep,"
                    + "     tbl_cliente.cli_telefone AS tbl_cliente_cli_telefone,"
                    + "     tbl_cliente.cli_num_casa AS tbl_cliente_cli_num_casa,"
                    + "     tbl_cliente.cli_telCod AS tbl_cliente_cli_telCod,"
                    + "     tbl_produto.pk_id_produto AS tbl_produto_pk_id_produto,"
                    + "     tbl_produto.pro_nome AS tbl_produto_pro_nome,"
                    + "     tbl_produto.pro_valor AS tbl_produto_pro_valor,"
                    + "     tbl_produto.pro_estoque AS tbl_produto_pro_estoque,"
                    + "     tbl_vendas.pk_id_vendas AS tbl_vendas_pk_id_vendas,"
                    + "     tbl_vendas.fk_cliente AS tbl_vendas_fk_cliente,"
                    + "     tbl_vendas.ven_data_venda AS tbl_vendas_ven_data_venda,"
                    + "     tbl_vendas.ven_valor_liquido AS tbl_vendas_ven_valor_liquido,"
                    + "     tbl_vendas.ven_valor_bruto AS tbl_vendas_ven_valor_bruto,"
                    + "     tbl_vendas.ven_desconto AS tbl_vendas_ven_desconto,"
                    + "     tbl_vendas.fk_forPag AS tbl_vendas_fk_forPag,"
                    + "     tbl_vendas_produtos.pk_id_venda_produtos AS tbl_vendas_produtos_pk_id_venda_produtos,"
                    + "     tbl_vendas_produtos.fk_produto AS tbl_vendas_produtos_fk_produto,"
                    + "     tbl_vendas_produtos.fk_vendas AS tbl_vendas_produtos_fk_vendas,"
                    + "     tbl_vendas_produtos.ven_pro_valor AS tbl_vendas_produtos_ven_pro_valor,"
                    + "     tbl_vendas_produtos.ven_pro_quantidade AS tbl_vendas_produtos_ven_pro_quantidade, "
                    + "     tbl_cliente.cli_PontoReferencia AS tbl_cliente_cli_PontoReferencia "
                    + "FROM "
                    + "     tbl_produto INNER JOIN tbl_vendas_produtos ON tbl_produto.pk_id_produto = tbl_vendas_produtos.fk_produto"
                    + "     INNER JOIN tbl_vendas ON tbl_vendas_produtos.fk_vendas = tbl_vendas.pk_id_vendas"
                    + "     INNER JOIN tbl_cliente ON tbl_vendas.fk_cliente = tbl_cliente.pk_id_cliente WHERE tbl_vendas.pk_id_vendas = '" + codVenda + "';"
            );
            JRResultSetDataSource jrRS = new JRResultSetDataSource(getResultSet());
            //Caminho do relatório
            InputStream caminhoRelatorio = this.getClass().getClassLoader().getResourceAsStream("relatorios/RelVenda.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRelatorio, new HashedMap(), jrRS);
            File file;
            file = new File("C:/SSDprojeto/rel/RelVenda'" + codVenda + "'.pdf");

            try {
                Desktop.getDesktop().open(file);
            } catch (IllegalArgumentException iae) {

                JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/SSDprojeto/rel/RelVenda'" + codVenda + "'.pdf");
                Desktop.getDesktop().open(file);

            } catch (FileNotFoundException fnf) {
                JOptionPane.showMessageDialog(null, "Feche o arquivo atual e tente novamente");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e);
            }

            file.deleteOnExit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }

    }

}
