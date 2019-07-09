/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import static edu.esprit.GUI.ForgotPasswordConfirmationController.userToConfirm;
import edu.esprit.models.User;
import edu.esprit.utils.CheckPassword;
import edu.esprit.utils.Hasher;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ayoub
 */
public class ForgotPasswordController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static User user;
    @FXML
    private PasswordField passTxt;
    @FXML
    private PasswordField passTxtConf;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        passTxt.requestFocus();
    }    

    @FXML
    private void onValidNewPassword(MouseEvent event) throws IOException {
       if(CheckPassword.isValid(passTxt.getText())){
        if(!Hasher.generatePasswordHash(passTxt.getText()).equals(UserManager.getUser().getPassword())){
        if(passTxt.getText().equals(passTxtConf.getText())){
            UserManager.getUser().setPassword(passTxt.getText());
            ServiceManager.getInstance().getUserService().editPassword(UserManager.getUser());
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage s = new Stage();
            Scene se = new Scene(root);
            s.setScene(se);
            Stage x = (Stage) passTxt.getScene().getWindow();
            x.close();
            s.show();
            
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Mot de passe non conforme!");
            alert.showAndWait();
            passTxt.requestFocus();
        }
       }else{
           Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez utiliser un nouveau mot de passe !");
            alert.showAndWait();
            passTxt.requestFocus();
           
       }
        }else {
           Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Votre mot de passe est invalide !\n un mot de passe doit contenir au minimum 8 caracteres.\n"
                    + " au moins une lettre miniscule, au moins une lettre majuscule et un chiffre de 0 à 9");
            alert.showAndWait();
            passTxt.requestFocus(); 
        }
    }
    
}
