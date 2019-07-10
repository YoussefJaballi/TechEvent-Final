/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import static edu.esprit.GUI.ChangeMailController.mailToChange;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ayoub
 */
public class ChangePhoneController implements Initializable {

    @FXML
    private Button next;
    @FXML
    private TextField phoneNumber;
    public static String phoneToChange;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onNext(MouseEvent event) throws IOException {

        if (!phoneNumber.getText().equals(UserManager.getUser().getPhone())) {
            phoneToChange = phoneNumber.getText();
            Parent root = FXMLLoader.load(getClass().getResource("ChangePhoneConfirmation.fxml"));
            Stage s = new Stage();
            Scene se = new Scene(root);
            s.setScene(se);
            Stage x = (Stage) phoneNumber.getScene().getWindow();
            x.close();
            s.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez choisir un autre numéro !");
            alert.showAndWait();
            phoneNumber.requestFocus();
        }
    }
}
