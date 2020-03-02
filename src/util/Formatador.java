package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Darlan
 */
public class Formatador {

    String caminhoImagemNova = "src/imagens/";

    /**
     * Converte a virgula em ponto e retorna o a String em double
     *
     * @param pString
     * @return double
     */
    public double converterVirgulaParaPontoReturn(String pString) {
        String retorno = new String();
        int tamanhoString = pString.length();
        for (int i = 0; i < tamanhoString; i++) {
            if (pString.charAt(i) == ',') {
                retorno += '.';
            } else {
                retorno += pString.charAt(i);
            }
        }
        return Double.parseDouble(retorno);

    }

    public ImageIcon redimensionarLogo(JLabel pjLDestino, String pNomeArquivo) {
        String imagemNova = caminhoImagemNova + pNomeArquivo + "1.jpg";

        ImageIcon pImagem = new ImageIcon();
        try {
            pImagem = new ImageIcon(imagemNova);

            double imagemFileL = pImagem.getIconWidth();
            double imagemFileA = pImagem.getIconHeight();
            double larguraDaLabel = pjLDestino.getWidth();
            double alturaDaLabel = pjLDestino.getHeight();

            double hipotenusa = Math.sqrt(imagemFileA + imagemFileL); // hipotenusa = raiz de lado vezes altura 7,74
            double hipotenusaReduzidaPelaLarguradaLabel = (larguraDaLabel * hipotenusa) / imagemFileL; //regra de 3 para achar a hipotenusa reduzida x = largura atual * hipotenusa / largura da imagem
            double novaAlturaImagem = (hipotenusaReduzidaPelaLarguradaLabel * imagemFileA) / hipotenusa; //regra de 3

            imagemFileA = novaAlturaImagem;
            imagemFileL = larguraDaLabel;

            if (novaAlturaImagem > alturaDaLabel) {
                hipotenusa = Math.sqrt(imagemFileA + imagemFileL); // hipotenusa = raiz de lado vezes altura 7,74
                double hipotenusaReduzidaPelaAlturaDaLabel = (alturaDaLabel * hipotenusa) / imagemFileA; //regra de 3 para achar a hipotenusa reduzida x = largura atual * hipotenusa / largura da imagem
                double novaLargura = (hipotenusaReduzidaPelaAlturaDaLabel * imagemFileL) / hipotenusa; //regra de 3
                imagemFileA = alturaDaLabel;
                imagemFileL = novaLargura;
            } else {
                hipotenusa = Math.sqrt(imagemFileA + imagemFileL); // hipotenusa = raiz de lado vezes altura 7,74
                hipotenusaReduzidaPelaLarguradaLabel = (larguraDaLabel * hipotenusa) / imagemFileL; //regra de 3 para achar a hipotenusa reduzida x = largura atual * hipotenusa / largura da imagem
                novaAlturaImagem = (hipotenusaReduzidaPelaLarguradaLabel * imagemFileA) / hipotenusa; //regra de 3

            }

            pImagem.setImage(pImagem.getImage().getScaledInstance((int) imagemFileL, (int) imagemFileA, 1));
            pjLDestino.setIcon(pImagem);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Você precisa escolher uma logo pois não foi possível localizar uma na pasta padrão.", "Erro : 2802201258", JOptionPane.OK_OPTION);

            escolherLogo(pjLDestino, pNomeArquivo);

        }

        return pImagem;
    }

