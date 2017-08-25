/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradordeplanilhas.model.sql;

/**
 *
 * @author nanda
 */

import geradordeplanilhas.model.database.Database;
import geradordeplanilhas.model.database.DatabaseFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConsultasSQL {

    //private Connection connection;
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    public void ConsultasSQL(){
    
    }

    public Connection getConnection() {
        return connection;
    }

    /*public void setConnection(Connection connection) {
        this.connection = connection;
    }*/

    public ResultSet ConsultaAtoresDoisModos(String anoInicio, String anoFim) throws SQLException{
        String consultaAtor = "select  DISTINCT p.idator, a.nome, idpapel from redesocial.filmes f \n" +
"join redesocial.papel p on f.idfilme = p.idfilme join redesocial.atores a on p.idator = a.idator where f.ano BETWEEN '" + anoInicio + "' AND '" + anoFim + "'";
        PreparedStatement stmt = connection.prepareStatement(consultaAtor);
        ResultSet resultadoAtor = stmt.executeQuery();
        return resultadoAtor;
    }
    
    public ResultSet ConsultaFilmesDoisModos(String anoInicio, String anoFim) throws SQLException{
        String consultaFilme = "select idfilme, titulo from redesocial.filmes  where ano BETWEEN '" + anoInicio + "' AND '" + anoFim + "'";
        PreparedStatement stmt = connection.prepareStatement(consultaFilme);
        ResultSet resultadoFilme = stmt.executeQuery();
        return resultadoFilme;
    }
    
    public ResultSet VerificaAtorFilme(String ator, String filme) throws SQLException{
        String verificaParticipaçãoAtorFilme = "select count(*) from redesocial.papel where idator = '" + ator + "' AND idfilme = '" + filme + "'";
        PreparedStatement stmt = connection.prepareStatement(verificaParticipaçãoAtorFilme);
        ResultSet verificaAtorFilme = stmt.executeQuery();
        return verificaAtorFilme;
    }
    
    public ResultSet ConsultaAtoresUmModo(String anoInicio, String anoFim) throws SQLException{
         String consultaAtor = "select  DISTINCT p.idator, a.nome, idpapel from redesocial.filmes f \n" +
"join redesocial.papel p on f.idfilme = p.idfilme join redesocial.atores a on p.idator = a.idator where f.ano BETWEEN '" + anoInicio + "' AND '" + anoFim + "'";
        PreparedStatement stmt = connection.prepareStatement(consultaAtor);
        ResultSet resultado = stmt.executeQuery();
        return resultado;
    }
    
    public ResultSet ConsultaAtoresUmModoExtra(String anoInicio, String anoFim) throws SQLException{
        String consultaA = "select idator from redesocial.filmes f join redesocial.papel p on f.idfilme = p.idfilme where f.ano BETWEEN '" + anoInicio + "' AND '" + anoFim + "'";
        PreparedStatement stmt = connection.prepareStatement(consultaA);
        ResultSet resultado = stmt.executeQuery();
        return resultado;
    }
    
    public ResultSet VerificaAtoresUmModo(String resultAtor, String ator) throws SQLException{
        String verificaAtores = "select count(*) from (select idfilme from redesocial.papel where idator = '" + resultAtor + "') as A\n" +
"join (select idfilme from redesocial.papel where idator = '" + ator + "') as B on A.idfilme = B.idfilme";
        PreparedStatement stmtVerificaAtores = connection.prepareStatement(verificaAtores);
        ResultSet resultadoVerificaAtores = stmtVerificaAtores.executeQuery();
        return resultadoVerificaAtores;
    }
    
  

}