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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author nanda
 */
public class FXMLAnchorPaneMatrizDoisModosController implements Initializable {


    @FXML
    private AnchorPane anchorPaneMatrizDoisModos;
    @FXML
    private RadioButton radioButtonAtorFilme;
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
    private ToggleGroup tipoRedeDoisModos;
    @FXML
    private ToggleGroup tipoLeituraDoisModos;
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
    
    private final boolean[] papeisSelecionados = new boolean[9];
    private boolean BoxValorada = false;
    private String anoI;
    private String anoF;
    private int tipoRede; //Tipo 1 para Ator X Filme
    private int tipoLeitura; //Tipo 1 para ID e Tipo 2 para Nome
    private String papeis = ""; // Papeis selecionados para query
   
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void handleToggleGroupTipoRede() {
        RadioButton selectedRadioButton = (RadioButton) tipoRedeDoisModos.getSelectedToggle();
        if (radioButtonAtorFilme.equals(selectedRadioButton)) {
            tipoRede = 1;

        } else {
            tipoRede = 1;

        }
    }

    @FXML
    public void handleToggleGroupTipoLeitura() {
        RadioButton selectedRadioButton = (RadioButton) tipoLeituraDoisModos.getSelectedToggle();
        if (radioButtonID.equals(selectedRadioButton)) {
            tipoLeitura = 1;

        } else {
            tipoLeitura = 2;

        }
    }

    @FXML
    public void handleCheckBoxValorada() {

        if (checkBoxValorada.isSelected()) {
            BoxValorada = true;
        } else {
            BoxValorada = false;
        }
    }

    @FXML
    public void handleTextFieldAnos() throws Exception {
        anoI = textFieldAnoInicio.getText();
        anoF = textFieldAnoFim.getText();
        if ("".equals(anoF)) {
            anoF = "2013";
        }
        if ("".equals(anoI)) {
            anoI = "1995";
        }
        int anoIni = Integer.parseInt(anoI);
        int anoFim = Integer.parseInt(anoF);

        if (anoIni > anoFim || anoIni < 1995 || anoFim > 2013) {
            throw new Exception("Ano inválido");
        }
    }

    @FXML
    public void handleCheckBoxes() {
       if (checkBoxAtor.isSelected()) {
            papeis += "'A'" + ",";
        }
        if (checkBoxProdExec.isSelected()) {
            papeis += "'PE'" + ",";
        }
        if (checkBoxDFoto.isSelected()) {
            papeis += "'F'" + ",";
        }
        if (checkBoxDArte.isSelected()) {
            papeis += "'DA'" + ",";
        }
        if (checkBoxEdicao.isSelected()) {
            papeis += "'E'" + ",";
        }
        if (checkBoxRoteirista.isSelected()) {
            papeis += "'R'" + ",";
        }
        if (checkBoxDiretor.isSelected()) {
            papeis += "'D'" + ",";
        }
        if (checkBoxProdutora.isSelected()) {
            papeis += "'P'" + ",";
        }
        if (checkBoxDistribuidora.isSelected()) {
            papeis += "'DT'" + ",";
        }
        if(papeis.endsWith(",")){
            papeis = papeis.substring (0, papeis.length() - 1);
        }
        if(papeis.equals("")){
            papeis = "'A', 'PE', 'F','DA','E','R','D', 'P', 'DT'";
        }
    }
        
    @FXML
    public void handleButtonGerarMatriz() throws IOException, SQLException, Exception{
        handleToggleGroupTipoRede();
        handleToggleGroupTipoLeitura();
        handleCheckBoxes();
        handleTextFieldAnos();
       GeraMatriz rede = new GeraMatriz("Planilha de Dois Modos Ator x Filme.csv");
       rede.PreencheMatrizDoisModos(tipoLeitura, BoxValorada, papeis, anoI, anoF);
    } 
}

