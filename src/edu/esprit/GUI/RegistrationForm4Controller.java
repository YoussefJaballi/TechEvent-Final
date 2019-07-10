/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.utils.ServiceManager;
import edu.esprit.utils.UserManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Popup;

/**
 * FXML Controller class
 *
 * @author yjaballi
 */
public class RegistrationForm4Controller implements Initializable {

    @FXML
    private Button button;
    @FXML
    private TextField LoginTxt;
    @FXML
    private Label LoginLabel;
    @FXML
    private TextField PasswordTxt;
    @FXML
    private Label PasswordLabel;
    @FXML
    private TextField ConfirmPasswordTxt;
    @FXML
    private Label ConfirmPasswordLabel;
    
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ConfirmPasswordTxt.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                 if(!(ConfirmPasswordTxt.getText().isEmpty()))
                {
                //using regex phone number validation
                        if (!(ConfirmPasswordTxt.getText().equals(PasswordTxt.getText()))) {
                            //&&!(TelephoneTxt.getText().substring(0, 4).equals("216"))
                            
                alert.setContentText("les 2 mots de passe ne sont pas identiques");
                alert.showAndWait();                    
                            ConfirmPasswordTxt.setText("");
                             }
                }
             
            }
        });
    }    

    @FXML
    private void handletFinishttonAction(ActionEvent event) {
        
        if(LoginTxt.getText() != null && PasswordTxt.getText() !=  null && ConfirmPasswordTxt.getText().equals(PasswordTxt.getText()))
        {
            
            UserManager.getRegisterUser().setLogin(LoginTxt.getText());
            UserManager.getRegisterUser().setPassword(PasswordTxt.getText());
            //UserManager.getRegisterUser().setUserActivated(false);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("user");
            System.out.println("user details interface 4:   "+UserManager.getRegisterUser());
            try
            {
                ServiceManager.getInstance().getUserService().create(UserManager.getRegisterUser());
                
                alert.setContentText("creer avec succes");
                alert.showAndWait();
                
            }
            catch(Exception e)
            {
                
                alert.setContentText("echec de validation a cause de "+e.getMessage());
                alert.showAndWait();
                e.printStackTrace();
            }
        }
    }
    
}
