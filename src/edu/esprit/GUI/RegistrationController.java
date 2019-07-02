/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author yjaballi
 */
public class RegistrationController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private Label label;
    @FXML
    private TextField Login;
    @FXML
    private TextField Pasword;
    @FXML
    private AnchorPane registrationAnchorpane;
    @FXML
    private Label loginLabel;
    @FXML
    private Label passwordLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // voir ServiceImageUpload dans projet HardeSocialPro
    }    

    @FXML
    private void handleRegistrationAction(ActionEvent event) {
        if(Login.getText() !=  null && Pasword.getText() !=  null)
        {
            
            registrationAnchorpane.getChildren().remove(Login);
            registrationAnchorpane.getChildren().remove(Pasword);
            registrationAnchorpane.getChildren().remove(loginLabel);
            registrationAnchorpane.getChildren().remove(passwordLabel);
            registrationAnchorpane.getChildren().add(new Label("email"));
            registrationAnchorpane.getChildren().add(new Label("name"));
        }
    }
    
}
