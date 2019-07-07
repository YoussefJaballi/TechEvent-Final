
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import static edu.esprit.GUI.ValidateUserController.userToConfirm;
import edu.esprit.models.User;
import edu.esprit.services.implementation.UserService;
import edu.esprit.utils.ServiceManager;
import edu.esprit.utils.UserManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yjaballi
 */
public class LoginController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private TextField Login;
    @FXML
    private TextField Pasword;
    TextField l = new TextField("test");

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {

        UserService us = new UserService();
        try {
            System.out.println(Pasword.getText());
            User u = ServiceManager.getInstance().getUserService().login(Login.getText(), Pasword.getText());
            if (u != null) {
                System.out.println(u);
                if (u.isIsActivated())
                {
                    UserManager.setUser(u);
                    UserManager.setParticipation(ServiceManager.getInstance().getParticipationService().findByUser(u.getId()));
                    Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                    Stage s = new Stage();
                    Scene se = new Scene(root);
                    s.setScene(se);
                    Stage x = (Stage) Login.getScene().getWindow();
                    x.close();
                    s.show();
                }else{
                   ValidateUserController.userToConfirm=u;
                    Parent root = FXMLLoader.load(getClass().getResource("ValidateUser.fxml"));
                    Stage s = new Stage();
                    Scene se = new Scene(root);
                    s.setScene(se);
                    Stage x = (Stage) Login.getScene().getWindow();
                    x.close();
                    s.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Login ou mot de passe incorect !");
                alert.showAndWait();
                Login.requestFocus();
                System.out.println("utilisateur introuvable");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("failed");
            //Logger.getLogger(FXMLajoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Logger.getLogger(FXMLajoutController.class.getName()).log(Level.SEVERE, null, ex);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //container.getChildren().add(l);
        // TODO
    }

    @FXML
    private void onForgotPassword(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ForgotPasswordLogin.fxml"));
            Stage s = new Stage();
            Scene se = new Scene(root);
            s.setScene(se);
            Stage x = (Stage) Login.getScene().getWindow();
            x.close();
            s.show();
       
    }

    @FXML
    private void onSignup(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RegistrationForm1.fxml"));
            Stage s = new Stage();
            Scene se = new Scene(root);
            s.setScene(se);
            Stage x = (Stage) Login.getScene().getWindow();
            x.close();
            s.show();

    }
        
}
