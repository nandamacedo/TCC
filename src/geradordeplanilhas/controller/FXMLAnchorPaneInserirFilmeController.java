/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradordeplanilhas.controller;


import geradordeplanilhas.model.sql.ConsultasSQL;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author nanda
 */
public class FXMLAnchorPaneInserirFilmeController implements Initializable {

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

    private ResultSet dFilme;

    ConsultasSQL query = new ConsultasSQL();
    //  List<Atores> listaAtores = new ArrayList<Atores>(10);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void recebeGridDadosFilme() throws SQLException {
        /*
        Atores at = new Atores("", "", 0, 0);
        for (int j = 0; j < 10; j++) {
            listaAtores.add(j, at);
        }*/

        String novoTitulo = this.gTitulo.getText();
        String novoAno = this.gAno.getText();
        Integer novoSalas = Integer.parseInt(this.gSalas.getText());
        Integer novoPublico = Integer.parseInt(this.gPublico.getText());
        Double novoRenda = Double.parseDouble(this.gRenda.getText());
        String novoUF = this.gUF.getText();

        dFilme = query.InsereNovoFilme(novoAno, novoTitulo, novoSalas, novoPublico, novoRenda, novoUF);

        if (dFilme.next()) {
            salvaAlteracoes();
            System.out.print(dFilme.getInt(1));
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao inserir o filme.");
            alert.showAndWait();
        }

    }

    @FXML
    private void salvaAlteracoes() throws SQLException {

        try {

            // ATOR 01
            if (!gAtor1.getText().trim().isEmpty()) {
                query.SelectAtor(gAtor1.getText(), 0, dFilme.getInt(1), 1);

            }

            // ATOR 02
            if (!gAtor2.getText().trim().isEmpty()) {
                query.SelectAtor(gAtor2.getText(), 0, dFilme.getInt(1), 1);

            }
            //ATOR 03
            if (!gAtor3.getText().trim().isEmpty()) {
                query.SelectAtor(gAtor3.getText(), 0, dFilme.getInt(1), 1);

            }
            //ATOR 04
            if (!gAtor4.getText().trim().isEmpty()) {
                query.SelectAtor(gAtor4.getText(), 0, dFilme.getInt(1), 1);

            }

            //PE
            if (!gPE.getText().trim().isEmpty()) {
                query.SelectAtor(gPE.getText(), 0, dFilme.getInt(1), 2);

            }
            //ARTE
            if (!gArte.getText().trim().isEmpty()) {
                query.SelectAtor(gArte.getText(), 0, dFilme.getInt(1), 3);

            }

            //FOTO
            if (!gFoto.getText().trim().isEmpty()) {
                query.SelectAtor(gFoto.getText(), 0, dFilme.getInt(1), 4);

            }

            //EDICAO
            if (!gEdicao.getText().trim().isEmpty()) {
                query.SelectAtor(gEdicao.getText(), 0, dFilme.getInt(1), 5);

            }

            //ROTEIRISTA
            if (!gRoteirista.getText().trim().isEmpty()) {
                query.SelectAtor(gRoteirista.getText(), 0, dFilme.getInt(1), 6);

            }

            //DIRETOR
            if (!gDiretor.getText().trim().isEmpty()) {
                query.SelectAtor(gDiretor.getText(), 0, dFilme.getInt(1), 7);

            }

            //PRODUTOR
            if (!gProdutor.getText().trim().isEmpty()) {
                query.SelectAtor(gProdutor.getText(), 0, dFilme.getInt(1), 8);

            }

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Filme");
            alert.setHeaderText(null);
            alert.setContentText("Novo filme inserido com sucesso.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao inserir dados do filme.");
            alert.showAndWait();

        }

    }

    @FXML
    public void handleButtonSalvar() throws SQLException {
        recebeGridDadosFilme();
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
    public void handleButtonCancelar() {
        limpaCampos();

    }

}
