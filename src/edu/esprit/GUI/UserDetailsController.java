/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> tnom;
    @FXML
    private TableColumn<?, ?> tdate;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onModifyClicked(MouseEvent event) {
        
    }

}
