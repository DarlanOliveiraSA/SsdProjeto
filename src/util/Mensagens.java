package util;

import model.ModelSessaoUsuario;

/**
 * Nesta classe será apresentada todas as mensagens do sistema
 *
 * @author Darlan
 */
public class Mensagens {

    ModelSessaoUsuario modelSessaoUsuario = new ModelSessaoUsuario();
    BLDatas calendario = new BLDatas();
    public String usuarioLogado = modelSessaoUsuario.nome;
    public String dataHoraAtual = calendario.retornarDataHora();

    /**
     * @return the usuarioLogado
     */
    public String getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * @param usuarioLogado the usuarioLogado to set
     */
    public void setUsuarioLogado(String usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

}
