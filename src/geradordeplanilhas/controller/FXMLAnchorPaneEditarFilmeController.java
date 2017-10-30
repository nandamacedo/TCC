/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradordeplanilhas.controller;

import geradordeplanilhas.entity.Atores;
import geradordeplanilhas.model.sql.ConsultasSQL;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author nanda
 */
public class FXMLAnchorPaneEditarFilmeController implements Initializable {

    @FXML
    private TextField textFieldNomeFilme;
    @FXML
    private Button buttonConsultaFilme;
    @FXML
    private GridPane gridPaneDadosFilme;
    @FXML
    private TextField gTitulo;
    @FXML
    private TextField gAno;
    @FXML
    private TextField gSalas;
    @FXML
    private TextField gRenda;
    @FXML
    private TextField gPublico;
    @FXML
    private TextField gUF;
    @FXML
    private TextField gAtor1;
    @FXML
    private TextField gAtor2;
    @FXML
    private TextField gAtor3;
    @FXML
    private TextField gAtor4;
    @FXML
    private TextField gDiretor;
    @FXML
    private TextField gRoteirista;
    @FXML
    private TextField gEdicao;
    @FXML
    private TextField gPE;
    @FXML
    private TextField gFoto;
    @FXML
    private TextField gArte;
    @FXML
    private TextField gProdutor;

