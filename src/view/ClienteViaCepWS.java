/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author user
 */
public class ClienteViaCepWS {

    private static ArrayList<Endereco> listaDeEnderecos = new ArrayList<Endereco>();

    private static String rua;
    private static String bairro;
    private static String cidade;
    private static String estado;
    private static String cep;

    /**
     * @return the listaDeEnderecos
     */
    public static ArrayList<Endereco> getListaDeEnderecos() {
        return listaDeEnderecos;
    }

    /**
     * @param aListaDeEnderecos the listaDeEnderecos to set
     */
    public static void setListaDeEnderecos(ArrayList<Endereco> aListaDeEnderecos) {
        listaDeEnderecos = aListaDeEnderecos;
    }

    public ClienteViaCepWS() {

    }

    public static void main(String[] args) throws IOException {
        //String json = buscarCep("30530600").toString();
        String json = buscarCepRua("MG", "rua", "belo horizonte").toString();
        System.out.println(" JSONN " + json);
// 
//        Map<String, String> mapa = new HashMap<>();
//
//        Matcher matcher = Pattern.compile("\"\\D.*?\": \".*?\"").matcher(json);
//        while (matcher.find()) {
//            String[] group = matcher.group().split(":");
//            mapa.put(group[0].replaceAll("\"", "").trim(), group[1].replaceAll("\"", "").trim());
//        }
//
//        //System.out.println(mapa);
//        JSONObject arquivoJson = new JSONObject();
//        arquivoJson.putAll(mapa);

    }

    public static JSONObject buscarCep(String cep) {
        JSONObject json = new JSONObject();

        try {
            URL url = new URL("http://viacep.com.br/ws/" + cep + "/json");
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();

            String inputLine;

            while ((inputLine = br.readLine()) != null) {
                jsonSb.append(inputLine);
            }
            br.close();
            //*************************************
            String jsonStringParaMapa = jsonSb.toString();
            Map<String, String> mapa = new HashMap<>();

            Matcher matcher = Pattern.compile("\"\\D.*?\": \".*?\"").matcher(jsonStringParaMapa);
            while (matcher.find()) {
                String[] group = matcher.group().split(":");
                mapa.put(group[0].replaceAll("\"", "").trim(), group[1].replaceAll("\"", "").trim());
            }

            //System.out.println(mapa);
            JSONObject arquivoJson = new JSONObject();
            arquivoJson.putAll(mapa);

            json.putAll(mapa);
            setRua(arquivoJson.get("logradouro").toString());
            setCep(arquivoJson.get("cep").toString());
            setEstado(arquivoJson.get("uf").toString());
            setCidade(arquivoJson.get("localidade").toString());
            setBairro(arquivoJson.get("bairro").toString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return json;
    }

    public static JSONObject buscarCepRua(String uf, String rua, String cidade) {
        JSONObject json = new JSONObject();

        try {
            //URL url = new URL("http://viacep.com.br/ws/" + cep + "/json");
            //https://viacep.com.br/ws/MG/CONTAGEM/dezenove/json/
            URL url = new URL("http://viacep.com.br/ws/" + uf + "/" + cidade + "/" + rua + "/json");
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();

            String inputLine;

            while ((inputLine = br.readLine()) != null) {
                jsonSb.append(inputLine);
            }
            br.close();
            //*************************************
            String jsonStringParaMapa = jsonSb.toString();//String completa do json

            System.out.println(jsonStringParaMapa + "bbbbbbbbbb");
            try (FileWriter arqJson = new FileWriter("programadores2.json")) {

                arqJson.write(jsonStringParaMapa);
                arqJson.flush();

            } catch (Exception e) {
            }

            Map<String, String> mapa = new HashMap<>();

            Matcher matcher = Pattern.compile("\"\\D.*?\": \".*?\"").matcher(jsonStringParaMapa);
            while (matcher.find()) {
                String[] group = matcher.group().split(":");
                mapa.put(group[0].replaceAll("\"", "").trim(), group[1].replaceAll("\"", "").trim());
            }

            //System.out.println(mapa);
            JSONObject arquivoJson = new JSONObject();
            arquivoJson.putAll(mapa);
            json.putAll(mapa);

            try {
                JSONParser jsonParser = new JSONParser();
                Object obj = jsonParser.parse(jsonStringParaMapa);
                JSONArray programadoresArray = (JSONArray) obj;

                listaDeEnderecos = new ArrayList<Endereco>();

                for (int i = 0; i < programadoresArray.size(); i++) {
                    Object objteste;
                    Endereco enderecoCadastro = new Endereco();

                    objteste = jsonParser.parse(programadoresArray.get(i).toString());

                    JSONObject endereco = (JSONObject) objteste;

                    enderecoCadastro.setBairro(endereco.get("bairro").toString());
                    enderecoCadastro.setRua(endereco.get("logradouro").toString());
                    enderecoCadastro.setCidade(endereco.get("localidade").toString());
                    enderecoCadastro.setEstado(endereco.get("uf").toString());

                    listaDeEnderecos.add(enderecoCadastro);

                    System.out.println(listaDeEnderecos.size());

                }

            } catch (Exception e) {
            }

            setRua(arquivoJson.get("logradouro").toString());
            setCep(arquivoJson.get("cep").toString());
            setEstado(arquivoJson.get("uf").toString());
            setCidade(arquivoJson.get("localidade").toString());
            setBairro(arquivoJson.get("bairro").toString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return json;
    }

    @Override
    public String toString() {

        return rua + " - " + bairro + " - " + cidade + " - " + estado + " - " + cep;
    }

    /**
     * @return the rua
     */
    public static String getRua() {
        return rua;
    }

    /**
     * @param aRua the rua to set
     */
    public static void setRua(String aRua) {
        rua = aRua;
    }

    /**
     * @return the bairro
     */
    public static String getBairro() {
        return bairro;
    }

    /**
     * @param aBairro the bairro to set
     */
    public static void setBairro(String aBairro) {
        bairro = aBairro;
    }

    /**
     * @return the cidade
     */
    public static String getCidade() {
        return cidade;
    }

    /**
     * @param aCidade the cidade to set
     */
    public static void setCidade(String aCidade) {
        cidade = aCidade;
    }

    /**
     * @return the estado
     */
    public static String getEstado() {
        return estado;
    }

    /**
     * @param aEstado the estado to set
     */
    public static void setEstado(String aEstado) {
        estado = aEstado;
    }

    /**
     * @return the cep
     */
    public static String getCep() {
        return cep;
    }

    /**
     * @param aCep the cep to set
     */
    public static void setCep(String aCep) {
        cep = aCep;
    }

}
