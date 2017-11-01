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
import java.sql.Statement;


public class ConsultasSQL {

    //private Connection connection;
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();

    public void ConsultasSQL() {

    }

    public Connection getConnection() {
        return connection;
    }

    /*public void setConnection(Connection connection) {
        this.connection = connection;
    }*/
    public ResultSet ConsultaAtoresDoisModos(String papeis, String anoInicio, String anoFim) throws SQLException {
        String consultaAtor = "select  DISTINCT p.idator, a.nome, ps.sigla, ps.idpapel from redesocial.filmes f \n"
                + "                join redesocial.papel p on f.idfilme = p.idfilme join redesocial.atores a on p.idator = a.idator join\n"
                + "                redesocial.papeis ps on ps.idpapel = p.idpapel where ps.sigla in (" + papeis + ") AND f.ano BETWEEN '" + anoInicio + "' AND '" + anoFim + "'";
        PreparedStatement stmt = connection.prepareStatement(consultaAtor);
        ResultSet resultadoAtor = stmt.executeQuery();
        return resultadoAtor;
    }

    public ResultSet ConsultaFilmesDoisModos(String anoInicio, String anoFim) throws SQLException {
        String consultaFilme = "select idfilme, titulo from redesocial.filmes  where ano BETWEEN '" + anoInicio + "' AND '" + anoFim + "'";
        PreparedStatement stmt = connection.prepareStatement(consultaFilme);
        ResultSet resultadoFilme = stmt.executeQuery();
        return resultadoFilme;
    }

    public ResultSet VerificaAtorFilme(String ator, String idpapel, String filme) throws SQLException {
        String verificaParticipaçãoAtorFilme = "select count(*) from redesocial.papel where idator = '" + ator + "' AND idfilme = '" + filme + "'AND idpapel = '" + idpapel + "'";
        PreparedStatement stmt = connection.prepareStatement(verificaParticipaçãoAtorFilme);
        ResultSet verificaAtorFilme = stmt.executeQuery();
        return verificaAtorFilme;
    }

    public ResultSet ConsultaAtoresUmModo(String papeis, String anoInicio, String anoFim) throws SQLException {
        String consultaAtor = "select  DISTINCT p.idator, a.nome, ps.sigla, ps.idpapel from redesocial.filmes f \n"
                + "join redesocial.papel p on f.idfilme = p.idfilme join redesocial.atores a on p.idator = a.idator join redesocial.papeis ps on ps.idpapel = p.idpapel where ps.sigla in (" + papeis + ") AND f.ano BETWEEN '" + anoInicio + "' AND '" + anoFim + "'";
        PreparedStatement stmt = connection.prepareStatement(consultaAtor);
        ResultSet resultado = stmt.executeQuery();
        return resultado;
    }

    public ResultSet VerificaAtoresUmModo(String resultAtor, String resultAtorIdPapel, String ator, String atorIdPapel, String anoInicio, String anoFim) throws SQLException {
        String verificaAtores = "select count(*) from (select p.idfilme from redesocial.papel p join redesocial.filmes f on p.idfilme = f.idfilme where p.idator = '" + resultAtor + "' AND p.idpapel = '" + resultAtorIdPapel + "' AND f.ano BETWEEN '" + anoInicio + "' AND '" + anoFim + "') as A\n"
                + "join (select p.idfilme from redesocial.papel p join redesocial.filmes f on p.idfilme = f.idfilme where p.idator = '" + ator + "' AND p.idpapel = '" + atorIdPapel + "' AND f.ano BETWEEN '" +anoInicio + "' AND '" + anoFim + "') as B on A.idfilme = B.idfilme";
        PreparedStatement stmtVerificaAtores = connection.prepareStatement(verificaAtores);
        ResultSet resultadoVerificaAtores = stmtVerificaAtores.executeQuery();
        return resultadoVerificaAtores;
    }

    public ResultSet ConsultaFilmesUmModo(String papeis, String anoInicio, String anoFim) throws SQLException {
        String consultaA = "select distinct f.idfilme, f.titulo, ps.idpapel, ps.sigla from redesocial.filmes f join redesocial.papel p on f.idfilme = p.idfilme join redesocial.papeis ps on\n"
                + "ps.idpapel = p.idpapel where ps.sigla in (" + papeis + ") AND f.ano BETWEEN '" + anoInicio + "' AND '" + anoFim + "' order by f.idfilme, ps.idpapel";
        PreparedStatement stmt = connection.prepareStatement(consultaA);
        ResultSet resultado = stmt.executeQuery();
        return resultado;
    }

