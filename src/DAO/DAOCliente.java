package DAO;

import model.ModelCliente;
import conexoes.ConexaoMySql;
import java.awt.Desktop;
import java.io.File;
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
public class DAOCliente extends ConexaoMySql {

    /**
     * grava Cliente
     *
     * @param pModelCliente return int
     * @return 
     */
    public int salvarClienteDAO(ModelCliente pModelCliente) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO tbl_cliente ("
                    + "cli_nome,"
                    + "cli_endereco,"
                    + "cli_bairro,"
                    + "cli_cidade,"
                    + "cli_uf,"
                    + "cli_cep,"
                    + "cli_telefone,"
                    + "cli_num_casa,"
                    + "cli_telCod,"
                    + "cli_PontoReferencia"
                    + ") VALUES ("
                    + "'" + pModelCliente.getCliNome() + "',"
                    + "'" + pModelCliente.getCliEndereco() + "',"
                    + "'" + pModelCliente.getCliBairro() + "',"
                    + "'" + pModelCliente.getCliCidade() + "',"
                    + "'" + pModelCliente.getCliUf() + "',"
                    + "'" + pModelCliente.getCliCep() + "',"
                    + "'" + pModelCliente.getCliTelefone() + "',"
                    + "'" + pModelCliente.getCliNumCasa() + "',"
                    + "'" + pModelCliente.getCliDDDTel() + "',"
                    + "'" + pModelCliente.getCliPontoReferencia() + "'"
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
     * recupera Cliente
     *
     * @param pIdCliente return ModelCliente
     */
    public ModelCliente getClienteDAO(int pIdCliente) {
        ModelCliente modelCliente = new ModelCliente();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_id_cliente,"
                    + "cli_nome,"
                    + "cli_endereco,"
                    + "cli_bairro,"
                    + "cli_cidade,"
                    + "cli_uf,"
                    + "cli_cep,"
                    + "cli_telefone,"
                    + "cli_num_casa,"
                    + "cli_telCod,"
                    + "cli_PontoReferencia"
                    + " FROM"
                    + " tbl_cliente"
                    + " WHERE"
                    + " pk_id_cliente = '" + pIdCliente + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelCliente.setIdCliente(this.getResultSet().getInt(1));
                modelCliente.setCliNome(this.getResultSet().getString(2));
                modelCliente.setCliEndereco(this.getResultSet().getString(3));
                modelCliente.setCliBairro(this.getResultSet().getString(4));
                modelCliente.setCliCidade(this.getResultSet().getString(5));
                modelCliente.setCliUf(this.getResultSet().getString(6));
                modelCliente.setCliCep(this.getResultSet().getString(7));
                modelCliente.setCliTelefone(this.getResultSet().getString(8));
                modelCliente.setCliNumCasa(this.getResultSet().getString(9));
                modelCliente.setCliDDDTel(this.getResultSet().getString(10));
                modelCliente.setCliPontoReferencia(this.getResultSet().getString(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return modelCliente;
    }

    /**
     * recupera Cliente pelo nome
     *
     * @param pNomeCliente return ModelCliente
     */
    public ModelCliente getClienteDAO(String pNomeCliente) {
        ModelCliente modelCliente = new ModelCliente();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_id_cliente,"
                    + "cli_nome,"
                    + "cli_endereco,"
                    + "cli_bairro,"
                    + "cli_cidade,"
                    + "cli_uf,"
                    + "cli_cep,"
                    + "cli_telefone,"
                    + "cli_num_casa,"
                    + "cli_telCod,"
                    + "cli_PontoReferencia"
                    + " FROM"
                    + " tbl_cliente"
                    + " WHERE"
                    + " cli_nome = '" + pNomeCliente + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelCliente.setIdCliente(this.getResultSet().getInt(1));
                modelCliente.setCliNome(this.getResultSet().getString(2));
                modelCliente.setCliEndereco(this.getResultSet().getString(3));
                modelCliente.setCliBairro(this.getResultSet().getString(4));
                modelCliente.setCliCidade(this.getResultSet().getString(5));
                modelCliente.setCliUf(this.getResultSet().getString(6));
                modelCliente.setCliCep(this.getResultSet().getString(7));
                modelCliente.setCliTelefone(this.getResultSet().getString(8));
                modelCliente.setCliNumCasa(this.getResultSet().getString(9));
                modelCliente.setCliDDDTel(this.getResultSet().getString(10));
                modelCliente.setCliPontoReferencia(this.getResultSet().getString(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return modelCliente;
    }

    /**
     * recupera Cliente pelo Telefone
     *
     * @param pTelefone
     * @return
     */
    public ModelCliente getClienteTelefone(String pTelefone) {
        ModelCliente modelCliente = new ModelCliente();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_id_cliente,"
                    + "cli_nome,"
                    + "cli_endereco,"
                    + "cli_bairro,"
                    + "cli_cidade,"
                    + "cli_uf,"
                    + "cli_cep,"
                    + "cli_telefone,"
                    + "cli_num_casa,"
                    + "cli_telCod,"
                    + "cli_PontoReferencia"
                    + " FROM"
                    + " tbl_cliente"
                    + " WHERE"
                    + " cli_telefone LIKE '%" + pTelefone + "%'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelCliente.setIdCliente(this.getResultSet().getInt(1));
                modelCliente.setCliNome(this.getResultSet().getString(2));
                modelCliente.setCliEndereco(this.getResultSet().getString(3));
                modelCliente.setCliBairro(this.getResultSet().getString(4));
                modelCliente.setCliCidade(this.getResultSet().getString(5));
                modelCliente.setCliUf(this.getResultSet().getString(6));
                modelCliente.setCliCep(this.getResultSet().getString(7));
                modelCliente.setCliTelefone(this.getResultSet().getString(8));
                modelCliente.setCliNumCasa(this.getResultSet().getString(9));
                modelCliente.setCliDDDTel(this.getResultSet().getString(10));
                modelCliente.setCliPontoReferencia(this.getResultSet().getString(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return modelCliente;
    }

    /**
     * recupera uma lista de Cliente return ArrayList
     *
     * @return
     */
    public ArrayList<ModelCliente> getListaClienteDAO() {
        ArrayList<ModelCliente> listamodelCliente = new ArrayList();
        ModelCliente modelCliente = new ModelCliente();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_id_cliente,"
                    + "cli_nome,"
                    + "cli_endereco,"
                    + "cli_bairro,"
                    + "cli_cidade,"
                    + "cli_uf,"
                    + "cli_cep,"
                    + "cli_telefone,"
                    + "cli_num_casa,"
                    + "cli_telCod,"
                    + "cli_PontoReferencia"
                    + " FROM"
                    + " tbl_cliente"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelCliente = new ModelCliente();
                modelCliente.setIdCliente(this.getResultSet().getInt(1));
                modelCliente.setCliNome(this.getResultSet().getString(2));
                modelCliente.setCliEndereco(this.getResultSet().getString(3));
                modelCliente.setCliBairro(this.getResultSet().getString(4));
                modelCliente.setCliCidade(this.getResultSet().getString(5));
                modelCliente.setCliUf(this.getResultSet().getString(6));
                modelCliente.setCliCep(this.getResultSet().getString(7));
                modelCliente.setCliTelefone(this.getResultSet().getString(8));
                modelCliente.setCliNumCasa(this.getResultSet().getString(9));
                modelCliente.setCliDDDTel(this.getResultSet().getString(10));
                modelCliente.setCliPontoReferencia(this.getResultSet().getString(11));
                listamodelCliente.add(modelCliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelCliente;
    }

    /**
     * atualiza Cliente
     *
     * @param pModelCliente return boolean
     */
    public boolean atualizarClienteDAO(ModelCliente pModelCliente) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE tbl_cliente SET "
                    + "pk_id_cliente = '" + pModelCliente.getIdCliente() + "',"
                    + "cli_nome = '" + pModelCliente.getCliNome() + "',"
                    + "cli_endereco = '" + pModelCliente.getCliEndereco() + "',"
                    + "cli_bairro = '" + pModelCliente.getCliBairro() + "',"
                    + "cli_cidade = '" + pModelCliente.getCliCidade() + "',"
                    + "cli_uf = '" + pModelCliente.getCliUf() + "',"
                    + "cli_cep = '" + pModelCliente.getCliCep() + "',"
                    + "cli_telefone = '" + pModelCliente.getCliTelefone() + "',"
                    + "cli_num_casa = '" + pModelCliente.getCliNumCasa() + "',"
                    + "cli_telCod = '" + pModelCliente.getCliDDDTel() + "',"
                    + "cli_PontoReferencia = '" + pModelCliente.getCliPontoReferencia() + "'"
                    + " WHERE "
                    + "pk_id_cliente = '" + pModelCliente.getIdCliente() + "'"
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
     * exclui Cliente
     *
     * @param pIdCliente return boolean
     */
    public boolean excluirClienteDAO(int pIdCliente) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM tbl_cliente "
                    + " WHERE "
                    + "pk_id_cliente = '" + pIdCliente + "'"
                    + ";"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    public boolean gerarRelatorioCliente() {
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT"
                    + "     tbl_cliente.pk_id_cliente AS tbl_cliente_pk_id_cliente, "
                    + "     tbl_cliente.cli_nome AS tbl_cliente_cli_nome, "
                    + "     tbl_cliente.cli_endereco AS tbl_cliente_cli_endereco, "
                    + "     tbl_cliente.cli_bairro AS tbl_cliente_cli_bairro, "
                    + "     tbl_cliente.cli_cidade AS tbl_cliente_cli_cidade, "
                    + "     tbl_cliente.cli_uf AS tbl_cliente_cli_uf, "
                    + "     tbl_cliente.cli_cep AS tbl_cliente_cli_cep, "
                    + "     tbl_cliente.cli_telefone AS tbl_cliente_cli_telefone "
                    + "FROM"
                    + "     tbl_cliente"
            );
            JRResultSetDataSource jrRS = new JRResultSetDataSource(getResultSet());
            //Caminho do relatório
            InputStream caminhoRelatorio = this.getClass().getClassLoader().getResourceAsStream("relatorios/ListaDeClientes.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRelatorio, new HashedMap(), jrRS);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/SSDprojeto/rel/RelClientes.pdf");
            File file = new File("C:/SSDprojeto/rel/RelClientes.pdf");
            try {
                Desktop.getDesktop().open(file);
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e);
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

    public ArrayList<ModelCliente> getCliNomeTelEndereco(ModelCliente pCliente) {
        ArrayList<ModelCliente> listaModelCliente = new ArrayList<>();
        ModelCliente modelCliente = new ModelCliente();

        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_id_cliente,"
                    + "cli_nome,"
                    + "cli_endereco,"
                    + "cli_bairro,"
                    + "cli_cidade,"
                    + "cli_uf,"
                    + "cli_cep,"
                    + "cli_telefone,"
                    + "cli_num_casa,"
                    + "cli_telCod,"
                    + "cli_PontoReferencia"
                    + " FROM"
                    + " tbl_cliente"
                    + " WHERE"
                    + " cli_telefone LIKE '%"
                    + pCliente.getCliTelefone() + "%' AND cli_nome LIKE '%"
                    + pCliente.getCliNome() + "%' AND cli_endereco LIKE '%"
                    + pCliente.getCliEndereco() + "%' "
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelCliente = new ModelCliente();
                modelCliente.setIdCliente(this.getResultSet().getInt(1));
                modelCliente.setCliNome(this.getResultSet().getString(2));
                modelCliente.setCliEndereco(this.getResultSet().getString(3));
                modelCliente.setCliBairro(this.getResultSet().getString(4));
                modelCliente.setCliCidade(this.getResultSet().getString(5));
                modelCliente.setCliUf(this.getResultSet().getString(6));
                modelCliente.setCliCep(this.getResultSet().getString(7));
                modelCliente.setCliTelefone(this.getResultSet().getString(8));
                modelCliente.setCliNumCasa(this.getResultSet().getString(9));
                modelCliente.setCliDDDTel(this.getResultSet().getString(10));
                modelCliente.setCliPontoReferencia(this.getResultSet().getString(11));
                listaModelCliente.add(modelCliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }

        return listaModelCliente;

    }

}
