/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradordeplanilhas;


import geradordeplanilhas.model.sql.ConsultasSQL;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author nanda
 */
public class GeraMatriz {


    private FileWriter planilha;
    private String nomeArquivo;
    private ArrayList<String> ListaAtores = new ArrayList<>();
    private ArrayList<String> ListaAtoresIdPapel = new ArrayList<>();
    private ArrayList<String> ListaAtoresNomes = new ArrayList<>();
    private ArrayList<String> ListaFilmes = new ArrayList<>();
    ConsultasSQL query = new ConsultasSQL();

    public GeraMatriz(String nomeArquivo) throws IOException {

        planilha = new FileWriter(nomeArquivo);
    }

    public FileWriter getPlanilha() {
        return planilha;
    }

    public void setPlanilha(FileWriter planilha) {
        this.planilha = planilha;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public void PreencheMatrizDoisModos(int tipoLeitura, String papeis, String anoInicio, String anoFim) throws SQLException, IOException {
        try {

            ResultSet resultadoAtor = query.ConsultaAtoresDoisModos(papeis, anoInicio, anoFim);

            String listaAtoresNomes = "";

            if (tipoLeitura == 1) { // Leitura por ID do Ator
                while (resultadoAtor.next()) {
                    listaAtoresNomes += resultadoAtor.getString(1) + ";";
                    ListaAtores.add(resultadoAtor.getString(1));
                    ListaAtoresIdPapel.add(resultadoAtor.getString(4));
                }
            } else { // Leitura por Nome do Ator

                while (resultadoAtor.next()) {
                    listaAtoresNomes += resultadoAtor.getString(2) + "-" + resultadoAtor.getString(3) + ";";;
                    ListaAtores.add(resultadoAtor.getString(1));
                    ListaAtoresIdPapel.add(resultadoAtor.getString(4));
                }

            }

            planilha.write(";" + listaAtoresNomes + System.getProperty("line.separator"));

            ResultSet resultadoFilme = query.ConsultaFilmesDoisModos(anoInicio, anoFim);

            while (resultadoFilme.next()) {
                String filme = resultadoFilme.getString(1);
                String filmeNome = resultadoFilme.getString(2);

                if (tipoLeitura == 1) {
                    planilha.write(filme + ";");
                } else {
                    planilha.write(filmeNome + ";");
                }
                for (int i = 0; i < ListaAtores.size(); i++) {
                    String ator = ListaAtores.get(i);
                    String idpapel = ListaAtoresIdPapel.get(i);
                    ResultSet verificaParticipação = query.VerificaAtorFilme(ator, idpapel, filme);
                    verificaParticipação.next();
                    String valor = verificaParticipação.getString(1);
                    int numero = Integer.parseInt(valor);
                    if (numero >= 1) {
                        planilha.write("1" + ";");
                    } else {
                        planilha.write("0" + ";");
                    }
                }
                planilha.write(System.getProperty("line.separator"));
            }
            planilha.close();
            System.out.println("Final Dois Modos");

        } catch (IOException e) {
            e.getMessage();
        }

    }

    public void PreencheMatrizUmModo(int tipoRede, int tipoLeitura, boolean valorada, String papeis, String anoInicio, String anoFim) throws SQLException, IOException {
        try {
            if (tipoRede == 1) { // Rede de Um Modo do Tipo Ator X Ator
                ResultSet resultado = query.ConsultaAtoresUmModo(papeis, anoInicio, anoFim);
                String listaAtoresNomes = "";

                if (tipoLeitura == 1) { // Leitura por ID do Ator
                    while (resultado.next()) {
                        listaAtoresNomes += resultado.getString(1) +  "-" + resultado.getString(3) + ";";
                        ListaAtores.add(resultado.getString(1));
                        ListaAtoresIdPapel.add(resultado.getString(4));
                    }
                } else { // Leitura por Nome/Sigla do Ator/Papel
                    while (resultado.next()) {
                        listaAtoresNomes += resultado.getString(2) + "-" + resultado.getString(3) + ";";
                        ListaAtores.add(resultado.getString(1));
                        ListaAtoresIdPapel.add(resultado.getString(4));
                    }
                }

                planilha.write(";" + listaAtoresNomes + System.getProperty("line.separator"));

                ResultSet resultadoA = query.ConsultaAtoresUmModo(papeis, anoInicio, anoFim);

                while (resultadoA.next()) {
                    String resultAtor = resultadoA.getString(1);
                    String resultAtorIdPapel = resultadoA.getString(4); // ID papel
                    String resultAtorNome = resultadoA.getString(2);
                    String resultSigla = resultadoA.getString(3);

                    if (tipoLeitura == 1) { // Leitura por ID
                        planilha.write(resultAtor + " - " + resultSigla + ";");
                    } else { // Leitura por Nome
                        planilha.write(resultAtorNome + " - " + resultSigla + ";");
                    }

                    for (int i = 0; i < ListaAtores.size(); i++) {
                        String ator = ListaAtores.get(i);
                        String atorIdPapel = ListaAtoresIdPapel.get(i);

                        if (resultAtor.equals(ator) && resultAtorIdPapel.equals(atorIdPapel)) {
                            planilha.write(0 + ";");
                        } else {
                            ResultSet resultadoVerificaAtores = query.VerificaAtoresUmModo(resultAtor, resultAtorIdPapel, ator, atorIdPapel, anoInicio, anoFim);
                            resultadoVerificaAtores.next();
                            String valor = resultadoVerificaAtores.getString(1);
                            int numero = Integer.parseInt(valor);
                            if (valorada == true) { // Quantos Filmes os Atores Atuaram Juntos
                                planilha.write(numero + ";");
                            } else { // 0 para nenhuma atuação conjunta e 1 para no mínimo uma atuação conjunta
                                if (numero != 0) {
                                    planilha.write(1 + ";");
                                } else {
                                    planilha.write(0 + ";");
                                }
                            }
                        }
                    }
                    planilha.write(System.getProperty("line.separator"));
                }
                System.out.println("Final Um Modo Ator");
                planilha.close();

            } else { // Rede de Um Modo do Tipo Filme x Filme

                ResultSet resultado = query.ConsultaFilmesUmModo(papeis, anoInicio, anoFim);
                String listaFilmesNomes = "";

                if (tipoLeitura == 1) { // Leitura por ID do Filme
                    while (resultado.next()) {
                        listaFilmesNomes += resultado.getString(1) + "-" + resultado.getString(4) + ";";
                        ListaFilmes.add(resultado.getString(1));
                        ListaAtoresIdPapel.add(resultado.getString(3));
                    }
                } else { //Leitura por Nome do Filme
                    while (resultado.next()) {
                        listaFilmesNomes += resultado.getString(2)  + "-" + resultado.getString(4) + ";";
                        ListaFilmes.add(resultado.getString(1));
                        ListaAtoresIdPapel.add(resultado.getString(3));

                    }
                }

                planilha.write(";" + listaFilmesNomes + System.getProperty("line.separator"));

                ResultSet resultadoA = query.ConsultaFilmesUmModo(papeis, anoInicio, anoFim);

                while (resultadoA.next()) {
                     
                    String resultFilme = resultadoA.getString(1);
                    String resultFilmeNome = resultadoA.getString(2);
                    String resultFilmeIdPapel = resultadoA.getString(3);
                    String resultSigla = resultadoA.getString(4);

                    if (tipoLeitura == 1) {
                        planilha.write(resultFilme + " - " + resultSigla + ";");
                    } else {
                        planilha.write(resultFilmeNome + " - " + resultSigla + ";");
                    }

                    for (int i = 0; i < ListaFilmes.size(); i++) {
                        String filme = ListaFilmes.get(i);
                        String filmeIdPapel = ListaAtoresIdPapel.get(i);

                        if (resultFilme.equals(filme)&& resultFilmeIdPapel.equals(filmeIdPapel)) { // Zera a interação de um filme com ele mesmo
                            planilha.write(0 + ";");
                        } else {
                            ResultSet resultadoVerificaFilmes = query.VerificaFilmesUmModo(resultFilme, resultFilmeIdPapel, filme, filmeIdPapel);
                            resultadoVerificaFilmes.next();
                            String valor = resultadoVerificaFilmes.getString(1);
                            int numero = Integer.parseInt(valor);
                            if (valorada == true) { // Quantos atores atuaram nos dois filmes
                                planilha.write(numero + ";");
                            } else { // 1 para pelo menos um ator em comum entre os filmes comparados e 0 para nenhum ator em comum
                                if (numero != 0) {
                                    planilha.write(1 + ";");
                                } else {
                                    planilha.write(0 + ";");
                                }
                            }
                        }
                    }
                    planilha.write(System.getProperty("line.separator"));
                }
                System.out.println("Final Um Modo Filme");
                planilha.close();

            }

        } catch (IOException e) {
            e.getMessage();
        }
    }
}