    public ResultSet VerificaFilmesUmModo(String resultFilme, String resultFilmeIdPapel, String filme, String filmeIdPapel) throws SQLException {
        String verificaFilmes = "select count(*) from (select DISTINCT idator from redesocial.papel where idfilme = '" + resultFilme + "' and idpapel = '" + resultFilmeIdPapel + "') as F \n"
                + "join (select DISTINCT idator from redesocial.papel where idfilme = '" + filme + "' and idpapel = '" + filmeIdPapel + "') as B on F.idator = B.idator";
        PreparedStatement stmtVerificaFilmes = connection.prepareStatement(verificaFilmes);
        ResultSet resultadoVerificaFilmes = stmtVerificaFilmes.executeQuery();
        return resultadoVerificaFilmes;
    }

    public ResultSet ConsultaFilmes(String ator) throws SQLException {
        String consultaFilmes = "select DISTINCT f.titulo, f.ano, f.salas, f.publico, f.renda, f.uf from redesocial.filmes f join redesocial.papel p on f.idfilme = p.idfilme join redesocial.atores a on a.idator = p.idator where\n"
                + "a.nome like '%" + ator + "%'";
        PreparedStatement stmtConsultaFilmes = connection.prepareStatement(consultaFilmes);
        ResultSet resultadoConsultaFilmes = stmtConsultaFilmes.executeQuery();
        return resultadoConsultaFilmes;
    }

    public ResultSet ConsultaAtores(String filme) throws SQLException {
        String consultaAtores = "select a.idator, ps.idpapel, a.nome, ps.papel, ps.sigla from redesocial.papel p join redesocial.filmes f on f.idfilme = p.idfilme \n"
                + "join redesocial.papeis ps on ps.idpapel = p.idpapel join redesocial.atores a on a.idator = p.idator where f.titulo like '" + filme + "' order by p.idpapel";
        PreparedStatement stmtConsultaAtores = connection.prepareStatement(consultaAtores);
        ResultSet resultadoConsultaAtores = stmtConsultaAtores.executeQuery();
        return resultadoConsultaAtores;
    }

    public ResultSet ConsultaDadosFilme(String filme) throws SQLException {
        String consultaDadosFilme = "select idfilme, titulo, ano, salas, publico, renda, uf from redesocial.filmes where titulo like '" + filme + "'";
        PreparedStatement stmtConsultaDadosFilme = connection.prepareStatement(consultaDadosFilme);
        ResultSet resultadoConsultaDadosFilme = stmtConsultaDadosFilme.executeQuery();
        return resultadoConsultaDadosFilme;
    }

    public void UpdateTitulo(String novoTitulo, int id) throws SQLException {
        String updateTitulo = "UPDATE redesocial.filmes SET titulo = '" + novoTitulo + "' WHERE idfilme = '" + id + "'";
        PreparedStatement stmtUpdate = connection.prepareStatement(updateTitulo);
        stmtUpdate.executeUpdate();

    }

    public void UpdateAno(String novoAno, int id) throws SQLException {
        String upAno = "UPDATE redesocial.filmes SET ano = '" + novoAno + "' WHERE idfilme = '" + id + "'";
        PreparedStatement stmtUpdate = connection.prepareStatement(upAno);
        stmtUpdate.executeUpdate();

    }

    public void UpdateSalas(Integer novoSalas, int id) throws SQLException {
        String upSalas = "UPDATE redesocial.filmes SET salas = '" + novoSalas + "' WHERE idfilme = '" + id + "'";
        PreparedStatement stmtUpdate = connection.prepareStatement(upSalas);
        stmtUpdate.executeUpdate();

    }

    public void UpdatePublico(Integer novoPublico, int id) throws SQLException {
        String upPublico = "UPDATE redesocial.filmes SET publico = '" + novoPublico + "' WHERE idfilme = '" + id + "'";
        PreparedStatement stmtUpdate = connection.prepareStatement(upPublico);
        stmtUpdate.executeUpdate();

    }

    public void UpdateRenda(Double novoRenda, int id) throws SQLException {
        String upRenda = "UPDATE redesocial.filmes SET renda = '" + novoRenda + "' WHERE idfilme = '" + id + "'";
        PreparedStatement stmtUpdate = connection.prepareStatement(upRenda);
        stmtUpdate.executeUpdate();

    }

    public void UpdateUF(String novoUF, int id) throws SQLException {
        String upUF = "UPDATE redesocial.filmes SET UF = '" + novoUF + "' WHERE idfilme = '" + id + "'";
        PreparedStatement stmtUpdate = connection.prepareStatement(upUF);
        stmtUpdate.executeUpdate();

    }

