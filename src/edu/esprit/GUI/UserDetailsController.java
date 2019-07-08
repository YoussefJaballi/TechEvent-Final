/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.utils.UserManager;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author azer
 */
public class UserDetailsController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label adresse;
    @FXML
    private Label email;
    @FXML
    private HBox buttonContainer;
    @FXML
    private Button modifier;
    @FXML
    private Label ddn;
    private AnchorPane screen;
    @FXML
    private Label tel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nom.setText(UserManager.getUser().getLastName());
        prenom.setText(UserManager.getUser().getName());
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        ddn.setText(dateFormat.format(UserManager.getUser().getBirthday()));
        email.setText(UserManager.getUser().getEmail());
        adresse.setText(UserManager.getUser().getAdress());
        tel.setText(UserManager.getUser().getPhone());
        
        
    }

    @FXML
    private void onModifyClicked(MouseEvent event) {
        AnchorPane content = null;
        
        try {
                this.screen = new AnchorPane();
                AnchorPane root = FXMLLoader.load(getClass().getResource("ModifyUserDetails.fxml"));
                HomeController.instance.setContent(root);
                //this.personTable.getSelectionModel().clearSelection();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }

}
