
package geradordeplanilhas.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author nanda
 */
public class FXMLGeradorController implements Initializable {
    
    @FXML
    private MenuItem menuItemMatrizUmModo;
    @FXML
    private MenuItem menuItemMatrizDoisModos;
    @FXML
    private AnchorPane anchorPanePrincipal;
    @FXML
    private MenuItem menuItemSair;

    


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    }    
    
    @FXML
    public void handleMenuItemMatrizUmModo() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/geradordeplanilhas/view/FXMLAnchorPaneMatrizUmModo.fxml"));
        anchorPanePrincipal.getChildren().setAll(a);
    } 
    
    @FXML
    public void handleMenuItemMatrizDoisModos() throws IOException{
        AnchorPane b = (AnchorPane) FXMLLoader.load(getClass().getResource("/geradordeplanilhas/view/FXMLAnchorPaneMatrizDoisModos.fxml"));
        anchorPanePrincipal.getChildren().setAll(b);
    } 
    
    @FXML
    public void fecharTelaAction() {
        System.exit(0);
    }
}
