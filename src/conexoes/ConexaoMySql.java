package conexoes;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Darlan
 */
public class ConexaoMySql {

    private boolean status = false;
    private String mensagem = "";   //variavel que vai informar o status da conexao
    private Connection con = null;  //variavel para conexao
    private Statement statement;//
    private ResultSet resultSet;//O resultado da consulta, Tudo que eu tentar buscar vai ficar salvo aqui

    private String servidor = "mysql22.redehost.com.br:41890";//classe de conexão com o banco oline
    private String nomeDoBanco = "dbssd3";
    private String usuario = "darlan22";//usuário do banco online
    private String senha = "Ener06537-*";//senha do banco online
    public String ondeConectar = "";//Decide se vai usar o banco Online ou o offline

    public ConexaoMySql() {

    }

    public ConexaoMySql(String pServidor, String pNomeDoBanco, String pUsuario, String pSenha) {
        this.servidor = pServidor;
        this.nomeDoBanco = pNomeDoBanco;
        this.usuario = pUsuario;
        this.senha = pSenha;

    }

    /**
     * Abre uma conexao com o banco
     *
     * @return Connection
     */
    public Connection conectar() {

        if (null != ondeConectar) {
            switch (ondeConectar) {
                case "online":
                    System.out.println(ondeConectar + " Foi passado correto");
                    nomeDoBanco = "dbssd3";
                    servidor = "mysql22.redehost.com.br:41890";
                    senha = "Ener06537-*";
                    usuario = "darlan22";
                    break;
                case "offline":
                    //System.out.println(ondeConectar + " Foi passado correto");
                    nomeDoBanco = "dbssd";
                    servidor = "localhost";
                    senha = "";
                    usuario = "root";
                    break;
                default:
                    //System.out.println("offline pois não foi selecionado nenhum");
                    nomeDoBanco = "dbssd";
                    servidor = "localhost";
                    senha = "";
                    usuario = "root";
                    break;
            }
        }

        try {
            //Driver do MySQL
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            //Servidor, nome do banco, usuario e senha
            String url = "jdbc:mysql://" + servidor + "/" + nomeDoBanco;
            this.setCon((Connection) DriverManager.getConnection(url, usuario, senha));

            //se ocorrer tudo bem, ou seja, se conectar a linha a segui é executada
            this.status = true;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return this.getCon();

    }

    /**
     * Executa qualquer comando no banco de dados
     *
     * @param pSQL
     * @return int
     */
    public boolean executarSQL(String pSQL) {

        try {
            /**
             * SetStatement serve para setar uma instrução para a
             * conexão,através dos dados de conexão getCon foi tipo o banco
             * escolhido, é uma conexão ativa com o banco Statement é uma
             * interface utilizada para executar instruções SQL.
             *
             */
            this.setStatement(getCon().createStatement());

            // Definido o Statement, executamos a query no banco de dados
            this.setResultSet(getStatement().executeQuery(pSQL));

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
        return true;
        
    }

    public boolean executarUpdateDeleteSQL(String pSQL) {
        try {

            //createStatement de con para criar o Statement
            this.setStatement(getCon().createStatement());

            // Definido o Statement, executamos a linha de comando "chamado de query" no banco de dados
            getStatement().executeUpdate(pSQL);

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Executa insert SQL
     *
     * @param pSQL
     * @return boolean
     */
    public int insertSQL(String pSQL) {
        int status = 0;
        try {
            //createStatement de con para criar o Statement
            this.setStatement(getCon().createStatement());

            // Definido o Statement, executamos a query no banco de dados
            this.getStatement().executeUpdate(pSQL);

            //consulta o ultimo id inserido
            this.setResultSet(this.getStatement().executeQuery("SELECT last_insert_id();"));

            //recupera o ultimo id inserido
            while (this.resultSet.next()) {
                status = this.resultSet.getInt(1);
            }

            //retorna o ultimo id inserido
            return status;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return status;
        }
    }

    /**
     * encerra a conexão corrente
     *
     * @return boolean
     */
    public boolean fecharConexao() {
        try {
            if ((this.getResultSet() != null) && (this.statement != null)) {
                this.getResultSet().close();
                this.statement.close();
            }
            this.getCon().close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return this.status;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @return the statement
     */
    public Statement getStatement() {
        return statement;
    }

    /**
     * O resultado da consulta, Tudo que eu tentar buscar vai ficar salvo aqui
     *
     * @return the resultSet
     */
    public ResultSet getResultSet() {
        return resultSet;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }

    /**
     * @param con the con to set
     */
    public void setCon(Connection con) {
        this.con = con;
    }

    /**
     * Statement é uma interface utilizada para executar instruções SQL.
     *
     * @param statement the statement to set
     */
    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    /**
     * @param resultSet the resultSet to set
     */
    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    /**
     * @return the servidor
     */
    public String getServidor() {
        return servidor;
    }

    /**
     * @param servidor the servidor to set
     */
    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    /**
     * @return the nomeDoBanco
     */
    public String getNomeDoBanco() {
        return nomeDoBanco;
    }

    /**
     * @param nomeDoBanco the nomeDoBanco to set
     */
    public void setNomeDoBanco(String nomeDoBanco) {
        this.nomeDoBanco = nomeDoBanco;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Este get foi criado para definir em qual banco de dados a classe
     * ConexãoMysql vai se conectar, online para o banco do site e qualquer
     * outro estado conecta no banco local isto só foi usado para validar o
     * login do cliente para saber se a mensalidade está em dia.
     *
     * @return the ondeConectar
     */
    public String getOndeConectar() {
        return this.ondeConectar;
    }

    /**
     * Este set foi criado para definir em qual banco de dados a classe
     * ConexãoMysql vai se conectar, online para o banco do site e qualquer
     * outro estado conecta no banco local isto só foi usado para validar o
     * login do cliente para saber se a mensalidade está em dia.
     *
     * @param pOndeConectar the ondeConectar to set
     */
    public void setOndeConectar(String pOndeConectar) {
        this.ondeConectar = pOndeConectar;
    }

}
