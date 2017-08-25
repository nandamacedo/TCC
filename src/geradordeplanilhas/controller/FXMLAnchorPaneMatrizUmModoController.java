/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradordeplanilhas.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author nanda
 */
public class FXMLAnchorPaneMatrizUmModoController implements Initializable {


    @FXML
    private AnchorPane anchorPaneMatrizUmModo;
    @FXML
    private RadioButton radioButtonAtorAtor;
    @FXML
    private RadioButton radioButtonFilmeFilme;
    @FXML
    private Button buttonGerarMatriz;
    @FXML
    private TextField textFieldAnoInicio;
    @FXML
    private TextField textFieldAnoFim;
    @FXML
    private RadioButton radioButtonID;
    @FXML
    private RadioButton radioButtonNome;
    @FXML
    private CheckBox checkBoxValorada;
    @FXML
    private CheckBox checkBoxAtor;
    @FXML
    private CheckBox checkBoxProdExec;
    @FXML
    private CheckBox checkBoxDFoto;
    @FXML
    private CheckBox checkBoxDArte;
    @FXML
    private CheckBox checkBoxEdicao;
    @FXML
    private CheckBox checkBoxDiretor;
    @FXML
    private CheckBox checkBoxRoteirista;
    @FXML
    private CheckBox checkBoxProdutora;
    @FXML
    private CheckBox checkBoxDistribuidora;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void handleButtonGerarMatriz() throws IOException, SQLException{
       GeraMatriz rede = new GeraMatriz("Planiplha Um Modo.csv");
       String anoI = textFieldAnoInicio.getText();
       String anoF = textFieldAnoFim.getText();
       rede.PreencheMatrizUmModo(anoI, anoF);
       
    } 
    
}
