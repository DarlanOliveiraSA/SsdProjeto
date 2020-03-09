/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.GridLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class MeuTableModel extends AbstractTableModel {

    private final String[] mNomesColunas = {
        "Rua", //indice 0 do vetor
        "Bairro", //indice 1 do vetor
        "Cidade", //indice 1 do vetor
        "UF" //indice 1 do vetor
    };

    private final List<Endereco> mLista;

    public MeuTableModel(List<Endereco> pListaEndereco) {

        mLista = pListaEndereco;
    }

    @Override

    public int getRowCount() {
        if (mLista == null) {
            return 0;
        }
        return mLista.size();
    }

    @Override
    public int getColumnCount() {
        return mNomesColunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {

            case 0:
                return mLista.get(linha).getRua();
            case 1:
                return mLista.get(linha).getBairro();
            case 2:
                return mLista.get(linha).getCidade();
            case 3:
                return mLista.get(linha).getEstado();
            default:
                return 0;
        }

    }
    
    //Opcionais porem importantes
    @Override
    public String getColumnName(int indice){
        
        return mNomesColunas[indice];
}
    
    public Class getColClass(int coluna){
     switch (coluna) {

            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            default:
                return null;
        }
    }
    
    public Endereco getEndereco(int linha){
    
        Endereco enderecoDaLinha = new Endereco();
        
        enderecoDaLinha.setBairro(mLista.get(linha).getBairro());
        enderecoDaLinha.setRua(mLista.get(linha).getRua());
        enderecoDaLinha.setCidade(mLista.get(linha).getCidade());
        enderecoDaLinha.setEstado(mLista.get(linha).getEstado());
        
        return enderecoDaLinha;
    }
    
    public void popularjpanel(JPanel painel, JFrame jframe) {

        MeuTableModel meuTableModel = new MeuTableModel(mLista);

        JTable jTable = new JTable(meuTableModel);
        painel.removeAll();

        painel.setLayout(new GridLayout(1, 1));
        painel.add(new JScrollPane(jTable));
        painel.setVisible(true);
        painel.validate();

    }
    

}
