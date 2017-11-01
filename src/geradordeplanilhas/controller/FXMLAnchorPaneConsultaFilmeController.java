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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author nanda
 */
public class FXMLAnchorPaneConsultaFilmeController implements Initializable {

    @FXML
    private TextField textFieldNomeFilme;
    @FXML
    private Button buttonConsultaFilme;
    @FXML
    private TableView<Atores> tableViewAtores;
    @FXML
    private TableColumn<Atores, String> colAtor;
    @FXML
    private TableColumn<Atores, String> colPapel;
    @FXML
    private TableColumn<Atores, String> colSigla;

    private String filme;
    private ObservableList<Atores> data;
    ConsultasSQL query = new ConsultasSQL();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void preencheTableViewAtores() {
        try {
            data = FXCollections.observableArrayList();
            filme = textFieldNomeFilme.getText();
            ResultSet rs = query.ConsultaAtores(filme);

            while (rs.next()) {

                data.add(new Atores(rs.getString(3), rs.getString(4), rs.getString(5)));
                tableViewAtores.setItems(data);

            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao preencher dados do filme.");

            alert.showAndWait();
        }

        colAtor.setCellValueFactory(new PropertyValueFactory<>("ator"));
        colPapel.setCellValueFactory(new PropertyValueFactory<>("papel"));
        colSigla.setCellValueFactory(new PropertyValueFactory<>("sigla"));

        tableViewAtores.setItems(data);
    }

    @FXML
    public void handleButtonConsultaFilme() {
        preencheTableViewAtores();
    }
}
