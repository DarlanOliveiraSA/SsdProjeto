package util;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Darlan
 */
public class Formatador {

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

    public ImageIcon redimensionarLogo(JLabel pjLDestino) {
        ImageIcon pImagem = new ImageIcon(getClass().getResource("/imagens/Fundos/Logo SSD Rev00.png"));

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

        return pImagem;
    }

}
