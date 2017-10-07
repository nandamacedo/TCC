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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class FXMLAnchorPaneConsultaDadosFilmeController implements Initializable {

    @FXML
    private TextField textFieldNomeFilme;
    @FXML
    private Button buttonConsultaDadosFilme;
    @FXML
    private TableView<Filme> tableViewDadosFilme;
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

    private String filme;
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
    public void preencheTableViewFilme() {

        
        try {
            data = FXCollections.observableArrayList();
            filme = textFieldNomeFilme.getText();
            ResultSet rs = query.ConsultaDadosFilme(filme);

            while (rs.next()) {

                data.add(new Filme(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5), rs.getString(6)));
                tableViewDadosFilme.setItems(data);

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
        tableViewDadosFilme.setItems(data);
    }
    
    @FXML
    public void handleButtonConsultaDadosFilme(){
        preencheTableViewFilme();
    
    }
    
}
