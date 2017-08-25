/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradordeplanilhas.controller;

import geradordeplanilhas.model.database.Database;
import geradordeplanilhas.model.database.DatabaseFactory;
import geradordeplanilhas.model.sql.ConsultasSQL;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author nanda
 */
public class GeraMatriz {
    
   /* private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();*/
    
    private FileWriter planilha;
    private String nomeArquivo;
    private ArrayList<String> ListaAtores = new ArrayList<String>();
    private ArrayList<String> ListaAtoresNomes = new ArrayList<String>();
    private ArrayList<String> ListaFilmes = new ArrayList<String>();
    ConsultasSQL query = new ConsultasSQL();
    
    public GeraMatriz(String nomeArquivo) throws IOException{
        //nomeArquivo = "planilhaTESTE.csv";     
        planilha = new FileWriter(nomeArquivo);
    }
    
    public FileWriter getPlanilha(){
        return planilha;
    }
        
    public void setPlanilha(FileWriter planilha){
        this.planilha = planilha;
    }
    
    public String getNomeArquivo(){
        return nomeArquivo;
    }
    
    public void setNomeArquivo(String nomeArquivo){
        this.nomeArquivo = nomeArquivo;
    }
        


    public void PreencheMatrizDoisModos (String anoInicio, String anoFim) throws SQLException, IOException{
       try{
            ResultSet resultadoAtor = query.ConsultaAtoresDoisModos(anoInicio, anoFim);
           
            String listaAtores = "";
            String listaAtoresNomes = "";
          
            while (resultadoAtor.next()) {
                listaAtores += resultadoAtor.getString(1)+ ";";
                listaAtoresNomes += resultadoAtor.getString(2)+ ";";

                ListaAtores.add(resultadoAtor.getString(1));
                ListaAtoresNomes.add(resultadoAtor.getString(2));;
            }
            
            planilha.write(";" + listaAtoresNomes + System.getProperty("line.separator"));
        
            ResultSet resultadoFilme = query.ConsultaFilmesDoisModos(anoInicio, anoFim);
            
            while (resultadoFilme.next()) {
                String filme = resultadoFilme.getString(1);
                String filmeNome = resultadoFilme.getString(2);
                planilha.write(filmeNome + ";");
                
                for(int i = 0; i < ListaAtores.size(); i++){
                    String ator = ListaAtores.get(i);
                    ResultSet verificaParticipação = query.VerificaAtorFilme(ator, filme);
                    verificaParticipação.next();
                        String valor = verificaParticipação.getString(1);
                        int numero = Integer.parseInt(valor);
                        if(numero >= 1){
                            planilha.write("1" + ";");
                        } else  planilha.write("0" + ";");
                }
                planilha.write(System.getProperty("line.separator"));    
            }
            planilha.close();
             System.out.println("Final Dois Modos");
            

        }catch(IOException e){
            e.getMessage();
        }
        
    }

    public void PreencheMatrizUmModo(String anoInicio, String anoFim) throws SQLException, IOException{
        try{

            ResultSet resultado = query.ConsultaAtoresUmModo(anoInicio, anoFim);
            String listaAtoresNomes = "";
           
            while (resultado.next()){
               // listaAtor += resultado.getString(1)+ ";";
                listaAtoresNomes += resultado.getString(2)+ ";";
                ListaAtores.add(resultado.getString(1));
            }
            
            planilha.write(";" + listaAtoresNomes + System.getProperty("line.separator"));
        
            ResultSet resultadoA = query.ConsultaAtoresUmModo(anoInicio, anoFim);
            
            
            while (resultadoA.next()) {
                String resultAtor = resultadoA.getString(1);
                planilha.write(resultAtor + ";");
                
                for(int i = 0; i < ListaAtores.size(); i++){
                    String ator = ListaAtores.get(i);        
                    ResultSet resultadoVerificaAtores = query.VerificaAtoresUmModo(resultAtor, ator);
                    resultadoVerificaAtores.next();
                        String valor = resultadoVerificaAtores.getString(1);
                        int numero = Integer.parseInt(valor);
                        planilha.write(numero + ";");                                

                }
                planilha.write(System.getProperty("line.separator"));    
            }
            System.out.println("Final Um Modo");
            planilha.close();
            

        }catch(IOException e){
            e.getMessage();
        }
    }
}
