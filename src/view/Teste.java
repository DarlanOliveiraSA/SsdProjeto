/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.RootPaneUI;

/**
 *
 * @author user
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        List<Endereco> listaEnderecos = new ArrayList();
        listaEnderecos.add(new Endereco("Rua tal", "bairro tal"));
        listaEnderecos.add(new Endereco("Rua tal2222", "bairro tal222"));
        listaEnderecos.add(new Endereco("Rua tal2223", "bairro tal223"));
        
        MeuTableModel meuTableModel = new MeuTableModel(listaEnderecos);
        
        JTable jTable = new JTable(meuTableModel);
        
        JFrame painelFundo = new JFrame();
        painelFundo.setSize(500, 500);
        painelFundo.setLayout(new GridLayout(1, 1));
        painelFundo.add(new JScrollPane(jTable));
        painelFundo.setVisible(true);
        
    }
    
    
    
    public void popularjpanel(List<Endereco> listaEnd, JPanel painel,JFrame jframe){
        
        MeuTableModel meuTableModel = new MeuTableModel(listaEnd);
        
        JTable jTable = new JTable(meuTableModel);
        
        jframe = new JFrame();
        jframe.setSize(500, 500);
        jframe.setLayout(new GridLayout(1, 1));
        jframe.add(new JScrollPane(jTable));
        jframe.setVisible(true);
        jframe.validate();
        
        
        
    
    }
    
}
