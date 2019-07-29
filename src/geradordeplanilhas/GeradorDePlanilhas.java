/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradordeplanilhas;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nanda
 */
public class GeradorDePlanilhas extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/FXMLGerador.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Análise de Redes: Cinematografia Brasileira");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
      

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
