/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

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
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author yjaballi
 */
public class HomeController implements Initializable {

    public static HomeController instance;

    @FXML
    private AnchorPane homeContainer;
    @FXML
    private VBox menu;
    @FXML
    private AnchorPane screen;
    @FXML
    private ImageView ProfileImg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        instance = this;
        menu.getStyleClass().add("menu");

        menu.getChildren().add(createMenuButton("Profile", "UserDetails"));
        menu.getChildren().add(createMenuButton("event", "EventDetails"));

        menu.getChildren().add(createMenuButton("Liste des evennements", "EventList"));
        menu.getChildren().add(createMenuButton("Ma calendrier", "/academiccalendar/ui/main/FXMLDocument"));

        if (UserManager.getUser().getRole().getId() == 1) {
            menu.getChildren().add(createMenuButton("Reclamations", "Reports"));
        } else if (UserManager.getUser().getRole().getId() == 3) {
            menu.getChildren().add(createMenuButton("Creer evennement", "AddEvent"));
        }

        AnchorPane content = null;
        try {
            /*
             set parameter to FXML page
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("EventList.fxml"));
             Parent root = loader.load();
             EventListController controller = loader.<EventListController>getController();
             controller.setFilter("dssd");
             content = (AnchorPane) root;*/
            content = FXMLLoader.load(getClass().getResource("EventList.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        screen.getChildren().add(content);

        // TODO
    }

    private Button createMenuButton(String label, String pageName) {
        Button button = new Button(label);
        button.getStyleClass().add("menu-button");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMinHeight(50);
        button.setOnAction(e -> {
            screen.getChildren().clear();
            AnchorPane content = null;
            try {
                content = FXMLLoader.load(getClass().getResource(pageName + ".fxml"));
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            screen.getChildren().add(content);
        });
        return button;
    }
    public void setContent(AnchorPane content){
        screen.getChildren().clear();
        screen.getChildren().add(content);
    }

}
