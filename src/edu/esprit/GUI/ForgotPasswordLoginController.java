/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.models.User;
import edu.esprit.utils.ServiceManager;
import edu.esprit.utils.UserManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ayoub
 */
public class ForgotPasswordLoginController implements Initializable {

    @FXML
    private TextField loginTxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loginTxt.requestFocus();
    }    

    @FXML
    private void onNext(MouseEvent event) throws IOException {
 
         UserManager.setUser(ServiceManager.getInstance().getUserService().findByLogin(loginTxt.getText()));
        if(UserManager.getUser()==null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.setContentText("Code invalid !!");
            alert.showAndWait();
            loginTxt.requestFocus();
        } else{
            Parent root = FXMLLoader.load(getClass().getResource("ForgotPasswordConfirmation.fxml"));
                    Stage s = new Stage();
                    Scene se = new Scene(root);
                    s.setScene(se);
                    Stage x = (Stage) loginTxt.getScene().getWindow();
                    x.close();
                    s.show();
        }
    }
    
}