    public void escolherLogo(JLabel jlabelOndeFicaLogo, String pNomeArquivo) {
        String imagemNova = caminhoImagemNova + pNomeArquivo + "1.jpg";

        JFileChooser arqEscolhido = new JFileChooser();
        File ultimaPasta = new File(caminhoImagemNova);
        File logoDoCliente = new File(imagemNova);
        

        arqEscolhido.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter extensãoDasImagens = new FileNameExtensionFilter("Arquivos JPG", "jpg", "png");
        arqEscolhido.addChoosableFileFilter(extensãoDasImagens);
        arqEscolhido.setAcceptAllFileFilterUsed(false);

        try {
            String pastaOndeFicaALogo = arqEscolhido.getSelectedFile().getAbsolutePath();
            ultimaPasta = new File(pastaOndeFicaALogo);
            arqEscolhido.setCurrentDirectory(ultimaPasta);
        } catch (NullPointerException e) {
            arqEscolhido.setCurrentDirectory(ultimaPasta);
        }

        if (arqEscolhido.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String caminhoDoArquivo = arqEscolhido.getSelectedFile().getAbsolutePath();

            ImageIcon imagem = new ImageIcon(caminhoDoArquivo);

            imagem = ajustarImgEmLabel(imagem, jlabelOndeFicaLogo);

            jlabelOndeFicaLogo.setIcon(imagem);
            File file = new File(caminhoDoArquivo);
            arqEscolhido.setCurrentDirectory(file);
            try {

                salvarArquivoEmNovoCaminho(file, logoDoCliente);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }

    private void salvarArquivoEmNovoCaminho(File fonte, File destino) throws IOException {

        OutputStream out;
        try (InputStream in = new FileInputStream(fonte)) {
            out = new FileOutputStream(destino);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        }
        out.close();
    }

    /**
     * Este método redimensiona a imagem considerando o tamanho da label. Ele
     * ajusta automaticamente ao ponto menor, supondo que a imagem seja mais
     * larga que a label. O método reduz a escala da imagem para se encaixar na
     * largura. E se a largura for menor ele se ajusta também a altura sem
     * perder a proporção.
     *
     * @param pImagem
     * @return
     */
    public ImageIcon ajustarImgEmLabel(ImageIcon pImagem, JLabel plogo) {

        double larguraDaImagem = pImagem.getIconHeight();
        double alturaDaImagem = pImagem.getIconWidth();

        double larguraDaLabel = plogo.getWidth();
        double alturaDaLabel = plogo.getHeight();

        //Encontra a distancia de um ponto a outro da imágem cruzando pelo centro.
        double distanciaCentral = Math.sqrt(larguraDaImagem + alturaDaImagem); // distanciaCentral = raiz de lado vezes altura
        double percentReducaoPelaLargura = (larguraDaLabel * distanciaCentral) / alturaDaImagem; //regra de 3 para achar o percentual de redução x = largura atual * distanciaCentral / largura da imagem
        double novaAlturaImagem = (percentReducaoPelaLargura * larguraDaImagem) / distanciaCentral; //regra de 3

        larguraDaImagem = novaAlturaImagem;
        alturaDaImagem = larguraDaLabel;

        if (novaAlturaImagem > alturaDaLabel) {
            distanciaCentral = Math.sqrt(larguraDaImagem + alturaDaImagem); // distanciaCentral = raiz de lado vezes altura
            double hipotenusaReduzidaPelaAlturaDaLabel = (alturaDaLabel * distanciaCentral) / larguraDaImagem; //regra de 3 para achar o percentual de redução x = largura atual * distanciaCentral / largura da imagem
            double novaLargura = (hipotenusaReduzidaPelaAlturaDaLabel * alturaDaImagem) / distanciaCentral; //regra de 3
            larguraDaImagem = alturaDaLabel;
            alturaDaImagem = novaLargura;
        } else {
            distanciaCentral = Math.sqrt(larguraDaImagem + alturaDaImagem); // distanciaCentral = raiz de lado vezes altura
            percentReducaoPelaLargura = (larguraDaLabel * distanciaCentral) / alturaDaImagem; //regra de 3 para achar o percentual de redução x = largura atual * distanciaCentral / largura da imagem
            novaAlturaImagem = (percentReducaoPelaLargura * larguraDaImagem) / distanciaCentral; //regra de 3

        }

        pImagem.setImage(pImagem.getImage().getScaledInstance((int) alturaDaImagem, (int) larguraDaImagem, 1));

        return pImagem;
    }

}