    private String filme = "";
    private ResultSet atores;
    private ResultSet dFilme;
    ConsultasSQL query = new ConsultasSQL();
    List<Atores> listaAtores = new ArrayList<Atores>(10);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void preencheGridDadosFilme(String nomefilme) {
        try {

            Atores at = new Atores("", "", 0, 0);
            for (int j = 0; j < 10; j++) {
                listaAtores.add(j, at);
            }

            filme = nomefilme;
            atores = query.ConsultaAtores(filme);
            dFilme = query.ConsultaDadosFilme(filme);
            if (!filme.contentEquals("") && dFilme.next()) {

                this.gTitulo.setText(dFilme.getString(2));
                this.gAno.setText(dFilme.getString(3));
                this.gSalas.setText(dFilme.getString(4));
                this.gPublico.setText(dFilme.getString(5));
                this.gRenda.setText(dFilme.getString(6));
                this.gUF.setText(dFilme.getString(7));

                int i = 0;
                while (atores.next()) {
                    Atores ator = new Atores(atores.getString(3), atores.getString(5), atores.getInt(1), atores.getInt(2));

                    if (ator.getSigla().contentEquals("P")) {
                        listaAtores.add(10, ator);
                    }
                    if (ator.getSigla().contentEquals("D")) {
                        listaAtores.add(9, ator);
                    }
                    if (ator.getSigla().contentEquals("R")) {
                        listaAtores.add(8, ator);
                    }
                    if (ator.getSigla().contentEquals("E")) {
                        listaAtores.add(7, ator);
                    }
                    if (ator.getSigla().contentEquals("F")) {
                        listaAtores.add(6, ator);
                    }
                    if (ator.getSigla().contentEquals("DA")) {
                        listaAtores.add(5, ator);
                    }
                    if (ator.getSigla().contentEquals("PE")) {
                        listaAtores.add(4, ator);
                    }
                    if (ator.getSigla().contentEquals("A") && i == 0) {
                        listaAtores.add(0, ator);
                    }
                    if (ator.getSigla().contentEquals("A") && i == 1) {
                        listaAtores.add(1, ator);
                    }
                    if (ator.getSigla().contentEquals("A") && i == 2) {
                        listaAtores.add(2, ator);
                    }
                    if (ator.getSigla().contentEquals("A") && i == 3) {
                        listaAtores.add(3, ator);
                    }
                    i++;
                }

                this.gAtor1.setText(listaAtores.get(0).getAtor());
                this.gAtor2.setText(listaAtores.get(1).getAtor());
                this.gAtor3.setText(listaAtores.get(2).getAtor());
                this.gAtor4.setText(listaAtores.get(3).getAtor());
                this.gPE.setText(listaAtores.get(4).getAtor());
                this.gArte.setText(listaAtores.get(5).getAtor());
                this.gFoto.setText(listaAtores.get(6).getAtor());
                this.gEdicao.setText(listaAtores.get(7).getAtor());
                this.gRoteirista.setText(listaAtores.get(8).getAtor());
                this.gDiretor.setText(listaAtores.get(9).getAtor());
                this.gProdutor.setText(listaAtores.get(10).getAtor());
            } else {
                limpaCampos();
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Filme não encontrado");
                alert.setHeaderText(null);
                alert.setContentText("Digite o nome de um filme cadastrado.");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error ao recuperar campos");
        }

    }

    @FXML
    public void limpaCampos() {

        try {
            this.textFieldNomeFilme.setText("");
            this.gTitulo.setText("");
            this.gAno.setText("");
            this.gSalas.setText("");
            this.gPublico.setText("");
            this.gRenda.setText("");
            this.gUF.setText("");
            this.gAtor1.setText("");
            this.gAtor2.setText("");
            this.gAtor3.setText("");
            this.gAtor4.setText("");
            this.gPE.setText("");
            this.gArte.setText("");
            this.gFoto.setText("");
            this.gEdicao.setText("");
            this.gRoteirista.setText("");
            this.gDiretor.setText("");
            this.gProdutor.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao limpar campos");
        }

    }

    @FXML
    private void salvaAlteracoes() throws SQLException {

        try {
            String novoTitulo = this.gTitulo.getText();
            String novoAno = this.gAno.getText();
            Integer novoSalas = Integer.parseInt(this.gSalas.getText());
            Integer novoPublico = Integer.parseInt(this.gPublico.getText());
            Double novoRenda = Double.parseDouble(this.gRenda.getText());
            String novoUF = this.gUF.getText();
            if (!novoTitulo.contentEquals(dFilme.getString(2))) {
                query.UpdateTitulo(novoTitulo, dFilme.getInt(1));

            }
            if (!novoAno.contentEquals(dFilme.getString(3))) {
                query.UpdateAno(novoAno, dFilme.getInt(1));
            }
            if (novoSalas != dFilme.getInt(4)) {
                query.UpdateSalas(novoSalas, dFilme.getInt(1));
            }
            if (novoPublico != dFilme.getInt(5)) {
                query.UpdatePublico(novoPublico, dFilme.getInt(1));
            }
            if (novoRenda != dFilme.getDouble(6)) {
                query.UpdateRenda(novoRenda, dFilme.getInt(1));
            }
            if (!novoUF.contentEquals(dFilme.getString(7))) {
                query.UpdateUF(novoUF, dFilme.getInt(1));
            }
            
            // ATOR 01
            if ((listaAtores.get(0).getAtor() != gAtor1.getText()) && listaAtores.get(0).getAtor() == "") {
                query.SelectAtor(gAtor1.getText(), listaAtores.get(0).getIdAtor(), dFilme.getInt(1), 1);

            }
            else if((!"".equals(listaAtores.get(0).getAtor())) && (gAtor1.getText().trim().isEmpty())){
                               query.DeletaAtorDoFilme(dFilme.getInt(1), listaAtores.get(0).getIdAtor(), 1);
                
            }
            if((listaAtores.get(0).getAtor() != gAtor1.getText()) && listaAtores.get(0).getAtor() != ""){
                query.UpdatePapeis(gAtor1.getText(), listaAtores.get(0).getIdAtor(), dFilme.getInt(1), 1);
            }
                        
           // ATOR 02
            if ((listaAtores.get(1).getAtor() != gAtor2.getText()) && listaAtores.get(1).getAtor() == "") {
                query.SelectAtor(gAtor2.getText(), listaAtores.get(1).getIdAtor(), dFilme.getInt(1), 1);

            }
            else if((!"".equals(listaAtores.get(1).getAtor())) && (gAtor2.getText().trim().isEmpty())){
                               query.DeletaAtorDoFilme(dFilme.getInt(1), listaAtores.get(1).getIdAtor(), 1);
                
            }
            if((listaAtores.get(1).getAtor() != gAtor2.getText()) && listaAtores.get(1).getAtor() != ""){
                query.UpdatePapeis(gAtor2.getText(), listaAtores.get(1).getIdAtor(), dFilme.getInt(1), 1);
            }
            //ATOR 03
            if ((listaAtores.get(2).getAtor() != gAtor3.getText()) && listaAtores.get(2).getAtor() == "") {
                query.SelectAtor(gAtor3.getText(), listaAtores.get(2).getIdAtor(), dFilme.getInt(1), 1);

            }
            else if((!"".equals(listaAtores.get(2).getAtor())) && (gAtor3.getText().trim().isEmpty())){
                              query.DeletaAtorDoFilme(dFilme.getInt(1), listaAtores.get(2).getIdAtor(), 1);
                
            }
            if((listaAtores.get(2).getAtor() != gAtor3.getText()) && listaAtores.get(2).getAtor() != ""){
                query.UpdatePapeis(gAtor3.getText(), listaAtores.get(2).getIdAtor(), dFilme.getInt(1), 1);
            }
            //ATOR 04
            if ((listaAtores.get(3).getAtor() != gAtor4.getText()) && listaAtores.get(3).getAtor() == "") {
                query.SelectAtor(gAtor4.getText(), listaAtores.get(3).getIdAtor(), dFilme.getInt(1), 1);

            }
            else if((!"".equals(listaAtores.get(3).getAtor())) && (gAtor4.getText().trim().isEmpty())){
                              query.DeletaAtorDoFilme(dFilme.getInt(1), listaAtores.get(3).getIdAtor(), 1);
                
            }
            if((listaAtores.get(3).getAtor() != gAtor4.getText()) && listaAtores.get(3).getAtor() != ""){
                query.UpdatePapeis(gAtor4.getText(), listaAtores.get(3).getIdAtor(), dFilme.getInt(1), 1);
            }
            
              //PE
            if ((listaAtores.get(4).getAtor() != gPE.getText()) && listaAtores.get(4).getAtor() == "") {
                query.SelectAtor(gPE.getText(), listaAtores.get(4).getIdAtor(), dFilme.getInt(1), 2);

            }
            else if((!"".equals(listaAtores.get(4).getAtor())) && (gPE.getText().trim().isEmpty())){
                              query.DeletaAtorDoFilme(dFilme.getInt(1), listaAtores.get(4).getIdAtor(), 2);
                
            }
            if((listaAtores.get(4).getAtor() != gPE.getText()) && listaAtores.get(4).getAtor() != ""){
                query.UpdatePapeis(gPE.getText(), listaAtores.get(4).getIdAtor(), dFilme.getInt(1), 2);
            }
             //ARTE
            if ((listaAtores.get(5).getAtor() != gArte.getText()) && listaAtores.get(5).getAtor() == "") {
                query.SelectAtor(gArte.getText(), listaAtores.get(5).getIdAtor(), dFilme.getInt(1), 3);

            }
            else if((!"".equals(listaAtores.get(5).getAtor())) && (gArte.getText().trim().isEmpty())){
                              query.DeletaAtorDoFilme(dFilme.getInt(1), listaAtores.get(5).getIdAtor(), 3);
                
            }
            if((listaAtores.get(5).getAtor() != gArte.getText()) && listaAtores.get(5).getAtor() != ""){
                query.UpdatePapeis(gArte.getText(), listaAtores.get(5).getIdAtor(), dFilme.getInt(1), 3);
            }
            
            //FOTO
            if ((listaAtores.get(6).getAtor() != gFoto.getText()) && listaAtores.get(6).getAtor() == "") {
                query.SelectAtor(gFoto.getText(), listaAtores.get(6).getIdAtor(), dFilme.getInt(1), 4);

            }
            else if((!"".equals(listaAtores.get(6).getAtor())) && (gFoto.getText().trim().isEmpty())){
                              query.DeletaAtorDoFilme(dFilme.getInt(1), listaAtores.get(6).getIdAtor(), 4);
                
            }
            if((listaAtores.get(6).getAtor() != gFoto.getText()) && listaAtores.get(6).getAtor() != ""){
                query.UpdatePapeis(gFoto.getText(), listaAtores.get(6).getIdAtor(), dFilme.getInt(1), 4);
            }
            
            //EDICAO
            if ((listaAtores.get(7).getAtor() != gEdicao.getText()) && listaAtores.get(7).getAtor() == "") {
                query.SelectAtor(gEdicao.getText(), listaAtores.get(7).getIdAtor(), dFilme.getInt(1), 5);

            }
            else if((!"".equals(listaAtores.get(7).getAtor())) && (gEdicao.getText().trim().isEmpty())){
                              query.DeletaAtorDoFilme(dFilme.getInt(1), listaAtores.get(7).getIdAtor(), 5);
                
            }
            if((listaAtores.get(7).getAtor() != gEdicao.getText()) && listaAtores.get(7).getAtor() != ""){
                query.UpdatePapeis(gEdicao.getText(), listaAtores.get(7).getIdAtor(), dFilme.getInt(1), 5);
            }
            
            //ROTEIRISTA
            if ((listaAtores.get(8).getAtor() != gRoteirista.getText()) && listaAtores.get(8).getAtor() == "") {
                query.SelectAtor(gRoteirista.getText(), listaAtores.get(8).getIdAtor(), dFilme.getInt(1), 6);

            }
            else if((!"".equals(listaAtores.get(8).getAtor())) && (gRoteirista.getText().trim().isEmpty())){
                              query.DeletaAtorDoFilme(dFilme.getInt(1), listaAtores.get(8).getIdAtor(), 6);
                
            }
            if((listaAtores.get(8).getAtor() != gRoteirista.getText()) && listaAtores.get(8).getAtor() != ""){
                query.UpdatePapeis(gRoteirista.getText(), listaAtores.get(8).getIdAtor(), dFilme.getInt(1), 6);
            }
            
            //DIRETOR
            if ((listaAtores.get(9).getAtor() != gDiretor.getText()) && listaAtores.get(9).getAtor() == "") {
                query.SelectAtor(gDiretor.getText(), listaAtores.get(9).getIdAtor(), dFilme.getInt(1), 7);

            }
            else if((!"".equals(listaAtores.get(9).getAtor())) && (gDiretor.getText().trim().isEmpty())){
                              query.DeletaAtorDoFilme(dFilme.getInt(1), listaAtores.get(9).getIdAtor(), 7);
                
            }
            if((listaAtores.get(9).getAtor() != gDiretor.getText()) && listaAtores.get(9).getAtor() != ""){
                query.UpdatePapeis(gDiretor.getText(), listaAtores.get(9).getIdAtor(), dFilme.getInt(1), 7);
            }
            
            //PRODUTOR
            if ((listaAtores.get(10).getAtor() != gProdutor.getText()) && listaAtores.get(10).getAtor() == "") {
                query.SelectAtor(gProdutor.getText(), listaAtores.get(10).getIdAtor(), dFilme.getInt(1), 8);

            }
            else if((!"".equals(listaAtores.get(10).getAtor())) && (gProdutor.getText().trim().isEmpty())){
                              query.DeletaAtorDoFilme(dFilme.getInt(1), listaAtores.get(10).getIdAtor(), 8);
                
            }
            if((listaAtores.get(10).getAtor() != gProdutor.getText()) && listaAtores.get(10).getAtor() != ""){
                query.UpdatePapeis(gProdutor.getText(), listaAtores.get(10).getIdAtor(), dFilme.getInt(1), 8);
            }
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Dados Alterados");
            alert.setHeaderText(null);
            alert.setContentText("Dados do filme alterados com sucesso.");
            alert.showAndWait();
            
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao atualizar dados do filme.");
            alert.showAndWait();

        }

    }

    @FXML
    public void handleButtonConsultaFilme() {
        preencheGridDadosFilme(textFieldNomeFilme.getText());
    }

    @FXML
    public void handleButtonSalvar() throws SQLException {
        salvaAlteracoes();
    }

    @FXML
    public void handleButtonCancelar() {
        limpaCampos();

    }

}
