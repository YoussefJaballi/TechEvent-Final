/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import static edu.esprit.GUI.ForgotPasswordConfirmationController.userToConfirm;
import static edu.esprit.GUI.ForgotPasswordController.user;
import edu.esprit.services.implementation.UserService;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ayoub
 */
public class ChangePasswordController implements Initializable {

    @FXML
    private PasswordField passTxt;
    @FXML
    private PasswordField passTxtConf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onValidNewPassword(MouseEvent event) throws IOException {
        if (!Hasher.generatePasswordHash(passTxt.getText()).equals(UserManager.getUser().getPassword())) {
            if (passTxt.getText().equals(passTxtConf.getText())) {
                UserManager.getUser().setPassword(passTxt.getText());
                ServiceManager.getInstance().getUserService().editPassword(user);
                ForgotPasswordController.user = userToConfirm;
                Stage x = (Stage) passTxt.getScene().getWindow();
                x.close();

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Mot de passe non conforme!");
                alert.showAndWait();
                passTxt.requestFocus();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez utiliser un nouveau mot de passe !");
            alert.showAndWait();
            passTxt.requestFocus();

        }
    }

}
