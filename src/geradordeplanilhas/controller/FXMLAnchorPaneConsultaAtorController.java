/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradordeplanilhas.controller;

import geradordeplanilhas.entity.Filme;
import geradordeplanilhas.model.sql.ConsultasSQL;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author nanda
 */
public class FXMLAnchorPaneConsultaAtorController implements Initializable {

    @FXML
    private TextField textFieldNomeAtor;
    @FXML
    private Button buttonConsultaAtor;
    @FXML
    private TableView<Filme> tableViewFilmes;
    @FXML
    private TableColumn<Filme, String> colFilme;
    @FXML
    private TableColumn<Filme, String> colAno;
    @FXML
    private TableColumn<Filme, Integer> colSalas;
    @FXML
    private TableColumn<Filme, Integer> colPublico;
    @FXML
    private TableColumn<Filme, Double> colRenda;
    @FXML
    private TableColumn<Filme, String> colUF;

    private String ator;
    private ObservableList<Filme> data;
    ConsultasSQL query = new ConsultasSQL();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void preencheTableViewFilmes() {

        
        try {
            data = FXCollections.observableArrayList();
            ator = textFieldNomeAtor.getText();
            ResultSet rs = query.ConsultaFilmes(ator);

            while (rs.next()) {

                data.add(new Filme(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5), rs.getString(6)));
                tableViewFilmes.setItems(data);

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        
        colFilme.setCellValueFactory(new PropertyValueFactory<> ("titulo"));
        colAno.setCellValueFactory(new PropertyValueFactory<> ("ano"));
        colSalas.setCellValueFactory(new PropertyValueFactory<> ("salas"));
        colPublico.setCellValueFactory(new PropertyValueFactory<> ("publico"));
        colRenda.setCellValueFactory(new PropertyValueFactory<> ("renda"));
        colUF.setCellValueFactory(new PropertyValueFactory<> ("UF"));
        tableViewFilmes.setItems(data);
    }

    @FXML
    public void handleButtonConsultaAtor() {
        preencheTableViewFilmes();

    }
}
