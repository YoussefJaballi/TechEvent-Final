/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;


import edu.esprit.models.User;
import edu.esprit.utils.CodeGeneration;
import edu.esprit.utils.ServiceMail;
import edu.esprit.utils.UserManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author Ayoub
 */
public class ChangeMailController implements Initializable {

    @FXML
    private Button next;
    @FXML
    private TextField email;
    public static String mailToChange;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onNext(MouseEvent event) throws IOException {
        
       if(!email.getText().equals(UserManager.getUser().getEmail())){
        mailToChange=email.getText();
        Parent root = FXMLLoader.load(getClass().getResource("ChangeMailConfirmation.fxml"));
                    Stage s = new Stage();
                    Scene se = new Scene(root);
                    s.setScene(se);
                    Stage x = (Stage) email.getScene().getWindow();
                    x.close();
                    s.show();
        
    
       }else{
           Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez choisir un autre email !");
            alert.showAndWait();
            email.requestFocus();
       }
}
}
