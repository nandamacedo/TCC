/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradordeplanilhas.controller;

import geradordeplanilhas.GeraMatriz;
import geradordeplanilhas.entity.Data;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
public class FXMLAnchorPaneMatrizUmModoController implements Initializable {

    @FXML
    private AnchorPane anchorPaneMatrizUmModo;
    @FXML
    private RadioButton radioButtonAtorAtor;
    @FXML
    private RadioButton radioButtonFilmeFilme;
    @FXML
    private ToggleGroup tipoRedeUmModo;
    @FXML
    private ToggleGroup tipoLeituraUmModo;
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

    private boolean BoxValorada = false;
    private String anoI;
    private String anoF;
    private int tipoRede; //Tipo 1 para Ator e Tipo 2 para Filme
    private int tipoLeitura; //Tipo 1 para ID e Tipo 2 para Nome-Sigla
    private String papeis; // Papeis selecionados para query
    private Service<Void> backgroundThread;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void handleToggleGroupTipoRede() {
        RadioButton selectedRadioButton = (RadioButton) tipoRedeUmModo.getSelectedToggle();
        if (radioButtonAtorAtor.equals(selectedRadioButton)) {
            tipoRede = 1;

        } else {
            tipoRede = 2;

        }
    }

    @FXML
    public void handleToggleGroupTipoLeitura() {
        RadioButton selectedRadioButton = (RadioButton) tipoLeituraUmModo.getSelectedToggle();
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
        papeis = "";
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
        if (papeis.endsWith(",")) {
            papeis = papeis.substring(0, papeis.length() - 1);
        }
        if (papeis.equals("")) {
            papeis = "'A', 'PE', 'F','DA','E','R','D', 'P'";
        }
    }

    @FXML
    public void handleButtonGerarMatriz(ActionEvent event) throws IOException, SQLException, Exception {
        backgroundThread = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        handleToggleGroupTipoRede();
                        handleToggleGroupTipoLeitura();
                        handleCheckBoxes();
                        handleTextFieldAnos();
                        Data data = new Data();

                        if (tipoRede == 1) {

                            GeraMatriz rede = new GeraMatriz("/home/nanda/NetBeansProjects/GeradorDePlanilhas/Resultados/" + "1ModoAtores" + anoI + "-" + anoF + "-" + data.Data2String() + ".csv");
                            rede.PreencheMatrizUmModo(tipoRede, tipoLeitura, BoxValorada, papeis, anoI, anoF);

                        } else {

                            GeraMatriz rede = new GeraMatriz("/home/nanda/NetBeansProjects/GeradorDePlanilhas/Resultados/" + "1ModoFilmes" + anoI + "-" + anoF + "-" + data.Data2String() + ".csv");
                            rede.PreencheMatrizUmModo(tipoRede, tipoLeitura, BoxValorada, papeis, anoI, anoF);

                        }
                        return null; //To change body of generated methods, choose Tools | Templates.
                    }
                };
            }
        };
        backgroundThread.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                System.out.print("Done!");
                
            }
        });
        backgroundThread.restart();

    }

}