    public ResultSet InsereNovoFilme(String Ano, String Titulo, Integer Salas, Integer Publico, Double Renda, String UF) throws SQLException {
        ResultSet ResultFilme = ConsultaDadosFilme(Titulo);
        if (ResultFilme.next() == false) {
            String novoFilme = "INSERT INTO redesocial.filmes(ano, titulo, salas, renda, publico, uf) VALUES('" + Ano + "', '" + Titulo + "', '" + Salas + "', '" + Renda + "', '" + Publico + "', '" + UF + "')";
            PreparedStatement stmtInsereNovoFilme = connection.prepareStatement(novoFilme, Statement.RETURN_GENERATED_KEYS);
            stmtInsereNovoFilme.executeUpdate();
            ResultSet insereFilme = stmtInsereNovoFilme.getGeneratedKeys();
            return insereFilme;

        } else {
            return ResultFilme;
        }

    }

    public void SelectAtor(String Ator, Integer idator, Integer idfilme, Integer idpapel) throws SQLException {
        if (!"".equals(Ator)) {
            String selectAtor = "Select idator from redesocial.atores where nome = '" + Ator + "' limit 1";
            PreparedStatement stmtSelectAtor = connection.prepareStatement(selectAtor);
            ResultSet resultadoSelectAtor = stmtSelectAtor.executeQuery();
            if (resultadoSelectAtor.next()) {
                int idAt = resultadoSelectAtor.getInt(1);

                String inserePapel = "INSERT INTO redesocial.papel(idfilme, idator, idpapel) VALUES('" + idfilme + "', '" + idAt + "', '" + idpapel + "')";
                PreparedStatement stmtInserePapel = connection.prepareStatement(inserePapel);
                stmtInserePapel.executeUpdate();
            } else {
                String insertAtor = "INSERT INTO redesocial.atores(nome) VALUES ('" + Ator + "')";
                PreparedStatement stmtInsereAtor = connection.prepareStatement(insertAtor, Statement.RETURN_GENERATED_KEYS);
                stmtInsereAtor.executeUpdate();
                ResultSet insereAtor = stmtInsereAtor.getGeneratedKeys();
                if (insereAtor.next()) {
                    int idNovoAtor = insereAtor.getInt(1);
                    String inserePapel = "INSERT INTO redesocial.papel(idfilme, idator, idpapel) VALUES('" + idfilme + "', '" + idNovoAtor + "', '" + idpapel + "')";
                    PreparedStatement stmtInserePapel = connection.prepareStatement(inserePapel);
                    stmtInserePapel.executeUpdate();
                }
            }
        } else {

        }

    }

    public void DeletaAtorDoFilme(Integer idFilme, Integer idAtor, Integer idPapel) throws SQLException {
        String DeletaAtorDoFilme = "DELETE FROM redesocial.papel WHERE idfilme = '" + idFilme + "'and idator = '" + idAtor + "' and idpapel = '" + idPapel + "'";
        PreparedStatement stmtDelAtorFilme = connection.prepareStatement(DeletaAtorDoFilme);
        stmtDelAtorFilme.executeUpdate();
    }

    public void UpdatePapeis(String Ator, Integer idator, Integer idfilme, Integer idpapel) throws SQLException {
        //DeletaAtorDoFilme(idfilme, idator, idpapel);
        if (!"".equals(Ator)) {
            DeletaAtorDoFilme(idfilme, idator, idpapel);
            String selectAtor = "Select idator from redesocial.atores where nome = '" + Ator + "' limit 1";
            PreparedStatement stmtSelectAtor = connection.prepareStatement(selectAtor);
            ResultSet resultadoSelectAtor = stmtSelectAtor.executeQuery();
            if (resultadoSelectAtor.next()) {
                int idAt = resultadoSelectAtor.getInt(1);
                String inserePapel = "INSERT INTO redesocial.papel(idfilme, idator, idpapel) VALUES('" + idfilme + "', '" + idAt + "', '" + idpapel + "')";
                PreparedStatement stmtInserePapel = connection.prepareStatement(inserePapel);
                stmtInserePapel.executeUpdate();

            } else {
                String insertAtor = "INSERT INTO redesocial.atores(nome) VALUES ('" + Ator + "')";
                PreparedStatement stmtInsereAtor = connection.prepareStatement(insertAtor, Statement.RETURN_GENERATED_KEYS);
                stmtInsereAtor.executeUpdate();
                ResultSet insereAtor = stmtInsereAtor.getGeneratedKeys();
                if (insereAtor.next()) {
                    int idNovoAtor = insereAtor.getInt(1);
                    String inserePapel = "INSERT INTO redesocial.papel(idfilme, idator, idpapel) VALUES('" + idfilme + "', '" + idNovoAtor + "', '" + idpapel + "')";
                    PreparedStatement stmtInserePapel = connection.prepareStatement(inserePapel);
                    stmtInserePapel.executeUpdate();
                }
            }
        } else {
            DeletaAtorDoFilme(idfilme, idator, idpapel);
        }
    }
}
